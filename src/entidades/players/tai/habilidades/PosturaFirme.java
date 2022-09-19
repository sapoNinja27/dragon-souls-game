package entidades.players.tai.habilidades;

import entidades.players.Habilidade;
import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaFirme extends Habilidade {

	@Override
	public String getTitulo(){
		return "Postura firme";
	}

	@Override
	public String getDescricao(){
		return "Tai assume uma postura firme, habilitando um golpe a distancia e aumentando o equilibrio dele, o golpe tem 3 execuções, atordoar inimigos em área ou repelir/puxar inimigos a sua frente. ";
	}

	@Override
	public String getCusto(){
		return "Custa 10 de furia";
	}

	@Override
	public BufferedImage getIcone() {
		return sprite.getSprite(128 * 4,0,128,128);
	}

	@Override
	public MovimentoPlayer getMovimentoPlayer() {
		return MovimentoPlayer.HABILIDADE_POSTURA_FIRME;
	}

	@Override
	public boolean isBasica(){
		return false;
	}
}
