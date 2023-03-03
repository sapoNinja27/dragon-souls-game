package entidades.players;

import configuracoes.DadosGame;
import entidades.Entidade;
import entidades.cenario.objetosluminosos.ObjetoLuminoso;
import entidades.mascaras.MascaraHitBox;
import entidades.players.tai.Tai;
import enums.*;
import graficos.Spritesheet;
import graficos.UI;
import interfaces.HudCommons;
import interfaces.MenuCommons;
import lombok.RequiredArgsConstructor;
import utils.FonteUtils;
import utils.ImageUtils;
import world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static enums.MovimentoPlayer.*;
import static java.util.Objects.nonNull;
import static utils.ImageUtils.draw;
import static utils.StringUtils.write;

@RequiredArgsConstructor
public class Animacao  {

    private int frames = 0;
    private int index = 0;
    private final int frameMaximo;
    private final int indexMaximo;
    private final BufferedImage[] sprite;


    public void tick(){
        if (frames >= frameMaximo) {
            index++;
            frames = 0;
        }
        if (index >= indexMaximo) {
            index = 13;
        }
    }


    public void render(Graphics g, int x, int y){
        g.drawImage(sprite[index], x, y, null);
    }
}
