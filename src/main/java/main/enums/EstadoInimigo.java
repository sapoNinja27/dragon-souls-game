package main.enums;

import lombok.Getter;
import main.entidades.players.Animacao;
import main.utils.Spritesheet;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public enum EstadoInimigo {
    ATAQUE_1(0, createAnimacao(8, "/inimigos/inimigo/ataque1.png")),
    ATAQUE_2(1, createAnimacao(12, "/inimigos/inimigo/ataque2.png")),
    ANDANDO(0, createAnimacao(6, "/inimigos/inimigo/correndo.png"));


    @Getter
    private final Animacao animacao;
    @Getter
    private final double initialCooldown;

    EstadoInimigo(double initialCooldown, Animacao animacao) {
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