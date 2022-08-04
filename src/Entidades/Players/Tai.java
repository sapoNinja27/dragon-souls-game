package Entidades.Players;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Configuration.Configuracoes;
import Graficos.Spritesheet;

import static Menu.ImageUtils.inverter;

public class Tai extends Player {

	public Tai(int x, int y) {
		super(x, y);
	}
	/*
	 * define os sprites e animações do player
	 * */
	@Override
	public void atualizarSprites() {
		List<BufferedImage> respirando = new ArrayList<>();
		List<BufferedImage> correndo = new ArrayList<>();
		List<BufferedImage> pulando = new ArrayList<>();
		spritesheet = new Spritesheet("/personagens/tai.png");
		for (int i = 0; i < 35; i++) {
			if (respirando.size() < 4) {
				respirando.add(spritesheet.getSprite(
						Configuracoes.TILE_SIZE * i,
						0,
						Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE)
				);
			}
			if (correndo.size() < 9) {
				correndo.add(spritesheet.getSprite(
						Configuracoes.TILE_SIZE * i,
						Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE)
				);
			}
			if (pulando.size() < 6) {
				pulando.add(spritesheet.getSprite(
						Configuracoes.TILE_SIZE * i,
						Configuracoes.TILE_SIZE * 2,
						Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE)
				);
			}
		}
		spritesDireita.addAll(respirando);
		spritesDireita.addAll(correndo);
		spritesDireita.addAll(pulando);

		spritesEsquerda.addAll(inverter(spritesDireita));
		super.atualizarSprites();
	}
//	public void punchMeteor() {
//		if (furia > 61) {
//			defesa=0;
//			speed = 0;
//			index = indexH1;
//			framesH1++;
//			if (framesH1 >= 10) {
//				framesH1 = 0;
//				indexH1++;
//				if (indexH1 > 41) {
//					indexH1 = 36;
////					furia -= 60;
//					parado = true;
//				}
//			}
//		} else {
//			parado = true;
//		}
//	}
	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
//		Sombras(g, direcao);
//		g.drawImage(direcao[index], this.getX() + posicao - Camera.x, this.getY() - Camera.y, null);
//		sombrear(g, direcao);
//		g.setColor(Color.red);
////		g.drawRect(
////				this.getX() - Camera.x + mascaras.get(0).getPosicaoX(),
////				this.getY() - Camera.y + mascaras.get(0).getPosicaoY(),
////				mascaras.get(0).getAutura(),
////				mascaras.get(0).getLargura());
//		g.setColor(Color.BLUE);
	}
}
