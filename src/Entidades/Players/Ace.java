package Entidades.Players;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Menu.ImageUtils;
import java.util.List;

import Configuration.Configuracoes;
import Graficos.Spritesheet;
import World.Camera;

import static Menu.ImageUtils.inverter;

public class Ace extends Player{
	public int framesDash = 0,maxFramesDash = 5,indexDash = 19,maxIndexDash = 24;
	public int framesDashS = 0,maxFramesDashS2 = 15,maxFramesDashS = 4,indexDashS = 20,maxIndexDashS = 23;
	private int nivelFoco = 0, maxNivelFoco = 4;

	public Ace(int x, int y) {
		super(x, y);
	}

	public int getNivelFoco() {
		return nivelFoco;
	}

	public int getMaxNivelFoco() {
		return maxNivelFoco;
	}

	/*
	* define os sprites e animações do player
	* */
	@Override
	public void atualizarSprites(){
		List<BufferedImage> respirando = new ArrayList<>();
		List<BufferedImage> correndo = new ArrayList<>();
		List<BufferedImage> pulando = new ArrayList<>();
		spritesheet = new Spritesheet("/personagens/ace.png");
		for(int i = 0; i < 35; i++){
			if(respirando.size() < 4){
				respirando.add(spritesheet.getSprite(
						Configuracoes.TILE_SIZE * i,
						0,
						Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE)
				);
			}
			if(correndo.size() < 9){
				correndo.add(spritesheet.getSprite(
						Configuracoes.TILE_SIZE * i,
						Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE)
				);
			}
			if(pulando.size() < 6){
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
	@Override
	public void tick() {
		super.tick();
		framesDash++;
		if(framesDash >= 30){
			nivelFoco ++;
			framesDash = 0;
			if(nivelFoco > maxNivelFoco){
				nivelFoco = 0;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		Color color;
		int opacidade = sombras;
		if(opacidade < 80){
			opacidade = 80;
		}
		if(nivelFoco > 2){
			if(nivelFoco == maxNivelFoco){
				color = (new Color(239, 239, 29, opacidade));
			} else {
				color = (new Color(218, 218, 185, opacidade));
			}
			g.drawImage(
					ImageUtils.sombreamento(spriteAtual().get(index), color),
					this.getX() + posicao - Camera.x - nivelFoco,
					this.getY() - Camera.y - nivelFoco,
					Configuracoes.TILE_SIZE + (nivelFoco * 2),
					Configuracoes.TILE_SIZE + (nivelFoco * 2),
					null
			);
		}
		super.render(g);
	}
}
