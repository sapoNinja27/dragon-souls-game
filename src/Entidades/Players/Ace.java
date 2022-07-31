package Entidades.Players;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Menu.ImageUtils;
import java.util.List;

import Configuration.Configuracoes;
import Graficos.Spritesheet;
import World.Camera;
import enums.DirecaoPlayer;

import static Menu.ImageUtils.inverter;

public class Ace extends Player{
	public int framesDash = 0,maxFramesDash = 5,indexDash = 19,maxIndexDash = 24;
	public int framesDashS = 0,maxFramesDashS2 = 15,maxFramesDashS = 4,indexDashS = 20,maxIndexDashS = 23;
	private int nivelFoco = 0, maxNivelFoco = 4;
	private List<BufferedImage> cacheEfeitos = new ArrayList<>();

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
//		//dash
//		for(int i =0; i < 5; i++){
//			rightAce[i+19] =   spt.getSprite(Configuracoes.TILE_SIZE*i, Configuracoes.TILE_SIZE*3, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
//		}
////		//parado soco
//		for(int i =0; i < 4; i++){
//			rightAce[i+24] =   spt.getSprite(Configuracoes.TILE_SIZE*i, Configuracoes.TILE_SIZE*4, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
//		}
////		//socos
//		for(int i =0; i < 6; i++){
//			rightAce[i+28] =   spt.getSprite(Configuracoes.TILE_SIZE*i, Configuracoes.TILE_SIZE*5, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
//		}
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
		//TODO meter um cachezão de sombra
		if(nivelFoco > 2){
			if(nivelFoco == maxNivelFoco){
				color = (new Color(239, 239, 29));
			} else {
				color = (new Color(218, 218, 185));
			}
			Graphics2D g2 = (Graphics2D) g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
			g.drawImage(
					ImageUtils.sombreamentoColorido(spriteAtual().get(index), color),
					this.getX() + posicao - Camera.x - nivelFoco,
					this.getY() - Camera.y - nivelFoco,
					Configuracoes.TILE_SIZE + (nivelFoco * 2),
					Configuracoes.TILE_SIZE + (nivelFoco * 2),
					null
			);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		super.render(g);
	}
	public void attsprite(){
		if(atacando) {
			if(direcaoPlayer.equals(DirecaoPlayer.ESQUERDA)) {
//				if( indexAtk== 29 || indexAtk == 30 ) {
//					posicao =-7;
//				}else if(indexAtk==31 ) {
//					posicao =-4;
//				}else {
//					posicao =0;
//				}
			}else if((direcaoPlayer.equals(DirecaoPlayer.DIREITA))) {
//				if( indexAtk== 29 || indexAtk == 30 ) {
//					posicao =8;
//				}else if(indexAtk==31 ) {
//					posicao =2;
//				}else {
//					posicao =0;
//				}
			}
		}else {
			posicao = 0;
		}
	}

	public void anim() {
//		if(isFreeX && moved && !dash && !dashS) {
//			index=indexMoved;
//		}else if(caiu_no_chao) {
//			index=index;
//		}else if(dash) {
//			index=indexDash;
//		}else if(dashS) {
//			index=indexDashS;
//		}else if(dashS2) {
//			index=indexDashS;
//		}else if(subindo) {
//			index=indexPul;
//		}else if(caindo) {
//			index=indexCai;
//		}else if(moved){
//			index=indexMoved;
//		}else if(atacando ) {
//			index=indexAtk;
//		}else if(parado) {
//			if(combat) {
//				index=indexParado+24;
//				frames++;
//				if(frames>=200) {
//					frames=0;
//					combat=false;
//				}
//			}else {
//				index=indexParado;
//			}
//		}
//
//		if(dash) {
//			parando=false;
//			framesDash++;
//			if(framesDash == maxFramesDash) {
//				framesDash = 0;
//				indexDash++;
//				if(indexDash == maxIndexDash) {
//					framesDash++;
//					indexDash = 19;
//					dash=false;
//					if(!moved) {
//						parando=true;
//					}
//
//				}
//			}
//		}
//		if(dashS) {
//			dash=false;
//			indexDash = 19;
//			framesDash = 0;
//			framesDashS++;
//			if(framesDashS == maxFramesDashS) {
//				framesDashS = 0;
//				indexDashS++;
//				if(indexDashS == maxIndexDashS) {
//					indexDashS = 23;
//					dashS=false;
//					dashS2=true;
//				}
//			}
//		}
//		if(dashS2) {
//			dash=false;
//			framesDashS++;
//			if(framesDashS == maxFramesDashS2) {
//				framesDashS = 0;
//				indexDashS=19;
//					dashS2=false;
//					parado=true;
//
//				}
//		}
//		if(parando ) {
//			index=12;
//			framesParan++;
//			if(direcaoPlayer.equals(DirecaoPlayer.ESQUERDA)) {
//				setX(getX()-1);
//			}else {
//				setX(getX()+1);
//			}
//			if(framesParan == maxFramesParan) {
//				framesParan = 0;
//				parado=true;
//				parando=false;
//				if(isPlayerDois) {
//					jaParou=true;
//				}
//			}
//		}
	}

//	void dash() {
//		if(dash) {
//			if(direcaoPlayer.equals(DirecaoPlayer.DIREITA)) {
//				setX(getX() + 15);
//			}else {
//				setX(getX() - 15);
//			}
//		}
//		if(dashS) {
//				if(direcaoPlayer.equals(DirecaoPlayer.DIREITA)) {
//					setX(getX() + 6);
//				}else {
//					setX(getX() - 6);
//				}
//			}
//			if(dashS2) {
//				if(direcaoPlayer.equals(DirecaoPlayer.DIREITA)) {
//					setX(getX() + 4);
//				}else {
//					setX(getX() - 4);
//				}
//			}
//	}

}
