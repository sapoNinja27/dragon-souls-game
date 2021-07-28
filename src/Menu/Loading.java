package Menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Entidades.Entity;
import Entidades.Cenario.PosteLuz;
import Graficos.Spritesheet;
import Main.Game;
import World.Camera;
import World.World;
import enums.TipoAmbiente;
import enums.TipoGame;
import enums.TipoMenu;

public class Loading {
	private boolean iniciando;
	private boolean carregando;
	private boolean finalizando;
	private BufferedImage[] pixels=new BufferedImage[7];
	private BufferedImage fundo;
	private Spritesheet loading=new Spritesheet("/loading.png");
	private int frames=0,index=0;
	private float op=1f;
	public  Loading() {
		fundo=loading.getSprite(0, 0, 928, 536);
		for(int i=0;i<pixels.length;i++) {
			pixels[i]=loading.getSprite( i*Configuracoes.TILE_SIZE,535, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
	}
	public void tick() {
		if(iniciando) {
			frames++;
			if(frames>10) {
				index++;
				frames=0;
				if(index==3) {
					index=0;
					iniciando=false;
					carregando=true;
				}
			}
		}
		if(carregando) {
			frames++;
			if(frames>10) {
				index++;
				frames=0;
				if(index==3) {
					index=0;
				}
			}
		}
		if(finalizando) {
			frames++;
			if(frames>10) {
				index++;
				frames=0;
				if(index==7) {
					index=0;
					carregando=false;
					finalizando=false;
					Game.isLoading=false;
				}
			}
		}
	}
	public void startWorld() {
		iniciando=true;
	}
	public void finishStartGame() {
		stop();
	}
	public void start() {
		iniciando=true;
	}

	public void stop() {
		finalizando=true;
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(carregando || iniciando) {
//			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
//
//			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g.setColor(Color.black);
			g.fillRect(0, 0, 1500, 1000);
			g.drawImage(fundo, 300, 100, null);
			g.setFont(new Font("",Font.LAYOUT_LEFT_TO_RIGHT,20));
			g.drawString("O verdadeiro nome de Tai é Nathaniel", 350, 180);
			g.drawString("Haine.", 350, 210);
			g.drawString("A comida favorita de Tai é frango cozido", 820, 350);
			g.drawString("com bacon, cogumelos e vinho, um prato", 820, 380);
			g.drawString("tipico da culinaria francesa.", 820, 410);
			g.drawImage(Entity.inverter(pixels[index]), 95, 535,Configuracoes.TILE_SIZE*2,Configuracoes.TILE_SIZE*2, null);
		}
	}
	
}	