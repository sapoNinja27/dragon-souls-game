package main.entidades.cenario.estaticos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.DadosGame;
import main.entidades.Entidade;
import main.world.Camera;

public class CanoEsgoto extends Plataforma {
    private int index;
    private int ciclo;
    private final BufferedImage[] img;
    private int frames = 0;
    private final int[] rand = new int[3];

    public CanoEsgoto(int x, int y, BufferedImage[] img) {
        super(x, y, null, 3);
        this.img = img;
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            rand[i] = random.nextInt(300);
        }
//		adicionarMascara(new MascaraHitBox(17, -30, 32, 40));
    }

    @Override
    public void tick(DadosGame dadosGame) {
        super.tick(dadosGame);
        frames++;
        if (index == 1) {
            if (frames >= rand[ciclo] + 50) {
                frames = 0;
                index = 0;
                ciclo++;
                if (ciclo > 2) {
                    ciclo = 0;
                }
            }
        }
        if (index == 0) {
            if (frames >= rand[ciclo] + 200) {
                frames = 0;
                index = 1;
                ciclo++;
                if (ciclo > 2) {
                    ciclo = 0;
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