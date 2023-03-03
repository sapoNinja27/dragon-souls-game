package enums;

import configuracoes.DadosGame;
import entidades.players.Animacao;
import graficos.Spritesheet;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static utils.ImageUtils.inverter;

public enum MovimentoPlayer {
    CAINDO(
            createAnimacao(0, 2, null)
    ),
    SUBINDO(
            createAnimacao(20, 3, null)
    ),


//    private void pular() {
//        index = Math.max(index, 13);
//        frames++;
//        limiteAltura += 4;
//        this.y -= 4;
//        if (frames >= 12) {
//            index++;
//            frames = 0;
//        }
//        if (index >= 15) {
//            index = 13;
//        }
//    }

    POUSANDO(
            createAnimacao(0, 1, null)
    ),
    ANDANDO(
            createAnimacao(0, 8, null)
    ),
    PARANDO(
            createAnimacao(0, 1, null)
    ),
    ATACANDO(
            createAnimacao(0, 0, null)
    ),
    RESPIRANDO(
            createAnimacao(20, 4, null)
    ),
    RESPIRANDO_EM_COMBATE(
            createAnimacao(0, 0, null)
    ),
    INVESTINDO(
            createAnimacao(0, 0, null)
    ),

    HABILIDADE_POSTURA_OFENSIVA(
            createAnimacao(0, 0, null)
    ),
    HABILIDADE_POSTURA_DEFENSIVA(
            createAnimacao(0, 0, null)
    ),
    HABILIDADE_POSTURA_IRADA(
            createAnimacao(0, 0, null)
    ),
    HABILIDADE_POSTURA_CALMA(
            createAnimacao(0, 0, null)
    ),
    HABILIDADE_POSTURA_FIRME(
            createAnimacao(0, 0, null)
    ),
    HABILIDADE_POSTURA_SELVAGEM(
            createAnimacao(0, 0, null)
    );

    @Getter
    private final Animacao animacao;
    private final Runnable[] acoes;

    MovimentoPlayer(Animacao animacao, Runnable... acoes) {
        this.acoes = acoes;
        this.animacao = animacao;
    }

    private static Animacao createAnimacao(int frameMaximo, int indexMaximo, String path) {
        Spritesheet spritesheet = new Spritesheet("/personagens/tai.png");//new Spritesheet(path);
        int quantidadeFrames = spritesheet.getHeight() / spritesheet.getWidth();
        int tileSize = 64;//spritesheet.getWidth();
        List<BufferedImage> frames = new ArrayList<>();
        for (int i = 0; i < quantidadeFrames; i++) {
            frames.add(spritesheet.getSprite(i * tileSize, 0, tileSize, tileSize));
        }
        return new Animacao(frameMaximo, indexMaximo, frames.toArray(new BufferedImage[0]));
    }
}

//    public void atualizarSprites(DadosGame dadosGame) {
//        int tileSize = dadosGame.getTileSize();
//        List<BufferedImage> respirando = new ArrayList<>();
//        List<BufferedImage> correndo = new ArrayList<>();
//        List<BufferedImage> pulando = new ArrayList<>();
//        spritesheet = new Spritesheet("/personagens/tai.png");
//        for (int i = 0; i < 35; i++) {
//            if (respirando.size() < 4) {
//                respirando.add(spritesheet.getSprite(dadosGame.getTileSize(i), 0, tileSize, tileSize));
//            }
//            if (correndo.size() < 9) {
//                correndo.add(spritesheet.getSprite(dadosGame.getTileSize(i), tileSize, tileSize, tileSize));
//            }
//            if (pulando.size() < 6) {
//                pulando.add(spritesheet.getSprite(dadosGame.getTileSize(i), dadosGame.getTileSize(2), tileSize, tileSize));
//            }
//        }
//        spritesDireita.addAll(respirando);
//        spritesDireita.addAll(correndo);
//        spritesDireita.addAll(pulando);
//
//        spritesEsquerda.addAll(inverter(spritesDireita));
//
