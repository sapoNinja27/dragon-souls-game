package main.entidades.cenario.estaticos;

import main.DadosGame;
import main.entidades.Entidade;
import main.entidades.Mascara;
import main.entidades.players.Player;
import main.enums.TipoFonte;
import main.enums.TipoMascara;
import main.utils.Fontes;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.nonNull;
import static main.enums.TipoAmbiente.ESGOTOS;

public class Bueiro extends Plataforma implements HasInteraction{
    private final BufferedImage[] img;
    private int frames = 0;
    private float op = 0.1f;

    public Bueiro(int x, int y, BufferedImage[] img) {
        super(x, y, null);
        this.img = img;
        depth = 3;
        adicionarMascara(Mascara.builder()
                .alias("bueiro")
                .x(17)
                .y(-15)
                .height(40)
                .width(32)
                .build());
    }

    @Override
    public void tick(DadosGame dadosGame) {
        if (colidindoComPlayer(dadosGame.getPlayer())) {
            frames++;
            if (frames >= 10) {
                if (op < 0.9f) {
                    op += 0.1f;
                }
            }
        } else {
            frames = 0;
            op = 0.1f;
        }
        super.tick(dadosGame);
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        int posX = getX() - Camera.x;
        int posY = getY() - Camera.y;
        if (colidindoComPlayer(dadosGame.getPlayer())) {
            g.drawImage(img[1], posX, posY, null);
            Graphics2D g2 = (Graphics2D) g;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
            g.setFont(Fontes.CrimsonText(TipoFonte.REGULAR, 20));
            g.setColor(Color.white);
            g.drawString("Esgotos", posX + 60, posY + 55);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else {
            g.drawImage(img[0], posX, posY, null);
        }
        super.render(g, dadosGame);
    }

    @Override
    public boolean applyInteraction(DadosGame dadosGame, List<Entidade> list) {
        Player player = dadosGame.getPlayer();
        EscadaEsgoto alvo = list
                .stream()
                .filter(e -> e instanceof EscadaEsgoto)
                .map(e -> (EscadaEsgoto) e)
                .min(Comparator.comparingInt(e -> (int) e.distanciaX(player.getX())))
                .orElse(null);
        if(nonNull(alvo)){
            player.setX(alvo.getX());
            player.setY(alvo.getY());
            dadosGame.setLocal(ESGOTOS);
        }
        return false;
    }
}