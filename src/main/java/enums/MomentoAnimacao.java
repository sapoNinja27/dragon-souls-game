package enums;

public enum MomentoAnimacao {
	INICIO(1),
	DURANTE(2),
	FIM(3);

	private final int cod;

	public static Integer totalTipos() {
		return MomentoAnimacao.values().length;
	}

	private MomentoAnimacao(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static MomentoAnimacao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (MomentoAnimacao x : MomentoAnimacao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
