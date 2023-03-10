package main.interfaces.menu;

import com.google.gson.Gson;
import main.DadosGame;
import main.interfaces.Botao;
import main.interfaces.mouse.Mouse;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.nonNull;

public class MenuCarregamento extends Menu {
    private List<Botao> botoes = new ArrayList<>(0);
    private boolean saveGame = false;

    public MenuCarregamento(String titulo, int width, int height) {
        super(titulo, width, height);
    }

    @Override
    public void tick(DadosGame dadosGame, Mouse mouse) {
        loadButtons(dadosGame);
        for (Botao botoe : botoes) {
            botoe.tick(mouse);
        }
//        boolean saveExists = file.exists();

        // pra deletar file=new File("save.txt"); file.delete();

//        botoes[0].setTexto(String.valueOf(file));
//        if (botoes[0].isClicked()) {
//            if (saveExists) {
////				String saver = Game.menu.loadGame(0);
////				Game.menu.applySave(saver);
//            }
//        }
//        if (botoes[3].isClicked()) {
//            dadosGame.home();
//        }
        super.tick(dadosGame, mouse);
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        for (Botao botao : botoes) {
            botao.render(g);
        }
        super.render(g, dadosGame);
    }

    private void loadButtons(DadosGame dadosGame) {
        if (botoes.isEmpty()) {
            List<Botao> newBotoes = new ArrayList<>();
            File folder = new File("saves");
            File[] files = folder.listFiles();
            if (nonNull(files)) {
                Arrays.sort(files, Comparator.comparingLong(File::lastModified));
                int size = Math.min(files.length, 9);
                int xspace = 20;
                for (int i = 0; i < size; i++) {
                    String name = files[i].getName().split(".txt")[0];
                    final int ni = i;
                    int posX = x + xspace;
                    int posY = y + 51 + (21 * i) + (150 * i);
                    switch (i) {
                        case 3:
                        case 4:
                        case 5:
                            posX = x + xspace * 3 + 393;
                            posY = y + 51 + (21 * (i - 3)) + (150 * (i - 3));
                            break;
                        case 6:
                        case 7:
                        case 8:
                            posX = x + xspace * 5 + 393 * 2;
                            posY = y + 51 + (21 * (i - 6)) + (150 * (i - 6));
                            break;
                    }
                    newBotoes.add(Botao.builder()
                            .x(posX)
                            .y(posY)
                            .spacingY(35)
                            .spacingX(35)
                            .cor(new Color(173, 8, 0, 150))
                            .texto(name)
                            .arcWidth(30).arcHeight(50)
                            .font(25)
                            .acao(() -> dadosGame.carregarJogo(files[ni], ni + 10))
                            .width(393)
                            .height(150)
                            .build());
                }
            }
            botoes = newBotoes;
        }
    }
}
