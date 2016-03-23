package com.ztomic.wkhtmltopdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.ztomic.wkhtmltopdf.argument.Argument;
import com.ztomic.wkhtmltopdf.argument.Option;
import com.ztomic.wkhtmltopdf.source.InputStreamSource;
import com.ztomic.wkhtmltopdf.source.Source;
import com.ztomic.wkhtmltopdf.source.UrlSource;

public class WkHtmlToPdf {

	public static final String DEFAULT_EXECUTABLE = "wkhtmltopdf";

	private String executable;
	private List<Argument> arguments = new LinkedList<>();
	private List<String> environment = new LinkedList<>();
	private List<Source<?>> sources = new LinkedList<>();
	private boolean toc = false;
	
	public WkHtmlToPdf(String executable) {
		this.executable = executable;
		addEnvironmentVariables("COLUMNS=300 < /dev/null");
	}

	public WkHtmlToPdf() {
		this(DEFAULT_EXECUTABLE);
	}

	public void addSources(Source<?>... sources) {
		for (Source<?> source : sources) {
			this.sources.add(source);
		}
	}

	public void addToc() {
		this.toc = true;
	}

	public void addArguments(Argument... parameters) {
		for (Argument param : parameters) {
			this.arguments.add(param);
		}
	}
	
	public void addArguments(Option... options) {
		for (Option o : options) {
			this.arguments.add(new Argument(o));
		}
	}
	
	public void addEnvironmentVariables(String... variables) {
		for (String env : variables) {
			this.environment.add(env);
		}
	}

	public Path save(Path path) throws IOException {
		return save(path, getPdfBytes());
	}

	protected Path save(Path path, byte[] document) throws IOException {
		return Files.write(path, document);
	}
	
	public byte[] getPdfBytes() throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		convert(outputStream);
		return outputStream.toByteArray();
	}
	
	public void convert(OutputStream outputStream) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		
		Process process = runtime.exec(getCommandArray(), getEnvironmentArray());

		for (Source<?> source : sources) {
			if (source instanceof InputStreamSource) {
				OutputStream stdInStream = process.getOutputStream();
				IOUtils.copy(((InputStreamSource) source).getSource(), stdInStream);
				IOUtils.closeQuietly(stdInStream);
			}
		}
		
		StreamReader outputStreamReader = new StreamReader(process.getInputStream(), outputStream);
		outputStreamReader.setName(getClass().getName() + "-OutputStreamReader-" + outputStreamReader.getId());
		outputStreamReader.start();

		ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
		StreamReader errorStreamReader = new StreamReader(process.getErrorStream(), errorStream);
		errorStreamReader.setName(getClass().getName() + "-ErrorStreamReader-" + errorStreamReader.getId());
		errorStreamReader.start();

		try {
			outputStreamReader.join();
			errorStreamReader.join();
			process.waitFor();
		} catch (InterruptedException e) {
			throw new IOException(e);
		}

		if (process.exitValue() != 0) {
			throw new IOException("Process (" + getCommand() + ") exited with status code " + process.exitValue() + ":\n" + errorStream.toString());
		}

		if (outputStreamReader.getException() != null) {
			throw outputStreamReader.getException();
		}

		if (errorStreamReader.getException() != null) {
			throw errorStreamReader.getException();
		}
	}
	
	protected String[] getEnvironmentArray() {
		return this.environment.stream().toArray(String[]::new);
	}

	protected String[] getCommandArray() {
		List<String> commandLine = new LinkedList<String>();
		commandLine.add(executable);

		if (toc) {
			commandLine.add("toc");
		}

		arguments.forEach(a -> {
			commandLine.add(a.getOption().command());

			String value = a.getValue();

			if (value != null) {
				commandLine.add(a.getValue());
			}
		});

		sources.forEach(source -> {
			if (source instanceof InputStreamSource) {
				commandLine.add("-");
			} else if (source instanceof UrlSource) {
				commandLine.add(((UrlSource) source).getSource());
			} else {
				throw new IllegalArgumentException("Unsupported source " + source);
			}
		});

		commandLine.add("-");

		return commandLine.toArray(new String[commandLine.size()]);
	}

	protected String getCommand() {
		return String.join(" ", getCommandArray());
	}

	class StreamReader extends Thread {

		private InputStream inputStream;
		private OutputStream outputStream;
		private IOException exception;

		StreamReader(InputStream inputStream, OutputStream outputStream) {
			this.inputStream = inputStream;
			this.outputStream = outputStream;
		}

		@Override
		public void run() {
			try {
				IOUtils.copy(inputStream, outputStream);
				IOUtils.closeQuietly(inputStream);
			} catch (IOException e) {
				this.exception = e;
			}
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public OutputStream getOutputStream() {
			return outputStream;
		}

		public IOException getException() {
			return exception;
		}

	}
}
