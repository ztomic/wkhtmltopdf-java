package com.ztomic.wkhtmltopdf.source;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;

public interface Source<T> {
	
	public static InputStreamSource from(byte[] bytes) {
		return new InputStreamSource(bytes);
	}
	
	public static InputStreamSource from(InputStream inputStream) throws IOException {
		return new InputStreamSource(IOUtils.toByteArray(inputStream));
	}
	
	public static InputStreamSource from(String content, String charsetName) throws IOException {
		return new InputStreamSource(content.getBytes(charsetName));
	}
	
	public static UrlSource fromUrl(String url) {
		return new UrlSource(url);
	}
	
	public static UrlSource from(File file) {
		return new UrlSource(file.getAbsolutePath());
	}
	
	public static UrlSource from(Path path) {
		return new UrlSource(path.toAbsolutePath().toString());
	}

	T getSource();
	
}
