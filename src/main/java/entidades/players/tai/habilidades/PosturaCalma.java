package entidades.players.tai.habilidades;

import entidades.players.Habilidade;
import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaCalma extends Habilidade {

	@Override
	public String getTitulo(){
		return "Postura calma";
	}

	@Override
	public String getDescricao(){
		return "Tai assume uma postura calma, curando 30% da furia atual como vida durante 5 segundos. ";
	}

	@Override
	public String getCusto(){
		return "Consome 30% da furia atual";
	}

	@Override
	public BufferedImage getIcone() {
		return sprite.getSprite(128 * 5,0,128,128);
	}

	@Override
	public MovimentoPlayer getMovimentoPlayer() {
		return MovimentoPlayer.HABILIDADE_POSTURA_CALMA;
	}

	@Override
	public boolean isBasica(){
		return false;
	}
}
