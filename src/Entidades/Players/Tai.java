package Entidades.Players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Graficos.Spritesheet;
import Main.Game;
import World.Camera;

public class Tai extends Player {
	private BufferedImage[] rightTai;
	private Spritesheet spt;
	private BufferedImage[] leftTai;
	private BufferedImage[] direcao;
	private BufferedImage[] sombra;
	public int framesDash = 0, maxFramesDash = 11, indexDash = 19, maxIndexDash = 20;
	public int framesDashS = 0, maxFramesDashS2 = 15, maxFramesDashS = 4, indexDashS = 20, maxIndexDashS = 23;
	private int indexH1 = 36, framesH1 = 0;
	// TODO fazer anima��o de habilidades

	public Tai(int x, int y) {
		super(x, y);
		vida = 100;
		defesa = 10;
		defesaMaxima = 100;
		furia = 100;
		maxFuria = 100;
		spt = new Spritesheet("/personagens/tai.png");
		rightTai = new BufferedImage[50];
		leftTai = new BufferedImage[50];
		direcao = new BufferedImage[50];
		sombra = new BufferedImage[50];
		// respirando
		for (int i = 0; i < 4; i++) {
			rightTai[i] = spt.getSprite(Configuracoes.TILE_SIZE * i, Configuracoes.TILE_SIZE * 0,
					Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
		// correndo
		for (int i = 0; i < 9; i++) {
			rightTai[i + 4] = spt.getSprite(Configuracoes.TILE_SIZE * i, Configuracoes.TILE_SIZE * 1,
					Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
		// pulando
		for (int i = 0; i < 6; i++) {
			rightTai[i + 13] = spt.getSprite(Configuracoes.TILE_SIZE * i, Configuracoes.TILE_SIZE * 2,
					Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
		// dash
		for (int i = 0; i < 5; i++) {
			rightTai[i + 19] = spt.getSprite(Configuracoes.TILE_SIZE * i, Configuracoes.TILE_SIZE * 3,
					Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
//		//parado soco
		for (int i = 0; i < 4; i++) {
			rightTai[i + 24] = spt.getSprite(Configuracoes.TILE_SIZE * i, Configuracoes.TILE_SIZE * 4,
					Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
//		//socos
		for (int i = 0; i < 6; i++) {
			rightTai[i + 28] = spt.getSprite(Configuracoes.TILE_SIZE * i, Configuracoes.TILE_SIZE * 5,
					Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
//		//hb1
		for (int i = 0; i < 8; i++) {
			rightTai[i + 34] = spt.getSprite(Configuracoes.TILE_SIZE * i, Configuracoes.TILE_SIZE * 6,
					Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
//		//h2
//		for(int i =0; i < 4; i++){
//			rightTai[i+37] =   Game.tai.getSprite(Configuracoes.TILE_SIZE*i, Configuracoes.TILE_SIZE*7, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
//		}
//		//h3
//		for(int i =0; i < 4; i++){
//			rightTai[i+37] =   Game.tai.getSprite(Configuracoes.TILE_SIZE*i, Configuracoes.TILE_SIZE*8, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
//		}
		// ??

	}

	public void punchMeteor() {
		if (h1 && furia > 61) {
			defesa=0;
			speed = 0;
			index = indexH1;
			framesH1++;
			if (framesH1 >= 10) {
				framesH1 = 0;
				indexH1++;
				if (indexH1 > 41) {
					indexH1 = 36;
					furia -= 60;
					h1 = false;
					parado = true;
					combat = true;
				}
			}
		} else {
			h1 = false;
			parado = true;
		}
	}

	public void attsprite() {
		for (int i = 0; i < 35; i++) {
			sombra[i] = Sombra(direcao[i]);
		}
		if (atacando) {
			if (dir == left_dir) {
				if (indexAtk == 30 || indexAtk == 29 || indexAtk == 31) {
					posicao = -9;
				} else if (indexAtk == 28 || indexAtk == 32) {
					posicao = -1;
				} else {
					posicao = 0;
				}
				for (int i = 0; i < 35; i++) {
					direcao[i] = leftTai[i];
				}
			} else if (dir == right_dir) {
				if (indexAtk == 30 || indexAtk == 29 || indexAtk == 31) {
					posicao = +9;
				} else if (indexAtk == 28 || indexAtk == 32) {
					posicao = +1;
				} else {
					posicao = 0;
				}
				for (int i = 0; i < 35; i++) {
					direcao[i] = (rightTai[i]);
				}
			}
		} else {
			posicao = 0;
		}
		if (dir == left_dir) {
			for (int i = 0; i < 50; i++) {
				direcao[i] = leftTai[i];
			}
		} else if (dir == right_dir) {
			for (int i = 0; i < 50; i++) {
				direcao[i] = (rightTai[i]);
			}
		}
		if (leftTai[0] == null && leftTai[34] == null) {
			for (int i = 0; i < 50; i++) {
				leftTai[i] = inverter(rightTai[i]);
			}
		}

	}

	public void tick() {
		punchMeteor();
		attsprite();
		setHitbox();
		anim();
		dash();
		movedY();
		lifesistem();
		furiaSistem();
		checkCollisionEnemy();
		if (this == Game.player) {
			updateCamera();
			nBot();
			checkCollisionLifePack();
		} else {
			bot();
		}
	}

	public void render(Graphics g) {
		Sombras(g, direcao);
		g.drawImage(direcao[index], this.getX() + posicao - Camera.x + mov_das_cena, this.getY() - Camera.y, null);
		sombrear(g, direcao);
		g.setColor(Color.red);
		g.drawRect(getX() - Camera.x + mascaracaX[2], getY() - Camera.y + mascaraY[2], mascaraAltura[2], mascaraLargura[2]);
		g.setColor(Color.BLUE);
		g.drawRect(getX() - Camera.x + mascaracaX[1], getY() - Camera.y + mascaraY[1], mascaraAltura[1], mascaraLargura[1]);
	}
}
