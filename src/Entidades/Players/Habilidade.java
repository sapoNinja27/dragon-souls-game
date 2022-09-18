package Entidades.Players;

import Configuration.Configuracoes;
import Entidades.Players.Player;
import Graficos.Spritesheet;
import enums.AcaoPlayer;
import enums.MovimentoPlayer;
import jObjects.Botao;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static Menu.StringUtils.write;
import static Menu.ImageUtils.draw;

public class Habilidade {
	Botao botao = new Botao(0,0,64,64);
	protected Spritesheet sprite = new Spritesheet("/menus/Menu.png");
	private int nivel;
	private int melhoria;
	private final boolean basica;
	private BufferedImage icone;
	private String titulo;
	private String descricao;
	private MovimentoPlayer movimentoPlayer;

	private int x,y;
	public String getTitulo(){
		return titulo;
	}

	private List<BufferedImage> filhas = new ArrayList<>();

	private boolean montado;

	public void montar(List<BufferedImage> icones, String titulo, String descricao, MovimentoPlayer movimentoPlayer){
		if(!montado){
			this.titulo = titulo;
			this.descricao = descricao;
			this.movimentoPlayer = movimentoPlayer;
			icone = icones.get(0);
			if(icones.size() > 1){
				filhas = icones.subList(1, icones.size());
			}
			montado = true;
		}
	}

	public BufferedImage getIcone() {
		return icone;
	}


	public Habilidade(boolean basica) {
		this.basica = basica;
	}

	public Habilidade() {
		this.basica = false;
	}

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

	public boolean isBasica() {
		return basica;
	}
	public boolean isOver(){
		return botao.mouseOver();
	}
	public void render(Graphics g, int x, int y){
		botao.setXY(x,y);
		g.drawImage(icone, x, y, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
		botao.tick();
	}
	public void desenharInfo(int x, int y, Graphics g, Color bordaMenu, Color fundoMenu, Player player){
		g.setColor(Color.white);
		g.fillRect(x + 1050, y + 30, 300, Configuracoes.TILE_SIZE * 3);
		g.setColor(bordaMenu);
		g.drawRect(x + 1050, y + 30, 300, 500);
		g.setColor(fundoMenu);
		g.fillRect(x + 1050, y + 30, 300, 500);

		g.setColor(bordaMenu);
		g.drawRect(x + 1050, y + 30 + Configuracoes.TILE_SIZE * 3, 300, 50);
		g.setColor(fundoMenu);
		g.fillRect(x + 1050, y + 30 + Configuracoes.TILE_SIZE * 3, 300, 50);


		player.setarAnimacao(movimentoPlayer);
		g.drawImage(player.spriteAtual().get(player.index), x + 1110, y + 95, 64 * 2, 64 * 2, null);

		draw(g, titulo, x + 1075, y + 255, Color.WHITE, 20);

		write(g, descricao, 14, x, y);
	}
}
