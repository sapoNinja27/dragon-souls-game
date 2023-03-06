package main.entidades.cenario.estaticos;

import main.DadosGame;
import main.entidades.Entidade;
import main.entidades.Mascara;
import main.entidades.players.Player;
import main.enums.TipoAmbiente;
import main.enums.TipoMascara;
import main.utils.Spritesheet;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.nonNull;
import static main.enums.TipoAmbiente.CIDADE_DE_BAIXO;
import static main.enums.TipoAmbiente.ESGOTOS;

public class EscadaEsgoto extends Entidade implements HasInteraction{
    private final BufferedImage[] img = new BufferedImage[2];
    private float op = 0.1f;
    private int frames = 0;

    public EscadaEsgoto(int x, int y, Spritesheet spt, DadosGame dadosGame) {
        super(x, y, 0, 0);
        int tileSize = dadosGame.getTileSize();
        depth = 10;
        adicionarMascara(Mascara.builder()
                .tipoMascara(TipoMascara.HITBOX)
                .x(17)
                .y(-100)
                .height(200)
                .width(32)
                .build());
        img[0] = spt.getSprite(0, 6 * tileSize, tileSize, tileSize);
        img[1] = spt.getSprite(0, 6 * tileSize, tileSize, tileSize - 3);
    }

    @Override
    public void tick(DadosGame dadosGame) {
        if (colidindo) {
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
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        int tileSize = dadosGame.getTileSize();
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < 7; i++) {
            g.drawImage(img[1], this.getX() - Camera.x - 27, this.getY() - Camera.y - tileSize - (tileSize * i), tileSize * 2, tileSize, null);
        }
        g.drawImage(img[0], this.getX() - Camera.x - 27, this.getY() - Camera.y, tileSize * 2, tileSize, null);
        if (colidindo) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
            g.setFont(new Font("Cambria Math", Font.PLAIN, 20));
            g.setColor(Color.white);
            g.drawString("Cidade", this.getX() - Camera.x + 5, this.getY() - Camera.y - 20);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        super.render(g, dadosGame);
    }

    @Override
    public boolean applyInteraction(DadosGame dadosGame, List<Entidade> list) {
        Player player = dadosGame.getPlayer();
        Bueiro alvo = list
                .stream()
                .filter(e -> e instanceof Bueiro)
                .map(e -> (Bueiro) e)
                .min(Comparator.comparingInt(e -> (int) e.distanciaX(player.getX())))
                .orElse(null);
        if(nonNull(alvo)){
            player.setX(alvo.getX());
            player.setY(alvo.getY());
            dadosGame.setLocal(CIDADE_DE_BAIXO);
        }
        return false;
    }
}