package Entidades.Players.tai.habilidades;

import Entidades.Players.Habilidade;
import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;
import java.util.Collections;

public class Atacar extends Habilidade {

	public Atacar(){
		montar(
				Collections.singletonList(sprite.getSprite(0,0,128,128)),
				"Atacar",
				"Tai desfere rapidamente diversos golpes em sua frente",
				MovimentoPlayer.ANDANDO
		);
	}
}
