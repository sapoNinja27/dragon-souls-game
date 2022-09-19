package entidades.players.tai.habilidades;

import entidades.players.Habilidade;
import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaOfensiva extends Habilidade {

	@Override
	public String getTitulo(){
		return "Postura ofensiva";
	}

	@Override
	public String getDescricao(){
		return "Tai ataca rapidamente os inimigos a sua frente durante 3,5 segundos, causando 300% do dano de ataque por golpe. ";
	}

	@Override
	public String getCusto(){
		return "Recebe 40 de furia";
	}

	@Override
	public BufferedImage getIcone() {
		return sprite.getSprite(0,0,128,128);
	}

	@Override
	public MovimentoPlayer getMovimentoPlayer() {
		return MovimentoPlayer.HABILIDADE_POSTURA_OFENSIVA;
	}

	@Override
	public boolean isBasica(){
		return true;
	}
}
