package enums;

public enum Texturas {
	MARMORE(1), 
	TIJOLO(2),
	MADEIRA(3),
	CIMENTO(4);

	private int cod;

	public static Integer totalTipos() {
		return Texturas.values().length;
	}

	private Texturas(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static Texturas toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Texturas x : Texturas.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
