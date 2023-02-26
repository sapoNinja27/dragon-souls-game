package configuracoes;

import entidades.players.Player;
import entidades.players.tai.Tai;
import enums.TipoAmbiente;
import enums.TipoGame;
import enums.TipoMenu;
import lombok.Getter;
import lombok.Setter;

import static enums.TipoGame.MENU;
import static enums.TipoGame.NORMAL;
import static enums.TipoMenu.*;

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

    private TipoMenu estadoMenu = HABILIDADES;
    private TipoMenu lastEstadoMenu = TipoMenu.INICIAL;
    private TipoGame estadoGame = MENU;
    private TipoAmbiente local = TipoAmbiente.RUA;
    private boolean dia = false;
    private int rota = 1;

    private final String path = "/niveis/area1.png";

    @Setter
    private Player player = new Tai(0, 0, this);

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
        if (!estadoMenu.equals(TipoMenu.INICIAL)) {
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
