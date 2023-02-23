package processamento;


import configuracoes.Configuracao;
import world.World;
import enums.AcaoPlayer;
import enums.TipoAcao;
import enums.TipoGame;
import enums.TipoMenu;
import graficos.Loading;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GerenciadorEstado {

    private GerenciadorMenu gerenciadorMenu;
    World world = new World("/niveis/area1.png");
//    private Cutscene cutscene;
    private GerenciadorEntidades gerenciadorEntidades;
    private GerenciadorSave gerenciadorSave;

    public void iniciar() {
        gerenciadorMenu = new GerenciadorMenu();
        world.startGame();
//        cutscene = new Cutscene();
        gerenciadorEntidades = world.getGerenciadorEntidades();
    }

    public GerenciadorEntidades getGerenciadorEntidades(){
        return gerenciadorEntidades;
    }

    public void tick(){
        Loading.tick();
        if (!Loading.isLoading()) {
            if(Configuracao.estadoGame == TipoGame.CUTSCENE){
//                cutscene.tick();
            }
            if (Configuracao.estadoGame == TipoGame.NORMAL) {
                gerenciadorEntidades.tick();
            }
            if (Configuracao.estadoGame == TipoGame.MENU) {
                gerenciadorMenu.atualizarPlayer(gerenciadorEntidades.getPlayer());
                gerenciadorMenu.tick();
                gerenciadorEntidades.getPlayer().tick();
            }
        }
    }
    public void renderBaixo(Graphics g){
        if (Configuracao.estadoGame.equals(TipoGame.CUTSCENE)){
//            cutscene.render(g);
        }
        if (Configuracao.estadoGame == TipoGame.NORMAL) {
            gerenciadorEntidades.render(g);
        }
    }
    public void renderHD(Graphics g){
        if (Configuracao.estadoGame == TipoGame.MENU) {
            gerenciadorMenu.render(g);
        }
    }


    private void pressionar(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (Configuracao.estadoMenu != TipoMenu.INICIAL) {
                if (Configuracao.estadoGame == TipoGame.MENU) {
                    Configuracao.estadoGame = TipoGame.NORMAL;
                } else if (Configuracao.estadoGame == TipoGame.NORMAL) {
                    Configuracao.estadoGame = TipoGame.MENU;
                }
            }
        }
        if(Configuracao.estadoGame == TipoGame.CUTSCENE){

        }
        if (Configuracao.estadoGame == TipoGame.NORMAL) {
            if (e.getKeyCode() == KeyEvent.VK_X) {
                //acao de clicar interagir
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                gerenciadorEntidades.trocaPersonagem();
            }

            if (e.getKeyCode() == KeyEvent.VK_D) {
                gerenciadorEntidades.acaoPlayer(AcaoPlayer.DIREITA);
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                gerenciadorEntidades.acaoPlayer(AcaoPlayer.DASH);
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                gerenciadorEntidades.acaoPlayer(AcaoPlayer.ESQUERDA);
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                gerenciadorEntidades.acaoPlayer(AcaoPlayer.CIMA);
            }
        }
        if (Configuracao.estadoGame == TipoGame.MENU) {
            gerenciadorMenu.tick();
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            gerenciadorEntidades.acaoPlayer(AcaoPlayer.ULTIMATE);
        }

        if (e.getKeyCode() == KeyEvent.VK_Q) {
            gerenciadorEntidades.acaoPlayer(AcaoPlayer.SOCO_FRACO);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            String[] opt1 = { "level" };
            int[] opt2 = { 1 };
            gerenciadorSave.saveGame(opt1, opt2, 0);
        }
    }

    public void soltar(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_X) {
            //clicar
        }
        if(Configuracao.estadoGame.equals(TipoGame.NORMAL)) {
//            if (e.getKeyCode() == KeyEvent.VK_UP) {
//
//            }
//			if(e.getKeyCode() == KeyEvent.VK_LEFT){
//				player.camL=false;
//				player.camx=0;
//			}
//			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//				player.camR=false;
//				player.camx=0;
//			}
        }
        if(Configuracao.estadoGame.equals(TipoGame.NORMAL)){
            if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) {
                gerenciadorEntidades.acaoPlayer(AcaoPlayer.PARAR);
            }

            if (e.getKeyCode() == KeyEvent.VK_W) {
                gerenciadorEntidades.acaoPlayer(AcaoPlayer.PARAR_PULO);
            }
        }
    }

    public void gerenciarCliques(KeyEvent e, TipoAcao tipoAcao){
        if(tipoAcao.equals(TipoAcao.PRESSIONAR)) {
            pressionar(e);
        }
        if(tipoAcao.equals(TipoAcao.SOLTAR)) {
            soltar(e);
        }
    }
}