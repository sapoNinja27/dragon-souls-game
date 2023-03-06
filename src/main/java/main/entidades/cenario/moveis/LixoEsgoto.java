package main.entidades.cenario.moveis;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.utils.Spritesheet;
import main.utils.ImageUtils;
import main.world.Camera;

public class LixoEsgoto extends ObjetosComMovimento{
	private BufferedImage img;
	public LixoEsgoto(int x, int y, Spritesheet spt) {
		super(x, y);
        Random random = new Random();
//		img=spt.getSprite(
//                Configuracao.TILE_SIZE+(32 * random.nextInt(2)),
//                6* Configuracao.TILE_SIZE+32 * random.nextInt(2),
//                32,
//                32);
	}
	public void tick() {
		x-=speed+1;
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(img,this.getX()-Camera.x+20+(20),this.getY()-Camera.y+10,32,32,null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.7f));
		g.drawImage(ImageUtils.sombreamento(img),this.getX()-Camera.x+20+(20),this.getY()-Camera.y+10,32,32,null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}
}