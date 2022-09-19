package interfaces;

import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public interface HabilidadesCommons {

	default String getTitulo(){
		return "";
	}
	default String getDescricao(){
		return "";
	}
	default String getCusto(){
		return "";
	}
	default BufferedImage getIcone() {
		return null;
	}
	default MovimentoPlayer getMovimentoPlayer() {
		return MovimentoPlayer.RESPIRANDO;
	}
	default boolean isBasica() {
		return false;
	}
}
