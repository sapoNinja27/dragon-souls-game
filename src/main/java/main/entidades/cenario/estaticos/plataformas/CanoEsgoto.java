package main.entidades.cenario.estaticos.plataformas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.DadosGame;
import main.entidades.Entidade;
import main.world.Camera;

public class CanoEsgoto extends Entidade {
	private int index;
	private int ciclo;
	private final BufferedImage[] img;
	private int frames = 0;
	private final int[] rand = new int[3];
	public CanoEsgoto(int x, int y, BufferedImage[] img) {
		super(x, y, 0, 0);
		this.img = img;
		depth = 3;
        Random random = new Random();
		for(int i=0;i<3;i++) {
			rand[i] = random.nextInt(300);
		}
//		adicionarMascara(new MascaraHitBox(17, -30, 32, 40));
	}

    @Override
	public void tick(DadosGame dadosGame) {
        frames++;
        if (index == 1) {
            if (frames >= rand[ciclo] + 50) {
                frames = 0;
                index = 0;
                ciclo++;
                if(ciclo>2) {
                    ciclo=0;
                }
            }
        }
        if (index == 0) {
            if (frames >= rand[ciclo] + 200) {
                frames = 0;
                index = 1;
                ciclo++;
                if(ciclo>2) {
                    ciclo=0;
                }
            }
        }
	}

    @Override
	public void render(Graphics g, DadosGame dadosGame) {
        g.drawImage(img[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        super.render(g, dadosGame);
	}
}