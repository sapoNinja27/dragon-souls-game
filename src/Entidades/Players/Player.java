package Entidades.Players;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Entidades.Entidade;
import Entidades.Cenario.PosteLuz;
import Entidades.ObjetoLuminoso;
import Main.Game;
import Menu.Animacao;
import Menu.ImageUtils;
import Menu.MascaraHitBox;
import World.Camera;
import World.World;
import enums.*;

public class Player extends Entidade {
	protected int posicao = 0;
	protected boolean jaParou;

	private boolean dentro;
	private Animacao andar = new Animacao(0, 4, 12, 6);
	private Animacao parar = new Animacao(0, 4, 12, 6);
	private Animacao pular = new Animacao(0, 4, 12, 6);

	public int framesMoved = 0, indexMoved = 4, maxIndexMoved = 12;
	public int framesParan = 0, maxFramesParan = 15;
	public int framesParado = 0, maxFramesParado = 17, indexParado = 0, maxIndexParado = 4;
	public int framesPulo = 0, maxFramesPulo = 15, indexPul = 13, maxIndexPul = 15;
	public int framesCai = 0, maxFramesCai = 15, indexCai = 16, maxIndexCai = 17;
	public int framesCai2 = 0, maxFramesCai2 = 15;
	public int framesAtk = 0;
	public int maxFramesAtk = 5;
	public int framesFur = 0;
	public int indexAtk = 27;
	public int maxIndexAtk = 33;
	public int framesDash = 0, maxFramesDash = 11, indexDash = 19, maxIndexDash = 20;
	public int framesDashS = 0, maxFramesDashS2 = 15, maxFramesDashS = 4, indexDashS = 20, maxIndexDashS = 23;
	public boolean caindo, subindo, podepular, completou_pulo, saiu_do_chao, caiu_no_chao, atacando, dash, dashS,
			dashS2;
	public boolean combat;
	public boolean right, up, left, down, parado, parando;
	private Player playerUm;
	private DirecaoPlayer direcaoPlayer = DirecaoPlayer.DIREITA;
	public int index = 0;
	public int frames = 0;
	public boolean Hudvisivel;
	public double speed = 5;
	public boolean visivel;
	public boolean moved = false;
	public double vida, vidaAdicional;
	public int defesa, defesaAdicional, defesaMaxima;
	public int furia, maxFuria;
	public boolean h1 = false;
	protected boolean isPlayerDois;
	private boolean isFree;

	public Player(int x, int y) {
		super(x, y, 0, 0);

	}

	public void setPlayerUm(){
		this.isPlayerDois = false;
	}

	public void setPlayerDois(){
		this.isPlayerDois = true;
	}

	public void tick(){;
	}

	public void teleportar(int x, int y) {
		if(!isPlayerDois){
			setX(x);
			setY(y);
		} else {
			if(this.direcaoPlayer.equals(DirecaoPlayer.ESQUERDA)) {
				setX(x+20);
				setY(y);
			}
			if(this.direcaoPlayer.equals(DirecaoPlayer.DIREITA)) {
				setX(x-20);
				setY(y);
			}
		}
	}

	public void anim() {

		if (right) {
			direcaoPlayer = DirecaoPlayer.DIREITA;
		} else if (left) {
			direcaoPlayer = DirecaoPlayer.ESQUERDA;
		}
		if (h1) {
			index = index;
		} else if (!isFree && moved && !dash && !dashS) {
			index = indexMoved;
		} else if (caiu_no_chao) {
//			index = index;
		} else if (dash) {
			index = indexDash;
		} else if (dashS) {
			index = indexDashS;
		} else if (dashS2) {
			index = indexDashS;
		} else if (subindo) {
			index = indexPul;
		} else if (caindo) {
			index = indexCai;
		} else if (!isFree && moved && !dash && !dashS) {
			index = indexMoved;
		} else if (atacando) {
			index = indexAtk;
		} else if (parado) {
			if (combat) {
				index = indexParado + 24;
				frames++;
				if (frames >= 200) {
					frames = 0;
					combat = false;
				}
			} else {
				index = indexParado;
			}
		}

		if (dash) {
			parando = false;
			framesDash++;
			if (framesDash == maxFramesDash) {
				framesDash = 0;
				indexDash++;
				if (indexDash == maxIndexDash) {
					indexDash = 19;
					dash = false;
					if (!moved) {
						parando = true;
					}

				}
			}
		}
		if (dashS) {
			dash = false;
			indexDash = 19;
			framesDash = 0;
			framesDashS++;
			if (framesDashS == maxFramesDashS) {
				framesDashS = 0;
				indexDashS++;
				if (indexDashS == maxIndexDashS) {
					indexDashS = 23;
					dashS = false;
					dashS2 = true;
				}
			}
		}
		if (dashS2) {
			dash = false;
			framesDashS++;
			if (framesDashS == maxFramesDashS2) {
				framesDashS = 0;
				indexDashS = 19;
				dashS2 = false;
				parado = true;

			}
		}
		if (parando) {
			index = 12;
			framesParan++;
			if (direcaoPlayer.equals(DirecaoPlayer.ESQUERDA)) {
				setX(getX() - 1);
			} else {
				setX(getX() + 1);
			}
			if (framesParan == maxFramesParan) {
				framesParan = 0;
				parado = true;
				parando = false;
				if (isPlayerDois) {
					jaParou = true;
				}
			}
		}
	}

