package main.processamento;


import main.interfaces.mouse.Mouse;
import main.DadosGame;
import main.enums.AcaoPlayer;
import main.enums.TipoAcao;
import main.enums.TipoGame;
import main.Loading;
import main.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static java.awt.event.KeyEvent.*;

public class GerenciadorEstado {

    private DadosGame dadosGame;
    private final GerenciadorMenu gerenciadorMenu = new GerenciadorMenu();
    private final GerenciadorEntidades gerenciadorEntidades = new GerenciadorEntidades();
    //TODO isso fica dentro de menu
//    private Cutscene cutscene;

    public void iniciar(DadosGame dadosGame) {
        this.dadosGame = dadosGame;
    }

    public void tick(Mouse mouse) {
        if(gerenciadorEntidades.getEntities().isEmpty()){
            gerenciadorEntidades.getEntities().addAll(dadosGame.getEntities());
        }
        Loading.tick();
        if (!Loading.isLoading()) {
            switch (dadosGame.getEstadoGame()) {
                case CUTSCENE:
//                cutscene.tick();
                    break;
                case NORMAL:
                    gerenciadorEntidades.tick(dadosGame);
                    break;
                case MENU:
                    gerenciadorMenu.tick(dadosGame, mouse);
                    break;
            }
        }
    }

    public void renderBaixo(Graphics g) {
        switch (dadosGame.getEstadoGame()) {
            case CUTSCENE:
//            cutscene.render(g);
                break;
            case NORMAL:
                gerenciadorEntidades.render(g, dadosGame);
                break;
            default:
                break;
        }
    }

    public void renderHD(Graphics g) {
        if (dadosGame.getEstadoGame().equals(TipoGame.MENU)) {
            gerenciadorMenu.render(g, dadosGame);
        }
    }


    private void pressionar(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dadosGame.escPressed();
        }

        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            dadosGame.tabPressed();
        }

        switch (dadosGame.getEstadoGame()) {
            case CUTSCENE:
            case MENU:
                break;
            case NORMAL:
                switch (e.getKeyCode()) {
                    case VK_X:
                        gerenciadorEntidades.interagir();
                        break;
                    case VK_1:
                        gerenciadorEntidades.acaoPlayer(AcaoPlayer.HABILIDADE_1, dadosGame.getPlayer());
                        break;
                    case VK_D:
                        gerenciadorEntidades.acaoPlayer(AcaoPlayer.DIREITA, dadosGame.getPlayer());
                        break;
                    case VK_E:
                        gerenciadorEntidades.acaoPlayer(AcaoPlayer.DASH, dadosGame.getPlayer());
                        break;
                    case VK_A:
                        gerenciadorEntidades.acaoPlayer(AcaoPlayer.ESQUERDA, dadosGame.getPlayer());
                        break;
                    case VK_W:
                        gerenciadorEntidades.acaoPlayer(AcaoPlayer.CIMA, dadosGame.getPlayer());
                        break;
                    case VK_R:
                        gerenciadorEntidades.acaoPlayer(AcaoPlayer.ULTIMATE, dadosGame.getPlayer());
                        break;
                    case VK_Q:
                        gerenciadorEntidades.acaoPlayer(AcaoPlayer.SOCO_FRACO, dadosGame.getPlayer());
                        break;
                }
                //TODO ajustar
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    dadosGame.saveGame(0);
                }
                break;
        }
    }

    public void soltar(KeyEvent e) {
        switch (dadosGame.getEstadoGame()) {
            case MENU:
            case CUTSCENE:
                break;
            case NORMAL:
                switch (e.getKeyCode()){
                    case VK_D:
                    case VK_A:
                        gerenciadorEntidades.acaoPlayer(AcaoPlayer.PARAR, dadosGame.getPlayer());
                        break;
                    case VK_W:
                        gerenciadorEntidades.acaoPlayer(AcaoPlayer.PARAR_PULO, dadosGame.getPlayer());
                        break;
                }

//                if (e.getKeyCode() == KeyEvent.VK_UP) {
//
//                }
//			if(e.getKeyCode() == KeyEvent.VK_LEFT){
//				player.camL=false;
//				player.camx=0;
//			}
//			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//				player.camR=false;
//				player.camx=0;
//			}
                break;
        }
    }

    public void gerenciarCliques(KeyEvent e, TipoAcao tipoAcao) {
        if (tipoAcao.equals(TipoAcao.PRESSIONAR)) {
            pressionar(e);
        }
        if (tipoAcao.equals(TipoAcao.SOLTAR)) {
            soltar(e);
        }
    }

    public void render(BufferedImage image, BufferStrategy bs) {
        Graphics g = image.getGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, dadosGame.getWidth(), dadosGame.getHeight());
        renderBaixo(g);
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, dadosGame.getScaleWidth(), dadosGame.getScaleHeight(), null);
        renderHD(g);
        Loading.render(g, dadosGame);
        bs.show();
    }
}
