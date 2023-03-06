package main.entidades.players;

import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class Animacao {

    private int frames = 0;
    private int index = 0;
    private final int frameMaximo;
    private final BufferedImage[] sprite;

    public void tick(Runnable... aditionalAction) {
        frames++;
        if (frames >= frameMaximo) {
            index++;
            frames = 0;
        }
        if (index >= sprite.length) {
            index = 0;
            for (Runnable acao : aditionalAction) {
                acao.run();
            }
        }
    }
    public void restartAnm(){
        frames = 0;
        index = 0;
    }

    public BufferedImage getSprite() {
        return sprite[index];
    }
}