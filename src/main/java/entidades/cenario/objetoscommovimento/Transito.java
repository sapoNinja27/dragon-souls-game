package entidades.cenario.objetoscommovimento;

import enums.TipoAmbiente;
import graficos.Spritesheet;
import utils.ImageUtils;
import world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Transito extends ObjetosComMovimento{
	private BufferedImage img;
	public Transito(int x, int y, Spritesheet spt) {
		super(x, y);
        Random random = new Random();
//		img = ImageUtils.inverter(
//                spt.getSprite(
//                    (random.nextInt(2)) * Configuracao.TILE_SIZE,
//                    (7 + random.nextInt(2)) * Configuracao.TILE_SIZE,
//                    Configuracao.TILE_SIZE,
//                    Configuracao.TILE_SIZE
//                )
//        );
	}
	public void tick() {
		x+=speed+10;
	}

	public void render(Graphics g) {
//		Graphics2D g2 = (Graphics2D) g;
////		g.setColor(Color.red);
////		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
//		if(Configuracao.local==TipoAmbiente.RUA) {
//			if(Configuracao.dia) {
//				g.drawImage(img,this.getX()-Camera.x+20+(20),this.getY()-Camera.y-100, Configuracao.TILE_SIZE*3, Configuracao.TILE_SIZE*3,null);
//			}else {
//				g.drawImage(img,this.getX()-Camera.x+20+(20),this.getY()-Camera.y-100, Configuracao.TILE_SIZE*3, Configuracao.TILE_SIZE*3,null);
//				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
//				g.drawImage(ImageUtils.sombreamento(img),this.getX()-Camera.x+20+(20),this.getY()-Camera.y-100, Configuracao.TILE_SIZE*3, Configuracao.TILE_SIZE*3,null);
//				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
//				g.setColor(Color.yellow);
//				g.fillPolygon(new int[]{
//						this.getX()-Camera.x- Configuracao.TILE_SIZE+3+300,
//						this.getX()-Camera.x- Configuracao.TILE_SIZE+5+295,
//						this.getX()-Camera.x- Configuracao.TILE_SIZE+8+300,
//
//						this.getX()-Camera.x- Configuracao.TILE_SIZE+40+500,
//						this.getX()-Camera.x- Configuracao.TILE_SIZE+40+500,
//						this.getX()-Camera.x- Configuracao.TILE_SIZE-40+500+70}
//						,new int[]{
//						this.getY()-Camera.y- Configuracao.TILE_SIZE*3+220,
//						this.getY()-Camera.y- Configuracao.TILE_SIZE*3+230,
//						this.getY()-Camera.y- Configuracao.TILE_SIZE*3+240,
//
//						this.getY()-Camera.y- Configuracao.TILE_SIZE*3+260,
//						this.getY()-Camera.y- Configuracao.TILE_SIZE*3+230,
//						this.getY()-Camera.y- Configuracao.TILE_SIZE*3+200},
//						6);
//				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//			}
//		}
	}
}