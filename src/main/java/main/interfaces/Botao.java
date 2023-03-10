package main.interfaces;

import main.interfaces.mouse.Mouse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import main.enums.TipoFonte;
import main.utils.Fontes;
import main.utils.ImageUtils;
import main.utils.MascaraUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

import static main.interfaces.Botao.State.*;
import static java.util.Objects.nonNull;

@Setter
@Getter
@Builder
public class Botao {
    private int x;
    private int y;
    private int width;
    private int height;
    private int arcWidth;
    private int arcHeight;
    private Color cor;
    private String texto;
    private int borda;
    private int spacingX, spacingY;
    private int mx, my;
    private boolean lastBotao;
    private boolean custom;
    private BufferedImage image;
    private Polygon mascara;
    private Runnable acao;
    @Builder.Default
    private int font = 19;
    @Builder.Default
    private State state = STANDART;

    private void changeState(State state) {
        if (OVER_PRESSED.equals(this.state)) {
            if (STANDART.equals(state)) {
                this.state = state;
            }
        } else {
            this.state = state;
        }
    }

    public void tick(Mouse mouse) {
        mx = mouse.getX();
        my = mouse.getY();

        if (isOver(mx, my)) {
            mouse.mouseEntered();
            changeState(OVER);
            lastBotao = true;
            switch (mouse.getMouseState()) {
                case PRESSED:
                case DRAGGED:
                    changeState(OVER_PRESSED);
                    lastBotao = true;
                    break;
            }
            verificarAcaoOnClick(mouse);
        } else {
            if (lastBotao) {
                changeState(STANDART);
                mouse.mouseExited();
                lastBotao = false;
            }
        }
    }

    private void verificarAcaoOnClick(Mouse mouse) {
        if (mouse.isClicked()) {
            if (nonNull(acao)) {
                acao.run();
            }
        }
    }

    public void render(Graphics g) {
        if (!custom) {
            g.setColor(Color.black);
            g.fillRoundRect(x - borda, y - borda, width + borda * 2, height + borda * 2, arcWidth, arcHeight);
            g.setColor(cor);
            g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
            switch (state) {
                case STANDART:
                    g.setColor(new Color(0, 0, 0, 0));
                    break;
                case OVER:
                    g.setColor(new Color(0, 0, 0, 60));
                    break;
                case OVER_PRESSED:
                    g.setColor(new Color(0, 0, 0, 120));
                    break;
            }
            g.fillRoundRect(x - borda, y - borda, width + borda * 2, height + borda * 2, arcWidth, arcHeight);
            g.setColor(Color.black);
            g.setFont(Fontes.CrimsonText(TipoFonte.BOLD, font));
            g.drawString(Optional.ofNullable(texto).orElse(""), x + spacingX, y + spacingY);
        } else {
            g.drawImage(image, x, y, width, height, null);
            switch (state) {
                case OVER:
                    g.drawImage(ImageUtils.sombreamento(image, new Color(0, 0, 0, 60)), x, y, width, height, null);
                    break;
                case OVER_PRESSED:
                    g.drawImage(ImageUtils.sombreamento(image, new Color(0, 0, 0, 120)), x, y, width, height, null);
                    break;
            }
        }
    }

    private boolean isOver(int mx, int my) {
        if (!custom) {
            return mx > x && mx < x + width && my > y && my < y + height;
        } else {
            return MascaraUtils.isColiding(mx, my, mascara);
        }
    }
    public boolean isOver(){
        return state.equals(OVER) || state.equals(OVER_PRESSED);
    }

    enum State {
        OVER,
        OVER_PRESSED,
        STANDART
    }
}
