package main;

import main.entidades.players.Player;
import lombok.Getter;
import lombok.Setter;
import main.enums.TipoAmbiente;
import main.enums.TipoGame;
import main.enums.TipoMenu;

import java.util.Arrays;

import static main.enums.TipoGame.MENU;
import static main.enums.TipoGame.NORMAL;
import static main.enums.TipoMenu.*;

@Getter
public class DadosGame {
    private final int width = 720;
    private final int height = 360;
    @Setter
    private int wordWidth;
    @Setter
    private int wordHeight;
    private final int tileSize = 64;
    private int scale = 2;

    private TipoMenu estadoMenu = INICIAL;
    private TipoMenu lastEstadoMenu = INICIAL;
    private TipoGame estadoGame = MENU;
    @Setter
    private TipoAmbiente local = TipoAmbiente.PERIMETRO_SUPERIOR;
    private boolean dia = true;
    private int rota = 1;

    private final String path = "/niveis/area1.png";

    @Setter
    private Player player = new Player(0, 0, tileSize, tileSize);

    @Setter
    private boolean musica = true;
    private Boolean efeitos = true;
    private Boolean idioma = true;
    private Integer volume = 0;

    public DadosGame() {
        //TODO carregar um game salvo ou criar um novo
    }


//    public void mudarEscala(int escala) {
//        if (escala == 2 || escala == 1) {
//            scale = escala;
//        }
//    }

//    public int rotear() {
//        rota++;
//        if (rota > 3) {
//            rota = 1;
//        }
//        return rota;
//
//    }

    public int getScaleWidth() {
        return width * scale;
    }

    public int getScaleHeight() {
        return height * scale;
    }

    public int getTileSize(int multiplyer) {
        return tileSize * multiplyer;
    }

    public void escPressed() {
        changeState(OPCOES, INICIAL);
        inventario();
    }

    public void tabPressed() {
        changeState(HABILIDADES, INICIAL, OPCOES);
    }

    private void changeState(TipoMenu novo, TipoMenu... menusDesabilitar) {
        if (NORMAL.equals(estadoGame) || Arrays.stream(menusDesabilitar).noneMatch(tipoMenu -> tipoMenu.equals(estadoMenu))) {
            estadoMenu = novo;
            switch (estadoGame) {
                case MENU:
                    estadoGame = NORMAL;
                    break;
                case NORMAL:
                    estadoGame = MENU;
                    break;
                case CUTSCENE:
                    break;
            }
        }
    }

    public void jogar() {
        estadoGame = NORMAL;
        changeEstadoMenu(lastEstadoMenu.equals(INICIAL) ? HABILIDADES : lastEstadoMenu);
    }

    public void carregarJogo() {
        estadoGame = MENU;
        changeEstadoMenu(LOAD);
    }

    public void configuracoes() {
        changeEstadoMenu(OPCOES);
    }

    public void home() {
        changeEstadoMenu(INICIAL);
    }

    public void habilidades() {
        changeEstadoMenu(HABILIDADES);
    }

    public void inventario() {
        changeEstadoMenu(INVENTARIO);
    }

    public void gameOver() {
        estadoGame = MENU;
        changeEstadoMenu(GAMEOVER);
    }

//    public String nextRota() {
//        String caminho = "";
//        if (rota == 1) {
//            caminho = "Industrial";
//        }
//        if (rota == 2) {
//            caminho = "Domiciliar";
//        }
//        if (rota == 3) {
//            caminho = "Hospitalar";
//        }
//        return caminho;
//
//    }

    private void changeEstadoMenu(TipoMenu novo) {
        lastEstadoMenu = estadoMenu;
        estadoMenu = novo;
    }
}
