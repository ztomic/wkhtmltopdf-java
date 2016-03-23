package com.ztomic.wkhtmltopdf;

import static org.hamcrest.core.StringContains.containsString;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Assert;
import org.junit.Test;

import com.ztomic.wkhtmltopdf.argument.Argument;
import com.ztomic.wkhtmltopdf.argument.Option;
import com.ztomic.wkhtmltopdf.source.Source;

public class WkHtmlToPdfTest {
	
	static final String TEST_STRING = "QWERTZUIOPŠĐASDFGHJKLČĆŽYXCVBNM\nqwertzuiopšđasdfghjklčćžyxcvbnm\n€ÀÁÂÃÄÅÈÉÊËÌÍÎÏÑÒÓÔÕÖØÙÚÛÜÝß\nàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ";

	@Test
	public void testCommand() throws Exception {
		WkHtmlToPdf pdf = new WkHtmlToPdf();
		pdf.addToc();
		pdf.addArguments(
			Argument.from(Option.PageOption.EnableJavascript),
			Argument.from(Option.HeaderAndFooterOption.HeaderHtml, "file:///example.html")
		);
		pdf.addSources(Source.fromUrl("http://www.google.com"));
		Assert.assertThat("command params should contain the --enable-javascript and --header-html", pdf.getCommand(), containsString("--enable-javascript --header-html file:///example.html"));
	}

	@Test
	public void testPdfFromStringToString() throws Exception {
		WkHtmlToPdf pdf = new WkHtmlToPdf();
		pdf.addArguments(Option.PageOption.EnableJavascript);
		pdf.addSources(Source.from("<html><head><meta charset=\"utf-8\"></head><h1>" + TEST_STRING + "</h1></html>", "UTF-8"));

		byte[] pdfBytes = pdf.getPdfBytes();
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {
			String pdfText = pdfTextStripper.getText(document);
			Assert.assertThat("document should contain the creditorName", pdfText, containsString(TEST_STRING));
		}
	}
	
	@Test
	public void testPdfFromStringToFile() throws Exception {
		WkHtmlToPdf pdf = new WkHtmlToPdf();
		pdf.addArguments(Option.PageOption.EnableJavascript);
		pdf.addSources(Source.from("<html><head><meta charset=\"utf-8\"></head><h1>" + TEST_STRING + "</h1></html>", "UTF-8"));
		
		Path pdfFile = pdf.save(Files.createTempFile("testpdf", ".pdf"));
		pdfFile.toFile().deleteOnExit();
		
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		try (PDDocument document = PDDocument.load(pdfFile.toFile())) {
			String pdfText = pdfTextStripper.getText(document);
			Assert.assertThat("document should contain the creditorName", pdfText, containsString(TEST_STRING));
		}
	}
}
