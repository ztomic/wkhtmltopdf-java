package com.ztomic.wkhtmltopdf.source;

/**
 * Local file or remote url source.
 */
public class UrlSource implements Source<String> {

	private String url;
	
	public UrlSource(String url) {
		this.url = url;
	}
	
	public String getSource() {
		return url;
	}
	
}
