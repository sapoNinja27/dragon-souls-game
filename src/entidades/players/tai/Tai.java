package entidades.players.tai;

import configuracoes.Configuracao;
import entidades.players.Habilidade;
import entidades.players.Player;
import entidades.players.tai.habilidades.*;
import graficos.Spritesheet;
import utils.ImageUtils;
import enums.MovimentoPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static utils.ImageUtils.inverter;
import static utils.StringUtils.write;

public class Tai extends Player {

	//basicas
	private Habilidade atacar = new PosturaOfensiva();
	private Habilidade bloquear = new PosturaDefensiva();
	private Habilidade transformar = new PosturaSelvagem();

	//luva de metal
	private Habilidade tempestadeDeRaios = new TempestadeEletrica();
	private Habilidade upgrade = new PosturaCalma();
	private Habilidade ondaDeChoque = new Energizar();
	private Habilidade pulsoGravitacional = new PosturaFirme();

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
						Configuracao.TILE_SIZE * i,
						0,
						Configuracao.TILE_SIZE,
						Configuracao.TILE_SIZE)
				);
			}
			if (correndo.size() < 9) {
				correndo.add(spritesheet.getSprite(
						Configuracao.TILE_SIZE * i,
						Configuracao.TILE_SIZE,
						Configuracao.TILE_SIZE,
						Configuracao.TILE_SIZE)
				);
			}
			if (pulando.size() < 6) {
				pulando.add(spritesheet.getSprite(
						Configuracao.TILE_SIZE * i,
						Configuracao.TILE_SIZE * 2,
						Configuracao.TILE_SIZE, Configuracao.TILE_SIZE)
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
	public void desenharInfo(int x, int y, Graphics g){
		setarAnimacao(MovimentoPlayer.RESPIRANDO);
		g.drawImage(spriteAtual().get(index), x + 1110, y + 95, 64 * 2, 64 * 2, null);
		ImageUtils.draw(g, "Tai", x + 1075, y + 255, Color.WHITE, 20);
		String descricao = "Tai aumenta sua furia em combate com inimigos, acumulos de furia concedem a ele 15% da furia atual como resistencias, 10 de armadura e 30 de dano de ataque, ao atingir o maximo de furia os efeitos decaem ate acabarem";
		write(g, descricao, 14, x + 1075, y + 255);
	}
}
