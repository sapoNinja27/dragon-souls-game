//package Entidades.Cenario;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//
//import Configuration.Configuracoes;
//import Entidades.Entidade;
//import Graficos.Spritesheet;
//import Main.Game;
//import World.Camera;
//
//public class Predio extends Entidade {
//	Porta porta;
//	PortaFora portaFora;
//	private int distanciaX, distanciaY;
//	BufferedImage paredes[] = new BufferedImage[10];
//	BufferedImage fundoRua;
//	BufferedImage elevador;
//	private Spritesheet predios;
//	ParedeInvisivel par;
//	private int id;
//	private boolean dentro;
//
//	public Predio(int id, int x, int y, int width, int height, Spritesheet predios) {
//		super(x, y, width, height);
//		this.id = id;
//		this.width = width;
//		this.distanciaX = this.getWidth() * Configuracoes.TILE_SIZE + Configuracoes.TILE_SIZE * 2 + 27;
//		this.distanciaY = this.getHeight() * Configuracoes.TILE_SIZE + Configuracoes.TILE_SIZE;
//		this.predios = predios;
//		for (int i = 0; i < 4; i++) {
//			paredes[i] = predios.getSprite(0, (1 + i) * Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE,
//					Configuracoes.TILE_SIZE);
//		}
//		elevador = predios.getSprite(1 * Configuracoes.TILE_SIZE, (1) * Configuracoes.TILE_SIZE,
//				Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
//		paredes[5] = predios.getSprite(1 * Configuracoes.TILE_SIZE, (3) * Configuracoes.TILE_SIZE,
//				Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
//		paredes[6] = predios.getSprite(1 * Configuracoes.TILE_SIZE, (2) * Configuracoes.TILE_SIZE,
//				Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
//	}
//
//	public int getId() {
//		return id;
//	}
//
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
//
//	public void generateObjects(int id) {
//		BufferedImage[] p1 = {
//				predios.getSprite(0, 0, Configuracoes.TILE_SIZE,
//						Configuracoes.TILE_SIZE),
//				predios.getSprite(Configuracoes.TILE_SIZE, 0, Configuracoes.TILE_SIZE,
//						Configuracoes.TILE_SIZE) };
//
//		porta = new Porta(id, getX() + 100, getY(), p1);
//		portaFora = new PortaFora(id, getX() + 100, getY(), p1);
//		portaFora.ativa = false;
//		par = new ParedeInvisivel(getX(), 0, width);
//		par.setLarguraParede(10);
//		Game.entities.add(portaFora);
//	}
//
//	public void tick() {
//		porta.tick();
//		if (dentro) {
//			par.tick();
//		}
//	}
//
//	public void render(Graphics g) {
//		this.distanciaX = this.getWidth() * Configuracoes.TILE_SIZE + Configuracoes.TILE_SIZE * 2 + 27;
//		if (dentro) {
//			if (this.distanciaX((int) x, Game.player.getX()) < distanciaX
//					&& this.distanciaX((int) y, Game.player.getY()) < distanciaY) {
//				g.setColor(Color.darkGray);
//				for (int i = 0; i < height; i++) {
//					g.drawImage(paredes[3], this.getX() - Camera.x + (Configuracoes.TILE_SIZE * (width - 1)),
//							this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2)), Configuracoes.TILE_SIZE,
//							Configuracoes.TILE_SIZE, null);
//					g.drawImage(paredes[2], this.getX() - Camera.x + (Configuracoes.TILE_SIZE * (width - 1)),
//							this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2 + 1)), Configuracoes.TILE_SIZE,
//							Configuracoes.TILE_SIZE, null);
//					g.drawImage(Entidade.inverter(paredes[3]), this.getX() - Camera.x,
//							this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2)), Configuracoes.TILE_SIZE,
//							Configuracoes.TILE_SIZE, null);
//					g.drawImage(Entidade.inverter(paredes[2]), this.getX() - Camera.x,
//							this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2 + 1)), Configuracoes.TILE_SIZE,
//							Configuracoes.TILE_SIZE, null);
//					for (int j = 0; j < width - 2; j++) {
//						g.drawImage(paredes[1],
//								this.getX() - Camera.x + (Configuracoes.TILE_SIZE * j) + Configuracoes.TILE_SIZE,
//								this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2)), Configuracoes.TILE_SIZE,
//								Configuracoes.TILE_SIZE, null);
//						g.drawImage(paredes[0],
//								this.getX() - Camera.x + (Configuracoes.TILE_SIZE * j) + Configuracoes.TILE_SIZE,
//								this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2 + 1)),
//								Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
//					}
//				}
//				for (int j = 0; j < width - 2; j++) {
//					for (int i = 0; i < height + 3; i++) {
//						g.drawImage(paredes[6],
//								this.getX() - Camera.x + Configuracoes.TILE_SIZE + (j * Configuracoes.TILE_SIZE),
//								this.getY() - Camera.y - (i * Configuracoes.TILE_SIZE), Configuracoes.TILE_SIZE,
//								Configuracoes.TILE_SIZE, null);
//					}
//				}
//				for (int i = 0; i < height + 3; i++) {
//					g.drawImage(paredes[6], this.getX() - Camera.x + ((width - 1) * Configuracoes.TILE_SIZE) - 13,
//							this.getY() - Camera.y - Configuracoes.TILE_SIZE - (i * Configuracoes.TILE_SIZE),
//							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
//					g.drawImage(paredes[6], this.getX() - Camera.x + 13,
//							this.getY() - Camera.y - Configuracoes.TILE_SIZE - (i * Configuracoes.TILE_SIZE),
//							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
//				}
//				g.drawImage(paredes[5], this.getX() - Camera.x + 10, this.getY() - Camera.y, Configuracoes.TILE_SIZE,
//						Configuracoes.TILE_SIZE, null);
//				g.drawImage(Entidade.inverter(paredes[5]),
//						this.getX() - Camera.x - 10 + Configuracoes.TILE_SIZE * (width - 1), this.getY() - Camera.y,
//						Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
//				porta.render(g);
//			}
//			g.drawImage(elevador, this.getX() - Camera.x + Configuracoes.TILE_SIZE * (width - 3),
//					this.getY() - Camera.y - Configuracoes.TILE_SIZE + 5, Configuracoes.TILE_SIZE * 2,
//					Configuracoes.TILE_SIZE * 2, null);
//			par.render(g);
//		} else if (!Game.player.getDentro()) {
//			if (this.distanciaX((int) x, Game.player.getX()) < distanciaX
//					&& this.distanciaX((int) y, Game.player.getY()) < distanciaY) {
//				g.setColor(Color.darkGray);
//				for (int i = 0; i < height; i++) {
//					g.drawImage(paredes[3], this.getX() - Camera.x + (Configuracoes.TILE_SIZE * (width - 1)),
//							this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2)), Configuracoes.TILE_SIZE,
//							Configuracoes.TILE_SIZE, null);
//					g.drawImage(paredes[2], this.getX() - Camera.x + (Configuracoes.TILE_SIZE * (width - 1)),
//							this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2 + 1)), Configuracoes.TILE_SIZE,
//							Configuracoes.TILE_SIZE, null);
//					g.drawImage(Entidade.inverter(paredes[3]), this.getX() - Camera.x,
//							this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2)), Configuracoes.TILE_SIZE,
//							Configuracoes.TILE_SIZE, null);
//					g.drawImage(Entidade.inverter(paredes[2]), this.getX() - Camera.x,
//							this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2 + 1)), Configuracoes.TILE_SIZE,
//							Configuracoes.TILE_SIZE, null);
//					for (int j = 0; j < width - 2; j++) {
//						g.drawImage(paredes[1],
//								this.getX() - Camera.x + (Configuracoes.TILE_SIZE * j) + Configuracoes.TILE_SIZE,
//								this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2)), Configuracoes.TILE_SIZE,
//								Configuracoes.TILE_SIZE, null);
//						g.drawImage(paredes[0],
//								this.getX() - Camera.x + (Configuracoes.TILE_SIZE * j) + Configuracoes.TILE_SIZE,
//								this.getY() - Camera.y - (Configuracoes.TILE_SIZE * (i * 2 + 1)),
//								Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
//					}
//				}
//				porta.render(g);
//			}
//		}
//	}
//}
