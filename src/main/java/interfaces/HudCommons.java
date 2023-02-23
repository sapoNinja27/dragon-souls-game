package interfaces;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public interface HudCommons {

	default void drawHud(Graphics g) {}
	default void atualizarSprites(){}
	default HashMap<String, Color> getCoresSet(){
		return null;
	}
	default BufferedImage getIcone(){
		return null;
	}
}
