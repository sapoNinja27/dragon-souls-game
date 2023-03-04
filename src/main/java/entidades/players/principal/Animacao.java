package entidades.players.principal;

import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class Animacao {

    private int frames = 0;
    private int index = 0;
    private final int frameMaximo;
    private final int indexMaximo;
    private final BufferedImage[] sprite;

    public void tick(Runnable... aditionalAction) {
        frames++;
        if (frames >= frameMaximo) {
            index++;
            frames = 0;
        }
        if (index >= indexMaximo) {
            index = 0;
            for (Runnable acao : aditionalAction) {
                acao.run();
            }
        }
    }

    public BufferedImage getSprite() {
        return sprite[index];
    }
}
