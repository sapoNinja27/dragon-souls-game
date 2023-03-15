package main.entidades.inimigos;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.DadosGame;
import main.entidades.Entidade;
import main.entidades.players.Player;
import main.enums.DirecaoPlayer;
import main.enums.EstadoInimigo;
import main.utils.ImageUtils;
import main.world.Camera;

import static main.enums.EstadoInimigo.*;

public class Inimigo extends Entidade {
    private EstadoInimigo estadoInimigo = ANDANDO;

    public Inimigo(int x, int y, int width, int height) {
        super(x, y, width, height, 14, 1.5, 350);
    }

    @Override
    public void tick(DadosGame dadosGame) {
        super.tick(dadosGame);
        verificearDistanciaPlayer(dadosGame);
    }

    @Override
    protected void acaoMorte() {
        if (!estadoInimigo.equals(HURTED) && !estadoInimigo.equals(MORRENDO)) {
            estadoInimigo = HURTED;
        }
    }

    private void verificearDistanciaPlayer(DadosGame dadosGame) {
        double distancia = distanciaX(dadosGame.getPlayer().getX());
        boolean esquerda = dadosGame.getPlayer().getX() > getX();
        if (estadoInimigo.equals(MORRENDO)) {
            estadoInimigo.getAnimacao().tick(() -> morto = true);
        } else {
            if (estadoInimigo.equals(HURTED)) {
                if (getVida() <= 0) {
                    estadoInimigo.getAnimacao().tick(() -> estadoInimigo = MORRENDO);
                } else {
                    estadoInimigo.getAnimacao().tick(() -> estadoInimigo = ANDANDO);
                }
                x += dadosGame.getPlayer().getDirecao().equals(DirecaoPlayer.ESQUERDA) ? -getSpeed() * 2 : getSpeed() * 2;
            } else {
                if (distancia > 20) {
                    if (!estadoInimigo.equals(ANDANDO)) {
                        estadoInimigo = ANDANDO;
                    }
                    estadoInimigo.getAnimacao().tick();
                    x += esquerda ? getSpeed() : -getSpeed();
                } else {
                    if (estadoInimigo.equals(ANDANDO)) {
                        estadoInimigo = rand.nextInt(2) == 1 ? ATAQUE_1 : ATAQUE_2;
                    }
                    estadoInimigo.getAnimacao().tick(() -> dadosGame.getPlayer().sofrerDano(30));
                    if (dadosGame.getPlayer().getGerenciadorMovimentos().frameDano()) {
                        vida -= dadosGame.getPlayer().getDano();
                        estadoInimigo = HURTED;
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        if (vida > 0) {
            g.setColor(Color.black);
            g.drawRect(getX() - Camera.x, getY() - Camera.y, 51, 6);
            g.setColor(new Color(68, 0, 0));
            g.fillRect(getX() - Camera.x + 1, getY() - Camera.y + 1, 50, 5);
            g.setColor(Color.RED);
            g.fillRect(getX() - Camera.x + 1, getY() - Camera.y + 1, 50 * (int) (vida)/ vidaMaxima, 5);
        }
        boolean esquerda = dadosGame.getPlayer().getX() > getX();
        BufferedImage img = !esquerda ? estadoInimigo.getAnimacao().getSprite() : ImageUtils.inverter(estadoInimigo.getAnimacao().getSprite());
        ImageUtils.applySombreamento(g, img, dadosGame.getTileSize(), getX() - Camera.x, getY() - Camera.y, sombreamento, sombras, () -> g.drawImage(img, getX() - Camera.x, getY() - Camera.y, null), false);
        super.render(g, dadosGame);
    }
}
