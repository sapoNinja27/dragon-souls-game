package menu;

import configuracoes.DadosGame;
import entidades.players.Habilidade;
import enums.TipoFonte;
import graficos.Spritesheet;
import utils.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

import static utils.ImageUtils.draw;
import static utils.ImageUtils.fill;

public class MenuHabilidades {
    private final Color bordaMenu;
    private final Color fundoMenu;

    private final Spritesheet fundo;

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
        fundo = new Spritesheet("/menus/background.png");
        bordaMenu = new Color(173, 8, 0);
        fundoMenu = new Color(173, 8, 0, 34);
    }

    private void atualizarFundo() {
        posx++;
        if (posx + 1440 == 2976) {
            posx = 0;
        }
    }

    public void tick() {
        atualizarFundo();
    }

    public void render(Graphics g, DadosGame dadosGame) {
        int x = 60;
        int y = 100;
        backgroundTela(g, dadosGame);
        desenharTextosInfo(x, y, g, dadosGame);
        desenharFundoMenu(x, y, g);
        desenharInfo(x, y, g, dadosGame);
        drawHabilidades(x, y, g, dadosGame);
    }

    private void backgroundTela(Graphics g, DadosGame dadosGame) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
        g.drawImage(fundo.getSprite(posx, 0, dadosGame.getScaleWidth(), dadosGame.getScaleHeight()), 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.setColor(new Color(56, 0, 0, 95));
        g.fillRect(0, 0, dadosGame.getScaleWidth(), dadosGame.getScaleHeight());
    }

    private void desenharTextosInfo(int x, int y, Graphics g, DadosGame dadosGame) {
        y = y - 18;
        g.setColor(Color.WHITE);
        Font font = StringUtils.getCustomFont("CrimsonText", TipoFonte.REGULAR, 35);
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

    private void desenharFundoMenu(int x, int y, Graphics g) {
        g.setColor(bordaMenu);
        g.drawRect(x, y + 30, 1000, 500);
        g.setColor(fundoMenu);
        g.fillRect(x, y + 30, 1000, 500);
    }

    private void desenharInfo(int x, int y, Graphics g, DadosGame dadosGame) {
        desenharLayoutDescricaoHabilidade(x, y, g, dadosGame);
        g.drawImage(img[0], x + 1222, y + 95, 64 * 2, 64 * 2, null);
        g.drawImage(img[1], x + 1222 - 64 * 2, y + 95, 64 * 2, 64 * 2, null);
        g.drawImage(img[1], x + 1243 - 64 * 3, y + 95, 64 * 2, 64 * 2, null);
        if (noneSelected(dadosGame.getPlayer().getHabilidades())) {
            dadosGame.getPlayer().desenharInfo(x, y, g);
        } else {
            getSelected(dadosGame.getPlayer().getHabilidades()).desenharInfo(x, y, g, dadosGame.getPlayer());
        }
    }

    private void desenharLayoutDescricaoHabilidade(int x, int y, Graphics g, DadosGame dadosGame) {
        int tileSize = dadosGame.getTileSize(3);
        fill(g, x + 1050, y + 30, new Color(41, 161, 236), 300, tileSize);
        draw(g, x + 1050, y + 30, bordaMenu, 300, 500);
        fill(g, x + 1050, y + 30, fundoMenu, 300, 500);
        draw(g, x + 1050, y + 30 + tileSize, bordaMenu, 300, 50);
        fill(g, x + 1050, y + 30 + tileSize, fundoMenu, 300, 50);
    }

    private boolean noneSelected(List<Habilidade> list) {
        return list.stream().noneMatch(Habilidade::isOver);
    }

    private Habilidade getSelected(List<Habilidade> list) {
        return list.stream().filter(Habilidade::isOver).findFirst().orElse(null);
    }

    private void drawHabilidades(int x, int y, Graphics g, DadosGame dadosGame) {
        g.setColor(bordaMenu);
        g.drawRect(x, y + 30, 1000, 50);
        g.setColor(fundoMenu);
        g.fillRect(x, y + 30, 1000, 50);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 25));
        g.drawString("Basico", x + 30, y + 65);

        g.setColor(bordaMenu);
        g.drawRect(x, y + 30 + 200, 1000, 50);
        g.setColor(fundoMenu);
        g.fillRect(x, y + 30 + 200, 1000, 50);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 25));
        g.drawString("Avançado", x + 30, y + 265);

        List<Habilidade> basicas = dadosGame.getPlayer().getHabilidades().stream().filter(Habilidade::isBasica).collect(Collectors.toList());
        List<Habilidade> avancadas = dadosGame.getPlayer().getHabilidades().stream().filter(habilidade -> !habilidade.isBasica()).collect(Collectors.toList());
        for (int i = 0; i < basicas.size(); i++) {
            basicas.get(i).setXY(120 + (i * 20) + (i * 1000 / basicas.size()), 220);
            basicas.get(i).render(g, dadosGame);
        }
        for (int i = 0; i < avancadas.size(); i++) {
            avancadas.get(i).setXY(120 + (i * 20) + (i * 1000 / avancadas.size()), 420);
            avancadas.get(i).render(g, dadosGame);
        }
    }
}
