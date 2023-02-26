package enums;

public enum TipoFonte {
	BOLD_ITALIC("BoldItalic"),
	BOLD("Bold"),
	ITALIC("Italic"),
	REGULAR("Regular"),
	SEMI_BOLD("SemiBold"),
	SEMI_BOLD_ITALIC("SemiBoldItalic");

	private final String cod;

	public static Integer totalTipos() {
		return TipoFonte.values().length;
	}

	TipoFonte(String cod) {
		this.cod = cod;
	}

	public String getCod() {
		return cod;
	}



	public static TipoFonte toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoFonte x : TipoFonte.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
