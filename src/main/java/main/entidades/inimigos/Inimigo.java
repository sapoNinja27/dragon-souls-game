package main.entidades.inimigos;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.DadosGame;
import main.entidades.Entidade;
import main.enums.EstadoInimigo;
import main.utils.ImageUtils;
import main.world.Camera;

public class Inimigo extends Entidade {

    private double speed = 1.5;
    private BufferedImage[] sprites = new BufferedImage[2];
    private EstadoInimigo estadoInimigo = EstadoInimigo.ANDANDO;

    public Inimigo(int x, int y, int width, int height) {
        super(x, y, width, height);
        depth = 14;
        vida = 350;
        vidaMaxima = 350;
    }

    @Override
    public void tick(DadosGame dadosGame) {
        super.tick(dadosGame);
        verificearDistanciaPlayer(dadosGame);
//		atacar(dadosGame);
//		gerenciarAtaque(dadosGame);
    }

    private void verificearDistanciaPlayer(DadosGame dadosGame) {
        double distancia = distanciaX(dadosGame.getPlayer().getX());
        boolean esquerda = dadosGame.getPlayer().getX() > getX();
        if (distancia > 20) {
            if (!estadoInimigo.equals(EstadoInimigo.ANDANDO)) {
                estadoInimigo = EstadoInimigo.ANDANDO;
            }
            estadoInimigo.getAnimacao().tick();
            x += esquerda ? speed : -speed;
        } else {
            if (estadoInimigo.equals(EstadoInimigo.ANDANDO)) {
                estadoInimigo = rand.nextInt(2) == 1 ? EstadoInimigo.ATAQUE_1 : EstadoInimigo.ATAQUE_2;
            }
            estadoInimigo.getAnimacao().tick(()-> dadosGame.getPlayer().sofrerDano(30));
            if(dadosGame.getPlayer().getGerenciadorMovimentos().frameDano()){
                vida -= (double) dadosGame.getPlayer().getDano() / 5;
            }
        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        g.setColor(Color.black);
        g.drawRect(getX() - Camera.x, getY() - Camera.y, 51, 6);
        g.setColor(new Color(68, 0, 0));
        g.fillRect(getX() - Camera.x + 1, getY() - Camera.y + 1, 50, 5);
        g.setColor(Color.RED);
        g.fillRect(getX() - Camera.x + 1, getY() - Camera.y + 1, 50 * getVida() / vidaMaxima, 5);
        boolean esquerda = dadosGame.getPlayer().getX() > getX();
        BufferedImage img = !esquerda ? estadoInimigo.getAnimacao().getSprite() : ImageUtils.inverter(estadoInimigo.getAnimacao().getSprite());
        ImageUtils.applySombreamento(g, img, dadosGame.getTileSize(), getX() - Camera.x, getY() - Camera.y, sombreamento, sombras, () -> g.drawImage(img, getX() - Camera.x, getY() - Camera.y, null), false);
        super.render(g, dadosGame);
    }
}
