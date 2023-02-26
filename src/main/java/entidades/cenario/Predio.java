package entidades.cenario;

import configuracoes.DadosGame;
import entidades.Entidade;
import entidades.cenario.limitescenario.ParedeInvisivel;
import entidades.mascaras.MascaraHitBox;
import graficos.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Predio extends Entidade {
    Porta porta;
    private boolean dentro;
    BufferedImage[] paredes = new BufferedImage[10];
    BufferedImage elevador;
    private Spritesheet predios;
    ParedeInvisivel paredeInvisivel;

    public Predio(int x, int y, int width, int height, Spritesheet predios) {
        super(x, y, width, height);
//		drawLimitX = this.getWidth() * Configuracao.TILE_SIZE + Configuracao.TILE_SIZE * 2 + 400;
//		drawLimitY = this.getHeight() * Configuracao.TILE_SIZE + Configuracao.TILE_SIZE;
//		this.predios = predios;
//		for (int i = 0; i < 4; i++) {
//			paredes[i] = predios.getSprite(0, (1 + i) * Configuracao.TILE_SIZE, Configuracao.TILE_SIZE,
//					Configuracao.TILE_SIZE);
//		}
//		elevador = predios.getSprite(Configuracao.TILE_SIZE, Configuracao.TILE_SIZE,
//				Configuracao.TILE_SIZE, Configuracao.TILE_SIZE);
//		paredes[5] = predios.getSprite(Configuracao.TILE_SIZE, (3) * Configuracao.TILE_SIZE,
//				Configuracao.TILE_SIZE, Configuracao.TILE_SIZE);
//		paredes[6] = predios.getSprite(Configuracao.TILE_SIZE, (2) * Configuracao.TILE_SIZE,
//				Configuracao.TILE_SIZE, Configuracao.TILE_SIZE);
    }

    public void setDentro(boolean dentro) {
        this.dentro = dentro;
    }

//	public void passarPorta(int id) {
//		if (id == this.id) {
//			if (dentro) {
//				dentro = false;
//				Game.player.setDentro(false);
//				portaFora.ativa = false;
//			} else {
//				dentro = true;
//				Game.player.setDentro(true);
//				portaFora.ativa = true;
//			}
//		}
//	}

    public void generateObjects(int id) {
//		BufferedImage[] p1 = {
//				predios.getSprite(0, 0, Configuracao.TILE_SIZE,
//						Configuracao.TILE_SIZE),
//				predios.getSprite(Configuracao.TILE_SIZE, 0, Configuracao.TILE_SIZE,
//						Configuracao.TILE_SIZE) };
//
//		porta = new Porta(getX() + 100, getY(), p1);
//		paredeInvisivel = new ParedeInvisivel(getX(), 0, width);
//		paredeInvisivel.setLarguraParede(10);
    }

    @Override
    public void tick(DadosGame dadosGame) {
        adicionarMascara(new MascaraHitBox(0, 0, 0, 0));
//        porta.tick();
        if (dentro) {
            paredeInvisivel.tick(dadosGame);
        }
    }

    private void desenharPredio(Graphics g) {
//		for (int i = 0; i < height; i++) {
//			g.drawImage(paredes[3], this.getX() - Camera.x + (Configuracao.TILE_SIZE * (width - 1)),
//					this.getY() - Camera.y - (Configuracao.TILE_SIZE * (i * 2)), Configuracao.TILE_SIZE,
//					Configuracao.TILE_SIZE, null);
//			g.drawImage(paredes[2], this.getX() - Camera.x + (Configuracao.TILE_SIZE * (width - 1)),
//					this.getY() - Camera.y - (Configuracao.TILE_SIZE * (i * 2 + 1)), Configuracao.TILE_SIZE,
//					Configuracao.TILE_SIZE, null);
//			g.drawImage(ImageUtils.inverter(paredes[3]), this.getX() - Camera.x,
//					this.getY() - Camera.y - (Configuracao.TILE_SIZE * (i * 2)), Configuracao.TILE_SIZE,
//					Configuracao.TILE_SIZE, null);
//			g.drawImage(ImageUtils.inverter(paredes[2]), this.getX() - Camera.x,
//					this.getY() - Camera.y - (Configuracao.TILE_SIZE * (i * 2 + 1)), Configuracao.TILE_SIZE,
//					Configuracao.TILE_SIZE, null);
//			for (int j = 0; j < width - 2; j++) {
//				g.drawImage(paredes[1],
//						this.getX() - Camera.x + (Configuracao.TILE_SIZE * j) + Configuracao.TILE_SIZE,
//						this.getY() - Camera.y - (Configuracao.TILE_SIZE * (i * 2)), Configuracao.TILE_SIZE,
//						Configuracao.TILE_SIZE, null);
//				g.drawImage(paredes[0],
//						this.getX() - Camera.x + (Configuracao.TILE_SIZE * j) + Configuracao.TILE_SIZE,
//						this.getY() - Camera.y - (Configuracao.TILE_SIZE * (i * 2 + 1)),
//						Configuracao.TILE_SIZE, Configuracao.TILE_SIZE, null);
//			}
//		}
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        g.setColor(Color.darkGray);
//		if (dentro) {
//            desenharPredio(g);
//            for (int j = 0; j < width - 2; j++) {
//                for (int i = 0; i < height + 3; i++) {
//                    g.drawImage(paredes[6],
//                            this.getX() - Camera.x + Configuracao.TILE_SIZE + (j * Configuracao.TILE_SIZE),
//                            this.getY() - Camera.y - (i * Configuracao.TILE_SIZE), Configuracao.TILE_SIZE,
//                            Configuracao.TILE_SIZE, null);
//                }
//            }
//            for (int i = 0; i < height + 3; i++) {
//                g.drawImage(paredes[6], this.getX() - Camera.x + ((width - 1) * Configuracao.TILE_SIZE) - 13,
//                        this.getY() - Camera.y - Configuracao.TILE_SIZE - (i * Configuracao.TILE_SIZE),
//                        Configuracao.TILE_SIZE, Configuracao.TILE_SIZE, null);
//                g.drawImage(paredes[6], this.getX() - Camera.x + 13,
//                        this.getY() - Camera.y - Configuracao.TILE_SIZE - (i * Configuracao.TILE_SIZE),
//                        Configuracao.TILE_SIZE, Configuracao.TILE_SIZE, null);
//            }
//            g.drawImage(paredes[5], this.getX() - Camera.x + 10, this.getY() - Camera.y, Configuracao.TILE_SIZE,
//                    Configuracao.TILE_SIZE, null);
//            g.drawImage(ImageUtils.inverter(paredes[5]),
//                    this.getX() - Camera.x - 10 + Configuracao.TILE_SIZE * (width - 1), this.getY() - Camera.y,
//                    Configuracao.TILE_SIZE, Configuracao.TILE_SIZE, null);
//            porta.render(g);
//			g.drawImage(elevador, this.getX() - Camera.x + Configuracao.TILE_SIZE * (width - 3),
//					this.getY() - Camera.y - Configuracao.TILE_SIZE + 5, Configuracao.TILE_SIZE * 2,
//					Configuracao.TILE_SIZE * 2, null);
//			paredeInvisivel.render(g);
//		} else {
//            desenharPredio(g);
//            porta.render(g);
//		}
    }
}
