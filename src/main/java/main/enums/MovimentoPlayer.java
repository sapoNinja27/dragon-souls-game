package main.enums;

import entidades.players.principal.Animacao;
import main.menu.graficos.Spritesheet;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public enum MovimentoPlayer {
    CAINDO(0, createAnimacao(8, "/personagens/tai/caindo.png")),
    SUBINDO(1, createAnimacao(12, "/personagens/tai/subindo.png")),
    POUSANDO(0, createAnimacao(0, "/personagens/tai/landing.png")),
    ANDANDO(0, createAnimacao(6, "/personagens/tai/correndo.png")),
    PARANDO(0, createAnimacao(0, "/personagens/tai/parando.png")),
    RESPIRANDO(0, createAnimacao(15, "/personagens/tai/respirando.png")),
    RESPIRANDO_EM_COMBATE(0, createAnimacao(15, "/personagens/tai/respirando_combat.png")),
    ATACANDO(1, createAnimacao(7, "/personagens/tai/atacando.png")),
    INVESTINDO(5, createAnimacao(15, "/personagens/tai/dash.png")),

    HABILIDADE_POSTURA_OFENSIVA(20, createAnimacao(10, "/personagens/tai/postura_ofensiva.png")),
    HABILIDADE_POSTURA_DEFENSIVA(1.5, createAnimacao(15, "/personagens/tai/respirando.png")),
    HABILIDADE_POSTURA_IRADA(8, createAnimacao(15, "/personagens/tai/respirando.png")),
    HABILIDADE_POSTURA_CALMA(14, createAnimacao(15, "/personagens/tai/respirando.png")),
    HABILIDADE_POSTURA_FIRME(2, createAnimacao(15, "/personagens/tai/respirando.png")),
    HABILIDADE_POSTURA_SELVAGEM(90, createAnimacao(15, "/personagens/tai/postura_selvagem.png"));

    @Getter
    private final Animacao animacao;
    @Getter
    private final double initialCooldown;

    MovimentoPlayer(double initialCooldown, Animacao animacao) {
        this.animacao = animacao;
        this.initialCooldown = initialCooldown;
    }

    private static Animacao createAnimacao(int frameMaximo, String path) {
        Spritesheet spritesheet = new Spritesheet(path);
        int quantidadeFrames = spritesheet.getWidth() / spritesheet.getHeight();
        int tileSize = spritesheet.getHeight();
        List<BufferedImage> frames = new ArrayList<>();
        for (int i = 0; i < quantidadeFrames; i++) {
            frames.add(spritesheet.getSprite(i * tileSize, 0, tileSize, tileSize));
        }
        return new Animacao(frameMaximo, frames.toArray(new BufferedImage[0]));
    }
}