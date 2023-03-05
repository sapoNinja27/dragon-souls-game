package jObjects.Mouse;


import lombok.Getter;
import main.menu.graficos.Spritesheet;
import main.utils.MascaraUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static jObjects.Mouse.MouseState.*;

@Getter
public class Mouse {
    private int x, y;
    private boolean over = false;
    private boolean alterouMouseIcon;
    private MouseState mouseState = DEFAULT;
    private final BufferedImage[] cursor;

    public Mouse() {
        Spritesheet spritesheet = new Spritesheet("/cursor.png");
        cursor = new BufferedImage[]{
                spritesheet.getSprite(32, 0, 32, 32),
                spritesheet.getSprite(0, 0, 32, 32)
        };
    }

    public void setCordinates(int nx, int ny) {
        x = nx + 10;
        y = ny + 5;
    }

    public void tick(JFrame frame) {
        if(alterouMouseIcon){
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            if (over) {
                Image image = getCursor()[0];
                Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
                frame.setCursor(c);
            } else {
                Image image = getCursor()[1];
                Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
                frame.setCursor(c);
            }
            alterouMouseIcon = false;
        }
    }

    public void mouseMoved(int x, int y) {
        setCordinates(x, y);
        mouseState = MOVED;
    }

    public void mousePressed() {
        mouseState = PRESSED;
    }

    public void mouseReleased(int x, int y) {
        setCordinates(x, y);
        mouseState = RELEASED;
    }

    public void mouseEntered() {
        changeMouseOver(true);
    }

    public void mouseExited() {
        changeMouseOver(false);
    }

    public void mouseDragged(int x, int y) {
        setCordinates(x, y);
        mouseState = DRAGGED;
    }

    public void mouseClicked(int x, int y) {
        setCordinates(x, y);
        mouseState = CLICKED;
    }

    public boolean isClicked() {
        if(mouseState.equals(CLICKED)){
            mouseState = MOVED;
            changeMouseOver(false);
            return true;
        }
        return false;
    }
    private void changeMouseOver(boolean over){
        alterouMouseIcon = true;
        this.over = over;
    }
}
