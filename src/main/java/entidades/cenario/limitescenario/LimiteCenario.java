package entidades.cenario.limitescenario;

import entidades.Entidade;
import main.DadosGame;
import main.enums.DirecaoPlayer;
import main.world.Camera;

import java.awt.*;

public class LimiteCenario extends Entidade {

    public LimiteCenario(int x, int y, DadosGame dadosGame) {
        super(x, y, dadosGame.getWordWidth(), 0);
//        adicionarMascara(new MascaraHitBox("esquerdo", 0, 0, 1, dadosGame.getHeight() * 19));
//        adicionarMascara(new MascaraHitBox("direito", dadosGame.getWordWidth() * dadosGame.getTileSize() - 2, 0, 1, dadosGame.getHeight() * 19));
    }

    //TODO mover a responsabilidade de validar pra ca
    @Override
    public void teleportarPlayer(DadosGame dadosGame) {
        Entidade player = dadosGame.getPlayer();
        int distanciaEntreAsParedes = width;
        int largura = height;
        if (player.getDirecao().equals(DirecaoPlayer.DIREITA)) {
            player.setX(this.getX() - 15 + largura);
            player.setParado(true);
        }
        if (player.getDirecao().equals(DirecaoPlayer.ESQUERDA)) {
            player.setX(this.getX() + (distanciaEntreAsParedes * dadosGame.getTileSize()) - 47 - largura);
            player.setParado(true);
        }
    }

    public void setLarguraParede(int largura) {
        this.height = largura;
        limparMascaras();
        int distanciaEntreAsParedes = width;
//		adicionarMascara(new MascaraHitBox("esquerdo", largura, 0, 1, Configuracao.HEIGHT * 19));
//		adicionarMascara(new MascaraHitBox("direito", distanciaEntreAsParedes * Configuracao.TILE_SIZE - 2 - largura, 0, 1, Configuracao.HEIGHT * 19));
    }


    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        g.setColor(Color.red);
        g.drawRect(this.getX() - Camera.x + mascaras.get(0).getX(), this.getY() - Camera.y + mascaras.get(0).getY(), mascaras.get(0).getAltura(), mascaras.get(0).getLargura());
        g.drawRect(this.getX() - Camera.x + mascaras.get(1).getX(), this.getY() - Camera.y + mascaras.get(1).getY(), mascaras.get(1).getAltura(), mascaras.get(1).getLargura());
        super.render(g, dadosGame);
    }

}