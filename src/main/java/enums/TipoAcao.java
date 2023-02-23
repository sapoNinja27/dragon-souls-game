package enums;

public enum TipoAcao {
	PRESSIONAR(1),
	SOLTAR(2);

	private final int cod;

	public static Integer totalTipos() {
		return TipoAcao.values().length;
	}

	private TipoAcao(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static TipoAcao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoAcao x : TipoAcao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
