package main;

import com.google.gson.Gson;
import main.entidades.Entidade;
import main.entidades.players.Player;
import lombok.Getter;
import lombok.Setter;
import main.enums.SaveSlotEncripto;
import main.enums.TipoAmbiente;
import main.enums.TipoGame;
import main.enums.TipoMenu;
import main.processamento.GerenciadorSave;
import main.world.World;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;
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
    private final List<Entidade> entities = new ArrayList<>();
    private final GerenciadorSave gerenciadorSave = new GerenciadorSave();
    private final World world = new World();

    private TipoMenu estadoMenu = INICIAL;
    private TipoMenu lastEstadoMenu = INICIAL;
    private TipoGame estadoGame = MENU;
    @Setter
    private TipoAmbiente local = TipoAmbiente.PERIMETRO_SUPERIOR;
    private boolean dia = true;
    private int rota = 1;

    private String path = "/niveis/area1.png";

    @Setter
    private Player player = new Player(0, 0, tileSize, tileSize);
    private SaveGameDto saveGameDto;
    private boolean loaded = false;

    @Setter
    private boolean musica = true;
    private Boolean efeitos = true;
    private Boolean idioma = true;
    private Integer volume = 0;

    public DadosGame() {
        //TODO carregar um game salvo ou criar um novo
    }

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
        if (estadoGame.equals(MENU) && lastEstadoMenu.equals(INICIAL)) {
            switch (estadoMenu) {
                case LOAD:
                case OPCOES:
                    estadoMenu = INICIAL;
                    break;
            }
        } else {
            changeState(OPCOES, INICIAL);
            inventario();
        }
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
        world.startGame(this, entities);
        estadoGame = NORMAL;
        changeEstadoMenu(lastEstadoMenu.equals(INICIAL) ? HABILIDADES : lastEstadoMenu);
    }

    public void saveGame(int encode) {
        encode = 9;
        gerenciadorSave.saveGame(this, encode);
    }

    public void carregarJogo(File file, int encode) {
        encode = 9;
        saveGameDto = gerenciadorSave.carregarJogo(file, encode);
        if (nonNull(saveGameDto)) {
            player = saveGameDto.getPlayer(this);
            local = saveGameDto.getLocal();
            dia = saveGameDto.isDia();
            path = saveGameDto.getPathArea();
            loaded = true;
        }
        jogar();
    }

    public void menuLoad() {
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

    private void changeEstadoMenu(TipoMenu novo) {
        lastEstadoMenu = estadoMenu;
        estadoMenu = novo;
    }

    public SaveGameDto createSave() {
        return SaveGameDto.builder()
                .dia(dia)
                .pathArea(path)
                .playerDto(new SaveGameDto.PlayerDto(player))
                .local(local)
                .build();
    }
}
