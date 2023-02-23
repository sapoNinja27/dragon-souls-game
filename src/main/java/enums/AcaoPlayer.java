package enums;

public enum AcaoPlayer {
	CIMA(1),
	BAIXO(2),
	DIREITA(3),
	ESQUERDA(4),
	DASH(5),
	ULTIMATE(6),
	SOCO_FRACO(7),
	PARAR(8),
	PARAR_PULO(9);

	private final int cod;

	public static Integer totalTipos() {
		return AcaoPlayer.values().length;
	}

	private AcaoPlayer(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static AcaoPlayer toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (AcaoPlayer x : AcaoPlayer.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
