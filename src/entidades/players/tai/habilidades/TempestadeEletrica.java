package entidades.players.tai.habilidades;

import entidades.players.Habilidade;
import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class TempestadeEletrica extends Habilidade {

	@Override
	public String getTitulo(){
		return "Tempestade elétrica";
	}

	@Override
	public String getDescricao(){
		return "Tai cria uma redoma elétrica em torno dele, causando 15 de dano por segundo a inimigos proximos, e causando energizar. ";
	}

	@Override
	public String getCusto(){
		return "Custa 5 de furia por segundo";
	}

	@Override
	public BufferedImage getIcone() {
		return sprite.getSprite(128 * 3,0,128,128);
	}

	@Override
	public MovimentoPlayer getMovimentoPlayer() {
		return MovimentoPlayer.HABILIDADE_TEMPESTADE_ELETRICA;
	}

	@Override
	public boolean isBasica(){
		return false;
	}
}
