package entidades.players;

import configuracoes.Configuracao;
import graficos.Spritesheet;
import interfaces.HabilidadesCommons;
import interfaces.MenuCommons;
import jObjects.Botao;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static utils.ImageUtils.draw;
import static utils.StringUtils.write;

public class Habilidade implements HabilidadesCommons, MenuCommons {
	Botao botao = new Botao(0,0,64,64);
	protected Spritesheet sprite = new Spritesheet("/menus/Menu.png");
	private int nivel;
	private int melhoria;
	private final List<BufferedImage> filhas = new ArrayList<>();

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getMelhoria() {
		return melhoria;
	}

	public void setMelhoria(int melhoria) {
		this.melhoria = melhoria;
	}

	public boolean isOver(){
		return botao.mouseOver();
	}

	public void render(Graphics g, int x, int y){
		botao.setXY(x,y);
		g.drawImage(getIcone(), x, y, Configuracao.TILE_SIZE, Configuracao.TILE_SIZE, null);
		botao.tick();
	}

	@Override
	public void desenharInfo(int x, int y, Graphics g, Player player){
		player.setarAnimacao(getMovimentoPlayer());
		g.drawImage(player.spriteAtual().get(player.index), x + 1110, y + 95, 64 * 2, 64 * 2, null);

		draw(g, getTitulo(), x + 1075, y + 255, Color.WHITE, 20);

		write(g, getDescricao(), 14, x + 1075, y + 255);

		draw(g, getCusto(), x + 1075, y + 500, Color.YELLOW, 10);

//		draw(g, "45", x + 1300, y + 500, Color.orange, 10);
	}
}
