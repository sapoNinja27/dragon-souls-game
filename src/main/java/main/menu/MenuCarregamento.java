package main.menu;

import jObjects.Mouse.Mouse;
import main.DadosGame;
import jObjects.Botao;

import java.awt.*;
import java.io.File;

public class MenuCarregamento  extends Menu {
    private final Botao[] botoes = {};
    private boolean saveGame = false;

    @Override
    public void tick(DadosGame dadosGame, Mouse mouse) {
        for (Botao botoe : botoes) {
            botoe.tick(mouse);
        }
        File file = new File("save.txt");
        boolean saveExists = file.exists();

        // pra deletar file=new File("save.txt"); file.delete();

        botoes[0].setTexto(String.valueOf(file));
//        if (botoes[0].isClicked()) {
//            if (saveExists) {
////				String saver = Game.menu.loadGame(0);
////				Game.menu.applySave(saver);
//            }
//        }
//        if (botoes[3].isClicked()) {
//            dadosGame.home();
//        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        g.setColor(Color.black);
        g.fillRoundRect(720 / 3 - 40, 65, 260 + 20, 200 + 20, 30, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(720 / 3 - 30, 75, 260, 200, 30, 50);
        g.setColor(Color.black);
        g.fillRoundRect(580, 312, 68, 28, 30, 50);
        g.setColor(Color.black);
        for (Botao botoe : botoes) {
            botoe.render(g);
        }
        g.drawString("Carregar Jogo", 720 / 2 - 80, 95);
    }
}
