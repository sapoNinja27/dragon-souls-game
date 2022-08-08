package Entidades.Players;

public class Habilidade {
	private int nivel;
	private int melhoria;
	private boolean basica;

	public Habilidade(boolean basica) {
		this.basica = basica;
	}

	public Habilidade() {
		this.basica = false;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getMelhoria() {
		return melhoria;
	}

	public void setMelhoria(int melhoria) {
		this.melhoria = melhoria;
	}

	public boolean isBasica() {
		return basica;
	}
}
