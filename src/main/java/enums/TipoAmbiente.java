package enums;

public enum TipoAmbiente {
	RUA(1), 
	TELHADO(2),
	ESGOTOS(3),
	DENTRO(4);

	private int cod;

	public static Integer totalTipos() {
		return TipoAmbiente.values().length;
	}

	private TipoAmbiente(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static TipoAmbiente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoAmbiente x : TipoAmbiente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
