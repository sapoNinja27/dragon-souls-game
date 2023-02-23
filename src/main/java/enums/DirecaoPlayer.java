package enums;

public enum DirecaoPlayer {
	ESQUERDA(1),
	DIREITA(2);

	private final int cod;

	public static Integer totalTipos() {
		return DirecaoPlayer.values().length;
	}

	private DirecaoPlayer(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static DirecaoPlayer toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (DirecaoPlayer x : DirecaoPlayer.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
