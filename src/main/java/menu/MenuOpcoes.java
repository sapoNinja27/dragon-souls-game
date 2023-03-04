package menu;

import main.DadosGame;
import main.enums.TipoMenu;
import jObjects.Botao;

import java.awt.*;

public class MenuOpcoes extends Menu{
    private final Botao[] principal = {
//			new Botao(720 / 2 - 200 + 91, 220, 90, 30, "Portugues", Color.DARK_GRAY, 2, 5, 20, 30, 50),
//            new Botao(580, 312, 68, 28, "Voltar", Color.red, 2, 10, 20, 30, 50)
    };
    private final Botao[] pause = {
//            new Botao(25, 50, 105, 25, "Habilidade", Color.red, 2, 9, 18, 30, 50),
//            new Botao(618 - 25, 50, 100, 25, "Inventario", Color.red, 2, 9, 18, 30, 50),
//            new Botao(618 - 70, 265, 150, 25, "Voltar Ao Jogo", Color.red, 2, 13, 18, 30, 50),
//            new Botao(618 - 70, 300, 150, 25, "Menu Principal", Color.red, 2, 13, 18, 30, 50)
    };
    private final Botao[] botoes = {
//            new Botao(720 / 2 - 9, 120, 90, 30, "On", Color.green, 2, 5, 20, 30, 50),
//            new Botao(720 / 2 - 9, 170, 90, 30, "On", Color.green, 2, 5, 20, 30, 50)
    };

    @Override
    public void tick(DadosGame dadosGame) {
        boolean isPrincipal = dadosGame.getLastEstadoMenu().equals(TipoMenu.INICIAL);
        for (Botao botoe : botoes) {
            botoe.tick();
        }
        if (botoes[0].isClicked()) {
            //TODO criar botão padrçao swith
//            if (dadosGame.isMusica()) {
//                botoes[0].setTexto("Off");
//                botoes[0].setCor(Color.red);
//                botoes[0].setSpacing(60, 20);
//                dadosGame.setMusica(false);
//            } else {
//                botoes[0].setText("On");
//                botoes[0].setCor(Color.green);
//                botoes[0].setSpacing(5, 20);
//                dadosGame.setMusica(true);
//            }
        }
        if (botoes[1].isClicked()) {
//            if (Configuracao.efeitos) {
//                botoes[1].setText("Off");
//                botoes[1].setCor(Color.red);
//                botoes[1].setSpacing(60, 20);
//                Configuracao.efeitos = false;
//            } else if (!Configuracao.efeitos) {
//                botoes[1].setText("On");
//                botoes[1].setCor(Color.green);
//                botoes[1].setSpacing(5, 20);
//                Configuracao.efeitos = true;
//            }
        }
        if (isPrincipal) {
            for (Botao botao : principal) {
                botao.tick();
            }
            if (principal[0].isClicked()) {
                dadosGame.home();
            }
        } else {
            for (Botao botao : pause) {
                botao.tick();
            }
            if (pause[0].isClicked()) {
                dadosGame.habilidades();
            }
            if (pause[1].isClicked()) {
                dadosGame.inventario();
            }
            if (pause[2].isClicked()) {
                dadosGame.jogar();
            }
            if (pause[3].isClicked()) {
                dadosGame.home();
            }
        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        super.render(g, dadosGame);
        boolean isPrincipal = dadosGame.getLastEstadoMenu().equals(TipoMenu.INICIAL);
        g.setColor(Color.black);
        g.fillRoundRect(720 / 3 - 40, 65, 260 + 20, 200 + 20, 30, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(720 / 3 - 30, 75, 260, 200, 30, 50);
        g.setColor(Color.black);
        g.drawString("Opções", 720 / 3 + 55, 95);
        g.drawString("Musica", 720 / 3 + 10, 140);
        g.drawString("Efeitos", 720 / 3 + 10, 190);
//		// volume
//		g.setColor(Color.black);
//		g.fillRect(720 / 2 + 20, 85, 99, 20);
//		if (Menu[currentOption] == "Volume") {
//			g.fillRect(720 / 2 + 20, 85, volume, 20);
//		} else {
//			g.fillRect(720 / 2 + 20, 85, Mouse.getX(), 20);
//		}

        if (isPrincipal) {
            for (Botao botao : principal) {
                botao.render(g);
            }
        } else {
            for (Botao botao : pause) {
                botao.render(g);
            }
        }
        for (Botao botoe : botoes) {
            botoe.render(g);
        }
    }
}
