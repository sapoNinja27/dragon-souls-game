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
}
