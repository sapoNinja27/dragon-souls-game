package enums;

public enum TipoPlayer {
	TAI(1),
	SANDER(2),
	ACE(3);

	private final int cod;

	public static Integer totalTipos() {
		return TipoPlayer.values().length;
	}

	private TipoPlayer(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static TipoPlayer toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoPlayer x : TipoPlayer.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
