package Entidades.Cenario;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Entidades.Entity;
import Main.Game;
import World.Camera;
import World.Tile;
import enums.TipoAmbiente;

public class Porta extends Entity {
	public boolean emFrente;
	private int frames = 0;
	private float op = 0.f;
	private BufferedImage[] porta1;
	private Color cor;
	private int tipo;
	private int id;
	private boolean dentro;
	private BufferedImage fund[] = new BufferedImage[2];

	public Porta(int id, int x, int y, Color cor, int tipo) {
		super(x, y, 0, 0);
		this.id=id;
		porta1 = new BufferedImage[2];
		setCor(cor);
		this.tipo = tipo;
	}

	private void setCor(Color cor) {
		for (int i = 0; i < 2; i++) {
			porta1[i] = Tile.colorir(Game.cenario.getSprite((i + 2) * Configuracoes.TILE_SIZE,
					(2) * Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE), cor);
		}
		for (int i = 0; i < 2; i++) {
			fund[i] = Tile.colorir(Game.cenario.getSprite((i + 4) * Configuracoes.TILE_SIZE,
					(0) * Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE), cor);
		}
	}

	public void tick() {
		checkCollisionPorta();
		depth = 1;
		if (emFrente) {
			frames++;
			if (frames >= 10) {
				if (op < 0.9f) {
					op += 0.1f;
				}
			}

		} else {
			frames = 0;
			op = 0.1f;
		}
		setMask(0, 0 - 25, -20, 46, 80);
	}

	public void checkCollisionPorta() {
		if (Entity.isColidding(Game.player, this, 0, 0)) {
			emFrente = true;
			if (Game.clicked) {
				Game.clicked = false;
				for (int i = 0; i < Game.predios.size(); i++) {
					Predio atual = Game.predios.get(i);
					if (atual.getId() == id) {
						atual.passarPorta(id);
					}
				}
				if(dentro) {
					dentro=false;
				}else {
					dentro = true;
				}
			}
		} else {
			emFrente = false;
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (!dentro) {
			if (!emFrente) {
				g.drawImage(porta1[0], this.getX() - Camera.x - Configuracoes.TILE_SIZE,
						this.getY() - Camera.y - Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE * 2,
						Configuracoes.TILE_SIZE * 2, null);
			} else {
				g.drawImage(porta1[1], this.getX() - Camera.x - Configuracoes.TILE_SIZE,
						this.getY() - Camera.y - Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE * 2,
						Configuracoes.TILE_SIZE * 2, null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
				g.setFont(new Font("Cambria Math", Font.ROMAN_BASELINE, 20));
				if (cor == Color.white) {
					g.setColor(Color.blue);
				} else {
					g.setColor(Color.blue);
				}
				g.drawString("Entrar", this.getX() - Camera.x - 35, this.getY() - Camera.y - 30);
//				g.drawLine(100+190, 150, 100+350, 150);
//				g.drawLine(100+160, 125, 100+310, 125);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
		}

	}

}