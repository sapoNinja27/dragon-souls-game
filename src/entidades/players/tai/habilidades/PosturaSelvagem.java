package entidades.players.tai.habilidades;

import entidades.players.Habilidade;
import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaSelvagem extends Habilidade {

	@Override
	public String getTitulo(){
		return "Postura selvagem";
	}

	@Override
	public String getDescricao(){
		return "Tai permite que seu lado maligno assuma o controle, recebendo novas habilidades, dano de ataque e roubo de vida, mas reduzindo muito a propria defesa, durante 30 segundos, ao final do efeito ele recebe uma penalidade de 15% de vida permanente. ";
	}

	@Override
	public String getCusto(){
		return "";
	}

	@Override
	public BufferedImage getIcone() {
		return sprite.getSprite(128 * 2,0,128,128);
	}

	@Override
	public MovimentoPlayer getMovimentoPlayer() {
		return MovimentoPlayer.HABILIDADE_POSTURA_SELVAGEM;
	}

	@Override
	public boolean isBasica(){
		return true;
	}
}
