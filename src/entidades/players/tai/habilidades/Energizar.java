package entidades.players.tai.habilidades;

import entidades.players.Habilidade;
import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class Energizar extends Habilidade {

	@Override
	public String getTitulo(){
		return "Energizar";
	}

	@Override
	public String getDescricao(){
		return "Tai lança uma esfera eletrica a sua frente, causando 50 de dano ao contato e deixando inimigos energizados. Inimigos energizados causam 3 de dano e transmitem energizado a inimigos proximos durante 2 segundos. ";
	}

	@Override
	public String getCusto(){
		return "Custa 20 de furia";
	}

	@Override
	public BufferedImage getIcone() {
		return sprite.getSprite(128 * 3,0,128,128);
	}

	@Override
	public MovimentoPlayer getMovimentoPlayer() {
		return MovimentoPlayer.HABILIDADE_ENERGIZAR;
	}

	@Override
	public boolean isBasica(){
		return false;
	}
}
