package main.enums;

public enum TipoFonte {
	BOLD_ITALIC("BoldItalic"),
	BOLD("Bold"),
	ITALIC("Italic"),
	REGULAR("Regular"),
	SEMI_BOLD("SemiBold"),
	SEMI_BOLD_ITALIC("SemiBoldItalic");

	private final String cod;

	TipoFonte(String cod) {
		this.cod = cod;
	}

	public String getCod() {
		return cod;
	}
}
