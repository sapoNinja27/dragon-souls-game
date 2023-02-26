package interfaces;

import configuracoes.DadosGame;
import entidades.players.Habilidade;
import entidades.players.Player;

import java.awt.*;
import java.util.List;

public interface MenuCommons {
	default List<Habilidade> getHabilidades(){
		return null;
	}
}