	void dash() {
		if (dash) {
			if (direcaoPlayer == DirecaoPlayer.DIREITA) {
				setX(getX() + 8);
			} else {
				setX(getX() - 8);
			}
		}
		if (dashS) {
			if (direcaoPlayer == DirecaoPlayer.DIREITA) {
				setX(getX() + 6);
			} else {
				setX(getX() - 6);
			}
		}
		if (dashS2) {
			if (direcaoPlayer == DirecaoPlayer.DIREITA) {
				setX(getX() + 4);
			} else {
				setX(getX() - 4);
			}
		}
	}

	public boolean getDentro() {
		return dentro;
	}

	public void setDentro(boolean dentro) {
		this.dentro = dentro;
	}



	public boolean isHudvisivel() {
		return Hudvisivel;
	}

	public void setHudvisivel(boolean hudvisivel) {
		Hudvisivel = hudvisivel;
	}

	public void updateCamera() {

		Camera.x = Camera.clamp(250 + this.getX() - (Configuracoes.WIDTH / 2), 0,
				World.WIDTH * Configuracoes.TILE_SIZE - Configuracoes.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Configuracoes.HEIGHT / 2) - 53, 0,
				World.HEIGHT * Configuracoes.TILE_SIZE - Configuracoes.HEIGHT);
	}


	protected void furiaSistem() {
//		furia =100;
		if (furia >= maxFuria) {

		} else {
			if (furia / 3 < 10) {
				if(defesa<10) {
					defesa ++;
				}else {
					defesa=10;
				}
			} else {
				defesa = furia / 3;
			}
		}
		if (parado && !combat && !atacando) {
			framesFur++;
			if (framesFur >= 10) {
				framesFur = 0;
				furia -= 1;
				if (furia <= 0) {
					furia = 0;
				}
			}

		}
	}

	public void lifesistem() {
		if (vida <= 0) {
			vida = 0;
			Configuracoes.estadoGame = TipoGame.MENU;
			Configuracoes.estadoMenu = TipoMenu.GAMEOVER;
		}
	}

	public void nBot() {
		movedX();
		depth = 6;
		speed = 6;
		if (atacando) {
			framesAtk++;
			if (framesAtk == maxFramesAtk) {
				framesAtk = 0;
				indexAtk++;
				if (indexAtk == maxIndexAtk) {
					indexAtk = 26;
					atacando = false;
					parado = true;
					combat = true;
				}
			}
		}
	}

	public void atualizarSombreamento(ObjetoLuminoso objetoLuminoso){
		if (distanciaX(objetoLuminoso.getX()) < 150 && distanciaY( objetoLuminoso.getY()) < 500) {
			int[] dist = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
			float[] opac = { 0.8f, 0.8f, 0.7f, 0.7f, 0.6f, 0.6f, 0.5f, 0.5f, 0.4f, 0.4f, 0.3f, 0.3f,
					0.2f, 0.2f, 0.1f, 0.1f };
			for (int c = 0; c < 15; c++) {
				if (Configuracoes.local == TipoAmbiente.RUA) {
					if (Configuracoes.dia) {
						sombras = 0.5f;
					} else {
						if ((int) distanciaX( objetoLuminoso.getX()) / 10 == dist[c]) {
							sombras = opac[c];
						}
					}
				} else if (Configuracoes.local == TipoAmbiente.ESGOTOS) {
					sombras = 0.0f;
				} else if (Configuracoes.local == TipoAmbiente.TELHADO) {
					if (Configuracoes.dia) {
						sombras = 0.0f;
					} else {
						sombras = 0.2f;
					}
				}
			}
		}
		if (distanciaX(objetoLuminoso.getX()) < 150 && distanciaY(objetoLuminoso.getY()) < 500) {
			//TODO descobrir oq a função faz e deixar ela bonita
			int[] dist = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			float[] opac = { 0.1f, 0.1f, 0.2f, 0.2f, 0.3f, 0.3f, 0.4f, 0.4f, 0.5f, 0.5f, 0.6f, 0.6f };
			for (int c = 0; c < 11; c++) {
				if (Configuracoes.local == TipoAmbiente.ESGOTOS) {
					sombreamento = 0.6f;
				}
				if (Configuracoes.local == TipoAmbiente.TELHADO) {
					if (Configuracoes.dia) {
						sombreamento = 0f;
					} else {
						sombreamento = 0.6f;
					}
				}
				if ((int) distanciaX(objetoLuminoso.getX()) / 10 == dist[c]) {
					if (Configuracoes.local == TipoAmbiente.RUA) {
						if (Configuracoes.dia) {
							sombreamento = 0f;
						} else {
							sombreamento = opac[c];
						}
					}
				}
			}
		}
	}

	public void sombrear(Graphics g, BufferedImage[] direcao) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, sombreamento));
		g.drawImage(ImageUtils.Sombra(direcao[index]), this.getX() + posicao - Camera.x, this.getY() - Camera.y, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	public void Sombras(Graphics g, BufferedImage[] direcao) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, sombras));
		if (!subindo && !caindo) {
			if (Configuracoes.dia) {
				g.drawImage(ImageUtils.inverterV(ImageUtils.Sombra(direcao[index])), this.getX() + posicao - Camera.x,
						this.getY() - Camera.y + Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE / 2, null);
			} else {
				g.drawImage(ImageUtils.inverterV(ImageUtils.Sombra(direcao[index])), this.getX() + posicao - Camera.x,
						this.getY() - Camera.y + Configuracoes.TILE_SIZE + 7, Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE / 2, null);
			}

		} else {
			g.drawImage(ImageUtils.Sombra(direcao[index]), this.getX() + posicao - Camera.x + 10,
					this.getY() - Camera.y + Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE,
					Configuracoes.TILE_SIZE / 2, null);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}

	public void movedY() {

		if (up && podepular) {
			subindo = true;
		} else {
			subindo = false;
			// cainimation=true;
		}
		if (isFree) {
			podepular = true;
		}

		if (subindo) {
			caiu_no_chao = false;
			saiu_do_chao = true;
			framesPulo++;
			if (framesPulo == maxFramesPulo) {
				framesPulo = 0;
				if (indexPul != maxIndexPul) {
					indexPul++;
				}
				if (indexPul == maxIndexPul) {
					subindo = false;
					podepular = false;
					completou_pulo = true;

				}
			}
			setY(getY() - 4);
		}
		if (down) {
			// cair da plat
		}

		if (isFree && !subindo) {
			caindo = true;
		} else {

			caindo = false;

		}
		if (isFree && saiu_do_chao) {
			caiu_no_chao = true;
		}

		if (caindo) {
			framesCai++;
			if (framesCai == maxFramesCai) {
				framesCai = 0;
				if (indexCai != maxIndexCai) {
					indexCai++;
				}
				if (indexCai == maxIndexCai) {
					caindo = false;
				}
			}
			setY(getY() + 4);
		}
		if (caiu_no_chao) {
			indexCai = 16;
			indexPul = 13;
			saiu_do_chao = false;
			index = 18;
			framesCai2++;
			if (framesCai2 == maxFramesCai2) {
				framesCai2 = 0;
				parado = true;
				caiu_no_chao = false;
			}
		}

	}


	public void setHitbox() {
		MascaraHitBox padrao = new MascaraHitBox("padrao", 20, 11, 20 ,52);
//		MascaraHitBox padrao = new MascaraHitBox("padrao", 20, 11, 20 ,52);
		adicionarMascara(padrao);
//		adicionarMascara(2, 11, 60, 40, 3);
		// ataques melle
//		if (dir == right_dir) {
//			if (atacando) {
//				if (indexAtk != 24) {
//					adicionarMascara(1, 40, 20, 30, 10);
//				}
//			} else {
//				adicionarMascara(1, 20, 20, 30, 10);
//			}
//		} else {
//			if (atacando) {
//				if (indexAtk != 24) {
//					adicionarMascara(1, -5, 20, 30, 10);
//				}
//			} else {
//				adicionarMascara(1, 20, 20, 30, 10);
//			}
//		}

	}

	public void setPlayer(Player player){
		this.playerUm = isPlayerDois ? player : null;
	}

	public void bot() {
		movedBot();
		depth = 5;
//		if(distanciaX(Game.player.getX(),Game.player2.getX())<100 && Game.player.up) {
//			up=true;
//			parado=false;
//		}else {
//			up=false;
//
//		}
		if (distanciaX(playerUm.getX()) < 20) {
			moved = false;
		}
		if (playerUm.moved) {
			if (distanciaX(playerUm.getX()) / 20 > 4) {
				speed = distanciaX(playerUm.getX()) / 25;
			}
		} else {
			if (distanciaX(playerUm.getX()) / 25 > 4) {
				speed = distanciaX(playerUm.getX()) / 30;
			}
		}

		if (playerUm.dash) {
			parado = false;
			dash = true;
			jaParou = false;
		} else {
			dash = false;
			if (!moved) {
				if (!jaParou) {
					parando = true;
				}
			}

		}
		if (playerUm.direcaoPlayer == DirecaoPlayer.ESQUERDA) {
			if (playerUm.getX() < getX()) {
				direcaoPlayer = DirecaoPlayer.ESQUERDA;
				if (playerUm.getX() < getX() && distanciaX(playerUm.getX()) > 70) {
					parado = false;
					left = true;
					right = false;
					moved = true;
					jaParou = false;
				} else {
					if (!jaParou) {
						left = false;
						moved = false;
						parando = true;
					}
				}
			} else {
				direcaoPlayer = DirecaoPlayer.DIREITA;
			}
		} else if (playerUm.direcaoPlayer == DirecaoPlayer.DIREITA) {
			if (playerUm.getX() > getX()) {
				direcaoPlayer = DirecaoPlayer.DIREITA;
				if (playerUm.getX() > getX() && distanciaX(playerUm.getX()) > 70) {
					parado = false;
					right = true;
					left = false;
					moved = true;
					jaParou = false;
				} else {
					if (!jaParou) {
						right = false;
						moved = false;
						parando = true;
					}
				}
			} else {
				direcaoPlayer = DirecaoPlayer.DIREITA;
			}

		}
		visivel = true;
	}

	public void movedX() {
		if (right) {
			moved = true;
			direcaoPlayer = DirecaoPlayer.DIREITA;
			correr(xDouble() + speed);
		}
		if (left) {
			moved = true;
			direcaoPlayer = DirecaoPlayer.ESQUERDA;
			correr(xDouble() - speed);
		}
		if (parado) {
			framesParado++;
			if (framesParado == maxFramesParado) {
				framesParado = 0;
				indexParado++;
				if (indexParado == maxIndexParado) {
					indexParado = 0;
				}
			}
		}
		if (moved) {
			framesMoved++;
			if (framesMoved >= 6) {
				framesMoved = 0;
				indexMoved++;
				if (indexMoved == maxIndexMoved) {
					indexMoved = 4;
				}

			}
		}
	}

	public void movedBot() {
		if (right && moved) {
			direcaoPlayer = DirecaoPlayer.DIREITA;
			correr(xDouble() + speed);
		}
		if (left && moved) {
			direcaoPlayer = DirecaoPlayer.ESQUERDA;
			correr(xDouble() - speed);
		}
		if (parado) {
			framesParado++;
			if (framesParado == maxFramesParado) {
				framesParado = 0;
				indexParado++;
				if (indexParado == maxIndexParado) {
					indexParado = 0;
				}
			}
		}
		if (moved) {
			framesMoved++;
			if (framesMoved >= 6) {
				framesMoved = 0;
				indexMoved++;
				if (indexMoved == maxIndexMoved) {
					indexMoved = 4;
				}

			}
		}
	}

	public void executarAcao(Boolean isFree, AcaoPlayer acaoPlayer){

		if (acaoPlayer.equals(AcaoPlayer.DIREITA)){
			atacando = false;
			combat = false;
			frames = 0;
			right = true;
			parado = false;
			parando = false;
			moved = true;
		}
		if (acaoPlayer.equals(AcaoPlayer.ESQUERDA)){
			left = true;
			parado = false;
			parando = false;
			moved = true;
			combat = false;
			frames = 0;
		}
		if (acaoPlayer.equals(AcaoPlayer.DASH)){
			parado = false;
			dash = true;
			combat = false;
			frames = 0;
		}
		if(acaoPlayer.equals(AcaoPlayer.CIMA)){
			parando = false;
			parado = false;
			caiu_no_chao = false;
			up = true;
		}
		if(acaoPlayer.equals(AcaoPlayer.ULTIMATE)){
			parado = false;
			h1 = true;
			combat = false;
			frames = 0;
		}
		if (acaoPlayer.equals(AcaoPlayer.SOCO_FRACO)){
			framesAtk = 0;
			parado = false;
			combat = false;
			frames = 0;
			if (dash) {
				dashS = true;
			}
			atacando = true;
		}

		if (acaoPlayer.equals(AcaoPlayer.PARAR)){
			moved = false;
			parando = true;
//            player.left = false;
//            player.moved = false;
//            if (!player.right) {
//                if (!player.caindo && !player.subindo) {
//                    player.parando = true;
//                }
//            }
		}

		if (acaoPlayer.equals(AcaoPlayer.PARAR_PULO)){
			up = false;
			podepular = false;
			moved = false;
		}
	}
}
