package menu;

import configuracoes.Configuracao;
import entidades.players.Habilidade;
import entidades.players.Player;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static utils.ImageUtils.draw;
import static utils.ImageUtils.fill;

public class MenuHabilidades {
	private Color bordaMenu, fundoMenu;
	private Player player;

	public void atualizarPlayer(Player player){
		this.player = player;
	}
	public void tick(){
		bordaMenu = player.getCoresSet().get("bordaMenu");
		fundoMenu = player.getCoresSet().get("fundoMenu");
	}
	public void render(Graphics g) {
		int x = 60;
		int y = 100;

		desenharTextosInfo(x, y, g);
		desenharFundoMenu(x, y, g);
		desenharInfo(x, y, g);
		g.setColor(bordaMenu);
		g.drawRect(x, y + 30, 1000, 50);
		g.setColor(fundoMenu);
		g.fillRect(x, y + 30, 1000, 50);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString("Basico", x + 30, y + 65);

		g.setColor(bordaMenu);
		g.drawRect(x, y + 30 + 200, 1000, 50);
		g.setColor(fundoMenu);
		g.fillRect(x, y  + 30 + 200, 1000, 50);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString("Avançado", x + 30, y + 265);

		List<Habilidade> basicas = player.getHabilidades().stream().filter(Habilidade::isBasica).collect(Collectors.toList());
		List<Habilidade> avancadas = player.getHabilidades().stream().filter(habilidade -> !habilidade.isBasica()).collect(Collectors.toList());
		for(int i = 0; i < basicas.size(); i++){
			basicas.get(i).setXY(120 + (i *20) + (i * 1000/basicas.size()), 220);
			basicas.get(i).render(g);
		}
		for(int i = 0; i < avancadas.size(); i++){
			avancadas.get(i).setXY(120 + (i *20) + (i * 1000/avancadas.size()), 420);
			avancadas.get(i).render(g);
		}
	}
	private void desenharTextosInfo(int x, int y, Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 30));
		g.drawString("Habilidades", x, y - 40);

		g.setFont(new Font("arial", Font.BOLD, 11));
		g.drawString(player.tipoPlayer() + " nível " + player.getNivel(), x, y - 15);
		g.drawString("Pontos de habilidade " + player.getPontosHabilidade(), x, y + 5);
		g.drawString("XP", x, y + 25);
		g.drawRect(x + 25, y + 17, 100, 8);
		g.fillRect(x + 25, y + 17, player.getXp(), 8);
	}

	private void desenharFundoMenu(int x, int y, Graphics g){
		g.setColor(bordaMenu);
		g.drawRect(x, y + 30, 1000, 500);
		g.setColor(fundoMenu);
		g.fillRect(x, y + 30, 1000, 500);
	}

	private void desenharInfo(int x, int y, Graphics g){
		desenharLayoutDescricaoHabilidade(x, y, g);
		if(noneSelected(player.getHabilidades())){
			player.desenharInfo(x, y, g);
		} else {
			getSelected(player.getHabilidades()).desenharInfo(x, y, g, player);
		}
	}

	private boolean noneSelected(List<Habilidade> list){
		return list.stream().noneMatch(Habilidade::isOver);
	}

	private Habilidade getSelected(List<Habilidade> list){
		return list.stream().filter(Habilidade::isOver).findFirst().orElse(null);
	}

	private void desenharLayoutDescricaoHabilidade(int x, int y, Graphics g){
		fill(g, x + 1050, y + 30, Color.WHITE, 300, Configuracao.TILE_SIZE * 3);
		draw(g, x + 1050, y + 30, bordaMenu, 300, 500);
		fill(g, x + 1050, y + 30, fundoMenu, 300, 500);
		draw(g, x + 1050, y + 30 + Configuracao.TILE_SIZE * 3, bordaMenu, 300, 50);
		fill(g, x + 1050, y + 30 + Configuracao.TILE_SIZE * 3, fundoMenu, 300, 50);
	}
}
