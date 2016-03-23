package com.ztomic.wkhtmltopdf.argument;

public class Argument {

	private Option option;
	private String value;
	
	public Argument(Option option, String value) {
		this.option = option;
		this.value = value;
	}
	
	public Argument(Option option) {
		this(option, null);
	}
	
	public static Argument from(Option option) {
		return new Argument(option);
	}
	
	public static Argument from(Option option, String value) {
		return new Argument(option, value);
	}
	
	public Option getOption() {
		return option;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (option != null) {
			builder.append(option.command());
		}
		if (value != null) {
			builder.append(" ").append(value);
		}
		return builder.toString();
	}
	
}
