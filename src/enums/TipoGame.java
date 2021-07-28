package enums;

public enum TipoGame {
	MENU(1), 
	NORMAL(2),
	CUTSCENE(3);

	private int cod;

	public static Integer totalTipos() {
		return TipoGame.values().length;
	}

	private TipoGame(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static TipoGame toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoGame x : TipoGame.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
