package main.interfaces.mouse;


import lombok.Getter;
import main.utils.Spritesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
public class Mouse {
    private int x, y;
    private boolean over = false;
    private boolean alterouMouseIcon;
    private MouseState mouseState = MouseState.DEFAULT;
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
        mouseState = MouseState.MOVED;
    }

    public void mousePressed() {
        mouseState = MouseState.PRESSED;
    }

    public void mouseReleased(int x, int y) {
        setCordinates(x, y);
        mouseState = MouseState.RELEASED;
    }

    public void mouseEntered() {
        changeMouseOver(true);
    }

    public void mouseExited() {
        changeMouseOver(false);
    }

    public void mouseDragged(int x, int y) {
        setCordinates(x, y);
        mouseState = MouseState.DRAGGED;
    }

    public void mouseClicked(int x, int y) {
        setCordinates(x, y);
        mouseState = MouseState.CLICKED;
    }

    public boolean isClicked() {
        if(mouseState.equals(MouseState.CLICKED)){
            mouseState = MouseState.MOVED;
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
