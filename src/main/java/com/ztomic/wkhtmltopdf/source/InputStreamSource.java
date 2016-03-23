package com.ztomic.wkhtmltopdf.source;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputStreamSource implements Source<InputStream> {

	private byte[] bytes;
	
	public InputStreamSource(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public InputStream getSource() {
		return new ByteArrayInputStream(this.bytes);
	}
	
}
