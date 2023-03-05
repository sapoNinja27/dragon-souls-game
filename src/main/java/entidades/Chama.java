package entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.DadosGame;
import main.menu.graficos.Spritesheet;

public class Chama extends Entidade {
    private BufferedImage sprite[];
    private int frames = 0, index = 0, maxIndex = 15;
    private Spritesheet chama = new Spritesheet("/fogo.png");

    public Chama(int x, int y) {
        super(x, y, 0, 0);
        sprite = new BufferedImage[16];

        for (int i = 0; i < 4; i++) {
//			sprite[i]=chama.getSprite((i)* Configuracao.TILE_SIZE,(0)* Configuracao.TILE_SIZE, Configuracao.TILE_SIZE, Configuracao.TILE_SIZE);
//			sprite[i+4]=chama.getSprite((i)* Configuracao.TILE_SIZE,(1)* Configuracao.TILE_SIZE, Configuracao.TILE_SIZE, Configuracao.TILE_SIZE);
//			sprite[i+8]=chama.getSprite((i)* Configuracao.TILE_SIZE,(2)* Configuracao.TILE_SIZE, Configuracao.TILE_SIZE, Configuracao.TILE_SIZE);
//			sprite[i+12]=chama.getSprite((i)* Configuracao.TILE_SIZE,(3)* Configuracao.TILE_SIZE, Configuracao.TILE_SIZE, Configuracao.TILE_SIZE);
        }
    }

    public void tick() {
        frames++;
        if (frames >= 7) {
            frames = 0;
            index++;
            if (index == maxIndex) {
                index = 0;
            }
        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
//		g.drawImage(sprite[index],this.getX(),this.getY(), Configuracao.TILE_SIZE, Configuracao.TILE_SIZE,null);
    }
}
