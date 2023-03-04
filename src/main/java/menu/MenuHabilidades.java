package menu;

import main.DadosGame;
import entidades.players.principal.habilidades.Habilidade;
import main.enums.TipoFonte;
import graficos.Spritesheet;
import main.utils.FonteUtils;
import main.utils.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MenuHabilidades extends Menu {

    private final Spritesheet fundo = new Spritesheet("/menus/background.png");

    private BufferedImage[] img;

    private Spritesheet tema;

    int posx = 0;

    public MenuHabilidades() {
        Spritesheet cenario = new Spritesheet("/cenario/cenario.png");
        img = new BufferedImage[]{
                (cenario).getSprite(0, 3 * 64,
                        64, 64),
                (cenario).getSprite((2) * 64, 3 * 64,
                        64, 64)
        };
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        super.render(g, dadosGame);
        int x = 60;
        int y = 100;
        desenharTextosInfo(x, y, g, dadosGame);
        desenharBordaFundoMenu(x, y, g);
        desenharFundoMenu(x, y, g);
        desenharInfo(x, y, g, dadosGame);
        drawHabilidades(x, y, g, dadosGame);
    }

    private void desenharTextosInfo(int x, int y, Graphics g, DadosGame dadosGame) {
        y = y - 18;
        g.setColor(Color.WHITE);
        Font font = FonteUtils.CrimsonText(TipoFonte.REGULAR, 35);
        g.setFont(font);
        g.drawString("Habilidades", x, y - 40);

        g.setFont(font.deriveFont(25f));
        g.drawString(String.format(
                "Nível  %s",
                dadosGame.getPlayer().getNivel()
        ), x, y - 15);
        g.setFont(font.deriveFont(17f));
        g.drawString(String.format(
                "%s XP %s Pontos  %s",
                dadosGame.getPlayer().getXp(),
                StringUtils.repeat(" ", 13 - String.valueOf(dadosGame.getPlayer().getXp()).length()),
                dadosGame.getPlayer().getPontosHabilidade()
        ), x, y + 5);
        g.drawRect(x, y + 17, 150, 16);
        g.fillRect(x, y + 17, dadosGame.getPlayer().getXp(), 16);
    }

    private void desenharInfo(int x, int y, Graphics g, DadosGame dadosGame) {
        desenharLayoutDescricao(x, y, g, dadosGame);
        g.drawImage(img[0], x + 1222, y + 95, 64 * 2, 64 * 2, null);
        g.drawImage(img[1], x + 1222 - 64 * 2, y + 95, 64 * 2, 64 * 2, null);
        g.drawImage(img[1], x + 1243 - 64 * 3, y + 95, 64 * 2, 64 * 2, null);
        dadosGame.getPlayer().desenharInfo(x, y, g, dadosGame, getSelected(dadosGame.getPlayer().getHabilidades()));
    }

    private boolean noneSelected(List<Habilidade> list) {
        return list.stream().noneMatch(Habilidade::isOver);
    }

    private Habilidade getSelected(List<Habilidade> list) {
        if (noneSelected(list)) {
            return null;
        }
        return list.stream().filter(Habilidade::isOver).findFirst().orElse(null);
    }

    private void drawHabilidades(int x, int y, Graphics g, DadosGame dadosGame) {
//        g.setColor(bordaMenu);
//        g.drawRect(x, y + 30, 1000, 50);
//        g.setColor(fundoMenu);
//        g.fillRect(x, y + 30, 1000, 50);
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("arial", Font.BOLD, 25));
//        g.drawString("Básico", x + 30, y + 65);
//
//        g.setColor(bordaMenu);
//        g.drawRect(x, y + 30 + 200, 1000, 50);
//        g.setColor(fundoMenu);
//        g.fillRect(x, y + 30 + 200, 1000, 50);
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("arial", Font.BOLD, 25));
//        g.drawString("Avançado", x + 30, y + 265);
//
//        List<Habilidade> basicas = dadosGame.getPlayer().getHabilidades().stream().filter(Habilidade::isBasica).collect(Collectors.toList());
//        List<Habilidade> avancadas = dadosGame.getPlayer().getHabilidades().stream().filter(habilidade -> !habilidade.isBasica()).collect(Collectors.toList());
//        for (int i = 0; i < basicas.size(); i++) {
//            basicas.get(i).setXY(120 + (i * 20) + (i * 1000 / basicas.size()), 220);
//            basicas.get(i).render(g, dadosGame);
//        }
//        for (int i = 0; i < avancadas.size(); i++) {
//            avancadas.get(i).setXY(120 + (i * 20) + (i * 1000 / avancadas.size()), 420);
//            avancadas.get(i).render(g, dadosGame);
//        }
    }
}
