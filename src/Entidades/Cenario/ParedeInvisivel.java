package Entidades.Cenario;

import java.awt.Color;
import java.awt.Graphics;

import Configuration.Configuracoes;
import Entidades.Entidade;
import Menu.MascaraHitBox;
import World.Camera;
import enums.DirecaoPlayer;

public class ParedeInvisivel extends Entidade {

	public ParedeInvisivel(int x, int y, int dist) {
		super(x, y, dist, 0);
		adicionarMascara(new MascaraHitBox("esquerdo", 0, 0, 1, Configuracoes.HEIGHT * 19));
		adicionarMascara(new MascaraHitBox("direito", dist * Configuracoes.TILE_SIZE - 2, 0, 1, Configuracoes.HEIGHT * 19));
	}

    @Override
	public void teleportarPlayer(Entidade player) {
        int distanciaEntreAsParedes = width;
        int largura = height;
        if (player.getDirecaoPlayer().equals(DirecaoPlayer.DIREITA)) {
            player.setX(this.getX() - 15 + largura);
            player.setParado(true);
        }
        if (player.getDirecaoPlayer().equals(DirecaoPlayer.ESQUERDA)) {
           player.setX(this.getX() + (distanciaEntreAsParedes * Configuracoes.TILE_SIZE) - 47 - largura);
           player.setParado(true);
        }
	}

	void setLarguraParede(int largura) {
		this.height = largura;
        limparMascaras();
        int distanciaEntreAsParedes = width;
		adicionarMascara(new MascaraHitBox("esquerdo", largura, 0, 1, Configuracoes.HEIGHT * 19));
		adicionarMascara(new MascaraHitBox("direito", distanciaEntreAsParedes * Configuracoes.TILE_SIZE - 2 - largura, 0, 1, Configuracoes.HEIGHT * 19));
	}



	public void render(Graphics g) {
		g.setColor(Color.red);
        g.drawRect(this.getX() - Camera.x + mascaras.get(0).getPosicaoX(), this.getY() - Camera.y + mascaras.get(0).getPosicaoY(), mascaras.get(0).getAutura(), mascaras.get(0).getLargura());
        g.drawRect(this.getX() - Camera.x + mascaras.get(1).getPosicaoX(), this.getY() - Camera.y + mascaras.get(1).getPosicaoY(), mascaras.get(1).getAutura(), mascaras.get(1).getLargura());
    }

}