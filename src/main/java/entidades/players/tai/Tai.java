package entidades.players.tai;

import configuracoes.DadosGame;
import entidades.players.Habilidade;
import entidades.players.Player;
import entidades.players.tai.habilidades.*;
import enums.MovimentoPlayer;
import graficos.Spritesheet;
import utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.ImageUtils.inverter;
import static utils.StringUtils.write;

public class Tai extends Player {

    //basicas
    private Habilidade atacar = new PosturaOfensiva();
    private Habilidade bloquear = new PosturaDefensiva();
    private Habilidade transformar = new PosturaSelvagem();

    //luva de metal
    private Habilidade upgrade = new PosturaCalma();
    private Habilidade ondaDeChoque = new PosturaIrritada();
    private Habilidade pulsoGravitacional = new PosturaFirme();

    public Tai(int x, int y, DadosGame dadosGame) {
        super(x, y, dadosGame);
    }

    /*
     * define os sprites e animações do player
     * */
    public void atualizarSprites(DadosGame dadosGame) {
        int tileSize = dadosGame.getTileSize();
        List<BufferedImage> respirando = new ArrayList<>();
        List<BufferedImage> correndo = new ArrayList<>();
        List<BufferedImage> pulando = new ArrayList<>();
        spritesheet = new Spritesheet("/personagens/tai.png");
        for (int i = 0; i < 35; i++) {
            if (respirando.size() < 4) {
                respirando.add(spritesheet.getSprite(dadosGame.getTileSize(i), 0, tileSize, tileSize));
            }
            if (correndo.size() < 9) {
                correndo.add(spritesheet.getSprite(dadosGame.getTileSize(i), tileSize, tileSize, tileSize));
            }
            if (pulando.size() < 6) {
                pulando.add(spritesheet.getSprite(dadosGame.getTileSize(i), dadosGame.getTileSize(2), tileSize, tileSize));
            }
        }
        spritesDireita.addAll(respirando);
        spritesDireita.addAll(correndo);
        spritesDireita.addAll(pulando);

        spritesEsquerda.addAll(inverter(spritesDireita));
    }

    @Override
    public List<Habilidade> getHabilidades() {
        return Arrays.asList(atacar, bloquear, transformar, upgrade, ondaDeChoque, pulsoGravitacional);
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
    public void desenharInfo(int x, int y, Graphics g) {
        setarAnimacao(MovimentoPlayer.RESPIRANDO);
        g.drawImage(spriteAtual().get(index), x + 1110, y + 40, 64 * 2, 64 * 2, null);
        ImageUtils.draw(g, "Tai", x + 1075, y + 255, Color.WHITE, 20);
        String descricao = "Tai aumenta sua furia em combate com inimigos, acumulos de furia concedem a ele 15% da furia atual como resistencias, 10 de armadura e 30 de dano de ataque, ao atingir o maximo de furia os efeitos decaem ate acabarem";
        write(g, descricao, 14, x + 1075, y + 255);
    }
}
