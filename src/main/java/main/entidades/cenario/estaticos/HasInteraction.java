package main.entidades.cenario.estaticos;

import main.DadosGame;
import main.entidades.Entidade;

import java.util.List;

public interface HasInteraction {

    public boolean applyInteraction(DadosGame dadosGame, List<Entidade> list);
}
