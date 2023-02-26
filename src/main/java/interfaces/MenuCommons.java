package interfaces;

import entidades.players.Habilidade;
import entidades.players.Player;

import java.awt.*;
import java.util.List;

public interface MenuCommons {
	default void desenharInfo(int x, int y, Graphics g){}
	default List<Habilidade> getHabilidades(){
		return null;
	}
}
