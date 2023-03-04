package jObjects;

import jObjects.Mouse.Mouse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Setter
@Getter
@Builder
public class Botao {
    private int x;
    private int y;
    private int altura;
    private int largura;
    private int arcoAltura;
    private int arcoLargura;
    private Color cor;
    private String texto;
    private boolean mouseOver;
    private boolean mousePressed;
    private boolean overPressed;
    private int borda;
    private int spacingX, spacingY;
    private boolean clicked;
    private int mx, my;
    private boolean lastBotao;
    private Runnable acao;

    public boolean isClicked() {
        if (clicked) {
            clicked = false;
            return true;
        }
        return false;
    }

    public void tick() {
        if (isClicked() && nonNull(acao)) {
            acao.run();
        }
        mx = Mouse.getX();
        my = Mouse.getY();
        if (mouseOver) {
            Mouse.over = true;
        } else {
            if (lastBotao) {
                Mouse.over = false;
                lastBotao = false;
            }
        }
        if (Mouse.pressed) {
            if (mx > x && mx < x + altura && my > y && my < y + largura) {
                overPressed = true;
                mousePressed = true;
                lastBotao = true;
            } else {
                overPressed = false;
                mouseOver = false;
            }
        } else if (Mouse.released) {
            mousePressed = false;
            if (isOver(mx, my)) {
                if (overPressed) {
                    Mouse.released = false;
                    overPressed = false;
                    Mouse.over = false;
                    clicked = true;
                }
                mouseOver = true;
            } else {
                if (overPressed) {
                    Mouse.released = false;
                    overPressed = false;
                }
                mouseOver = false;
            }
        } else {
            if (isOver(mx, my)) {
                mouseOver = true;
                lastBotao = true;
            } else {
                mouseOver = false;
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.black);
        g.fillRoundRect(x - borda, y - borda, altura + borda * 2, largura + borda * 2, arcoAltura, arcoLargura);
        g.setColor(cor);
        g.fillRoundRect(x, y, altura, largura, arcoAltura, arcoLargura);
        g.setColor(Color.black);
        if (mousePressed) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        } else if (mouseOver) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        } else {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
        }
        g.fillRoundRect(x - borda, y - borda, altura + borda * 2, largura + borda * 2, arcoAltura, arcoLargura);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.BOLD, 18));
        g.drawString(Optional.ofNullable(texto).orElse(""), (x + spacingX), y + spacingY);

    }

    private boolean isOver(int mx, int my) {
        return mx > x && mx < x + altura && my > y && my < y + largura;
    }
}
