package enums;

public enum TipoMenu {
	INICIAL(1), 
	PAUSE(2),
	HABILIDADES(3),
	OPCOESPRINCIPAL(4),
	OPCOESPAUSE(5),
	LOAD(6),
	INVENTARIO(7);

	private int cod;

	public static Integer totalTipos() {
		return TipoMenu.values().length;
	}

	private TipoMenu(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static TipoMenu toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoMenu x : TipoMenu.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
