package Entidades.Players.tai;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import Configuration.Configuracoes;
import Entidades.Players.Habilidade;
import Entidades.Players.Player;
import Entidades.Players.tai.habilidades.Atacar;
import Menu.ImageUtils;
import Graficos.Spritesheet;
import enums.MovimentoPlayer;

import static Menu.ImageUtils.inverter;
import static Menu.StringUtils.write;

public class Tai extends Player {

	//basicas
	private Habilidade atacar = new Atacar();
	private Habilidade bloquear = new Habilidade(true);
	private Habilidade transformar = new Habilidade(true);

	//luva de metal
	private Habilidade upgrade = new Habilidade();
	private Habilidade ondaDeChoque = new Habilidade();
	private Habilidade tempestadeDeRaios = new Habilidade();
	private Habilidade pulsoGravitacional = new Habilidade();

	public Tai(int x, int y) {
		super(x, y);
	}
	/*
	 * define os sprites e animações do player
	 * */
	@Override
	public void atualizarSprites() {
		List<BufferedImage> respirando = new ArrayList<>();
		List<BufferedImage> correndo = new ArrayList<>();
		List<BufferedImage> pulando = new ArrayList<>();
		spritesheet = new Spritesheet("/personagens/tai.png");
		for (int i = 0; i < 35; i++) {
			if (respirando.size() < 4) {
				respirando.add(spritesheet.getSprite(
						Configuracoes.TILE_SIZE * i,
						0,
						Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE)
				);
			}
			if (correndo.size() < 9) {
				correndo.add(spritesheet.getSprite(
						Configuracoes.TILE_SIZE * i,
						Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE)
				);
			}
			if (pulando.size() < 6) {
				pulando.add(spritesheet.getSprite(
						Configuracoes.TILE_SIZE * i,
						Configuracoes.TILE_SIZE * 2,
						Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE)
				);
			}
		}
		spritesDireita.addAll(respirando);
		spritesDireita.addAll(correndo);
		spritesDireita.addAll(pulando);

		spritesEsquerda.addAll(inverter(spritesDireita));
		super.atualizarSprites();
	}
	@Override
	public List<Habilidade> getHabilidades(){

		;
		bloquear.montar(
				Collections.singletonList(sprite.getSprite(128,0,128,128)),
				"Bloquear",
				"Tai defende 70% do dano do proximo ataque",
				MovimentoPlayer.ANDANDO
		);
		transformar.montar(
				Collections.singletonList(sprite.getSprite(128 * 2,0,128,128)),
				"Transformar",
				"",
				MovimentoPlayer.ANDANDO
		);

		tempestadeDeRaios.montar(
				Collections.singletonList(sprite.getSprite(128 * 5,0,128,128)),
				"Postura calma",
				"",
				MovimentoPlayer.ANDANDO
		);
		pulsoGravitacional.montar(
				Collections.singletonList(sprite.getSprite(128 * 5,0,128,128)),
				"Pulso gravitacional",
				"",
				MovimentoPlayer.ANDANDO
		);
		upgrade.montar(
				Collections.singletonList(sprite.getSprite(128 * 3,0,128,128)),
				"Tempestade de raios",
				"",
				MovimentoPlayer.ANDANDO
		);
		ondaDeChoque.montar(
				Collections.singletonList(sprite.getSprite(128 * 4,0,128,128)),
				"Onda de choque",
				"",
				MovimentoPlayer.ANDANDO
		);
		return Arrays.asList(atacar,bloquear, transformar, upgrade, ondaDeChoque, tempestadeDeRaios, pulsoGravitacional);
	}

	@Override
	public HashMap<String, Color> getCoresSet(){
		HashMap<String, Color> cores = new HashMap<>();
		cores.put("bordaMenu", new Color(173, 8, 0));
		cores.put("fundoMenu", new Color(173, 8, 0, 34));


		cores.put("corBarraVida", new Color(189, 21, 0));
		cores.put("corBarraVidaVazia", new Color(45, 6, 0));
		cores.put("corBarraMana", new Color(206, 126, 0));
		cores.put("corBarraManaVazia", new Color(100, 57, 0));

		return cores;
	}

	@Override
	public String tipoPlayer(){
		return "Tai";
	}

	@Override
	public BufferedImage getIcone(){
		return icone[0];
	}
//	public void punchMeteor() {
//		if (furia > 61) {
//			defesa=0;
//			speed = 0;
//			index = indexH1;
//			framesH1++;
//			if (framesH1 >= 10) {
//				framesH1 = 0;
//				indexH1++;
//				if (indexH1 > 41) {
//					indexH1 = 36;
////					furia -= 60;
//					parado = true;
//				}
//			}
//		} else {
//			parado = true;
//		}
//	}
	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
//		Sombras(g, direcao);
//		g.drawImage(direcao[index], this.getX() + posicao - Camera.x, this.getY() - Camera.y, null);
//		sombrear(g, direcao);
//		g.setColor(Color.red);
////		g.drawRect(
////				this.getX() - Camera.x + mascaras.get(0).getPosicaoX(),
////				this.getY() - Camera.y + mascaras.get(0).getPosicaoY(),
////				mascaras.get(0).getAutura(),
////				mascaras.get(0).getLargura());
//		g.setColor(Color.BLUE);
	}
	@Override
	public void drawHud(Graphics g) {
		g.setColor(new Color(16, 16, 16));
		g.fillRect(95, 84, 101, 4);
		g.setColor(new Color(194, 194, 194));
		g.fillRect(95, 84, 101, 2);
	}

	@Override
	public void desenharInfo(int x, int y, Graphics g, Color bordaMenu, Color fundoMenu){
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


		setarAnimacao(MovimentoPlayer.RESPIRANDO);
		g.drawImage(spriteAtual().get(index), x + 1110, y + 95, 64 * 2, 64 * 2, null);

		ImageUtils.draw(g, "Tai", x + 1075, y + 255, Color.WHITE, 20);
		String descricao = "Tai aumenta sua furia em combate com inimigos, acumulos de furia concedem a ele 15% da furia atual como resistencias, 10 de armadura e 30 de dano de ataque, ao atingir o maximo de furia os efeitos decaem ate acabarem";
		write(g, descricao, 14, x, y);
	}
}
