package enums;

public enum MovimentoPlayer {
	CAINDO(1),
	SUBINDO(2),
	POUSANDO(3),
	ANDANDO(4),
	PARANDO(5),
	ATACANDO(6),
	RESPIRANDO(7),
	RESPIRANDO_EM_COMBATE(8),
	INVESTINDO(9),
	HABILIDADE(10);

	private final int cod;

	public static Integer totalTipos() {
		return MovimentoPlayer.values().length;
	}

	private MovimentoPlayer(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}



	public static MovimentoPlayer toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (MovimentoPlayer x : MovimentoPlayer.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
