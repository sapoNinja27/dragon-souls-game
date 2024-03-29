package main.interfaces.menu;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.interfaces.mouse.Mouse;
import main.DadosGame;
import main.enums.TipoFonte;
import main.utils.Fontes;

public class MenuInventario extends Menu {
    public MenuInventario(String titulo, int width, int height) {
        super(titulo, width, height);
    }

    //16 itens chave
    //14 itens para companions
    //12 peda?os de equipamento
    //3 armaduras


    @Override
    public void tick(DadosGame dadosGame, Mouse mouse) {
        super.tick(dadosGame, mouse);
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        super.render(g, dadosGame);
        int x = 60;
        int y = 100;


        desenharBordaFundoMenu(x, y, 1000, 535, g);
        desenharFundoMenu(x, y, 1000, 535, g);
        desenharLayoutDescricao(x, y, g, dadosGame);
        g.setColor(Color.BLACK);
        int tamabho = 100;
        drawList(g, x + 1, y + 31, tamabho, tamabho, dadosGame);
//		for (Botao botoe : botoes) {
//			botoe.render(g);
//		}
        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.BOLD, 15));
    }

//    @Override
//    protected void desenharBordaFundoMenu(int x, int y, Graphics g) {
//        g.setColor(bordaMenu);
////        g.drawRect(x, y + 30, 1000, 535);
////        g.drawRect(x, y + 30, 1000, 535);
////        g.drawRect(x, y + 30, 1000, 535);
//        g.drawLine(x, y + 30, x, 666);
//        g.drawLine(x + 1000, y + 30, x + 1000, 666);
//        g.drawLine(x, 666, x + 1000, 666);
//    }

    private void drawList(Graphics g, int x, int y, int width, int height, DadosGame dadosGame) {
        //5 x 9

//        int i = 0, j = 0;
//        List<Item> itensPaginados = items.stream().filter(item -> item.tipoItem.equals(TipoItem.KEY_ITEM)).collect(Collectors.toList());
//        for (Item item : itensPaginados) {
//            item.render(g, x + i * (width + 8), y + j * (width + 8));
//            if (i != 0 && i % 8 == 0) {
//                j++;
//            }
//            i++;
//            if (j == 5) {
//                j = 0;
//            }
//            if (i == 9) {
//                i = 0;
//            }
//        }
    }
}
