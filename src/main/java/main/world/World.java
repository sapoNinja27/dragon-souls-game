package main.world;

import main.entidades.cenario.Fundo;
import main.entidades.cenario.LimiteCenario;
import main.entidades.cenario.estaticos.LataLixo;
import main.entidades.cenario.estaticos.plataformas.Bueiro;
import main.entidades.cenario.estaticos.plataformas.CanoEsgoto;
import main.entidades.cenario.estaticos.plataformas.Plataforma;
import main.entidades.cenario.estaticos.portas.EscadaEsgoto;
import main.entidades.cenario.estaticos.portas.Portao;
import main.DadosGame;
import entidades.cenario.limitescenario.LimiteDeCenarioAbismo;
import entidades.cenario.limitescenario.ParedeInvisivel;
import main.entidades.cenario.iluminacao.Ilminacao;
import main.entidades.cenario.iluminacao.Poste;
import main.entidades.players.Player;
import main.enums.TipoIluminacao;
import main.utils.Spritesheet;
import main.processamento.GerenciadorEntidades;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class World {
    private int tileSize;
    private final Spritesheet cenario = new Spritesheet("/cenario/cenario.png");
//    private final Construcao[] construcoes = {
//            new Construcao(1, 0, 7, 7,
//                    new Spritesheet("/cenario/predioBranco.png")),
//            new Construcao(3, 0, 0, 9,
//                    new Spritesheet("/cenario/stone.png")),
//            new Construcao(8, 0, 0, 7,
//                    new Spritesheet("/cenario/predioBranco.png"))
//    };
//	private Predio[] predios2 = {
//			new Predio(2,0, 0, 6, 4, Texturas.MARMORE),
//			new Predio(4,0, 0, 25, 4, Texturas.MARMORE),
//			new Predio(5,0, 0, 12, 4, Texturas.MARMORE), 
//			new Predio(6,0, 0, 11, 4, Texturas.MARMORE),
//			new Predio(7,0, 0, 32, 4, Texturas.MARMORE), 
//			new Predio(9,0, 0, 7, 4, Texturas.MARMORE)
//	};

    private void fundo(GerenciadorEntidades gerenciadorEntidades, DadosGame dadosGame) {
        Fundo fundo = new Fundo(0, 0, dadosGame.getWordWidth(), dadosGame.getWordHeight());
        gerenciadorEntidades.addEntidade(fundo);
    }

    private void latas(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades, DadosGame dadosGame) {
        if (pixelAtual == 0xFF5E0000) {
            LataLixo pack = new LataLixo(x * tileSize, y * tileSize, cenario, dadosGame);
            gerenciadorEntidades.addEntidade(pack);
        }
    }

    private void portoes(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades) {
        if (pixelAtual == 0xFF7F0000) {
            Portao pack = new Portao(x * tileSize, y * tileSize, cenario, tileSize, tileSize);
            gerenciadorEntidades.addEntidade(pack);
        }
    }

    private void limiteCenario(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades, DadosGame dadosGame) {
        if (pixelAtual == 0xFF8E8E34) {
//            LimiteDeCenarioAbismo pack = new LimiteDeCenarioAbismo(x * tileSize, y * tileSize, tileSize, tileSize);
//            gerenciadorEntidades.addEntidade(pack);
        } else if (pixelAtual == 0xFFFF0000) {
            LimiteCenario esquerda = new LimiteCenario(x * tileSize, y * tileSize, 1, 2, dadosGame.getWordHeight() * tileSize);
            gerenciadorEntidades.addEntidade(esquerda);
        } else if (pixelAtual == 0xFFCB0000) {
            LimiteCenario direita = new LimiteCenario(x * tileSize, y * tileSize, 2, 2, dadosGame.getWordHeight() * tileSize);
            gerenciadorEntidades.addEntidade(direita);
        }
    }

    private void escadas(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades, DadosGame dadosGame) {
        if (pixelAtual == 0xFFE50063) {
            EscadaEsgoto pack = new EscadaEsgoto(x * tileSize, y * tileSize, cenario, dadosGame);
            gerenciadorEntidades.addEntidade(pack);
        }
    }

    private void plataformas(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades, DadosGame dadosGame) {
        if (pixelAtual == 0xFFFFFFFF) {
            Plataforma pack = new Plataforma(x * tileSize, y * tileSize, cenario.getSprite((2) * tileSize, 3 * tileSize, tileSize, tileSize));
            gerenciadorEntidades.addEntidade(pack);
        } else if (pixelAtual == 0xFFF2F2F2) {
            // chao de esgoto
            Plataforma pack = new Plataforma(x * tileSize, y * tileSize, (cenario).getSprite((5) * tileSize, 3 * tileSize, tileSize, tileSize));
            gerenciadorEntidades.addEntidade(pack);
        } else if (pixelAtual == 0xFFE5E5E5) {
            // chao de esgoto com canos
            BufferedImage[] img = {(cenario).getSprite(3 * tileSize, 3 * tileSize, tileSize, tileSize), (cenario).getSprite(4 * tileSize, 3 * tileSize, tileSize, tileSize)};
            Plataforma plat = new Plataforma(x * tileSize, y * tileSize, null);
            CanoEsgoto pack = new CanoEsgoto(x * tileSize, y * tileSize, img);
            gerenciadorEntidades.addEntidade(pack);
            gerenciadorEntidades.addEntidade(plat);
        } else if (pixelAtual == 0xFFAA004A) {
            // bueiro
            int posPost = dadosGame.getPlayer().getRand().nextInt(2) == 1 ? -tileSize / (dadosGame.getPlayer().getRand().nextInt(4) + 1) : tileSize / (dadosGame.getPlayer().getRand().nextInt(4) + 1);
            Poste post = new Poste(x * tileSize - posPost, y * tileSize, cenario, dadosGame);
            Ilminacao iluminacao = new Ilminacao(x * tileSize - posPost, y * tileSize, tileSize, tileSize, TipoIluminacao.POSTE_LUZ);
            gerenciadorEntidades.addEntidade(post);
            gerenciadorEntidades.addEntidade(iluminacao);
            BufferedImage[] img = {(cenario).getSprite(0, 3 * tileSize, tileSize, tileSize), (cenario).getSprite(tileSize, 3 * tileSize, tileSize, tileSize)};

            Bueiro pack = new Bueiro(x * tileSize, y * tileSize, img);
            gerenciadorEntidades.addEntidade(pack);
        } else if (pixelAtual == 0xFFD8D8D8) {
            // Telhado
            Plataforma pack = new Plataforma(x * tileSize, y * tileSize, null);
            gerenciadorEntidades.addEntidade(pack);
        }

    }

    private void predios(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades) {
        if (pixelAtual == 0xFF494949) {
//            Construcao construcao = new Construcao()
//            construcoes[0].setX(x * tileSize);
//            construcoes[0].setY(y * tileSize);
//            Construcao pack = construcoes[0];
//            pack.generateObjects(1);
//            gerenciadorEntidades.addEntidade(pack);
        }
    }

    private void player(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades, DadosGame dadosGame) {
        Player player = dadosGame.getPlayer();
        if (pixelAtual == 0xFF0026FF && !player.isFirstSpawn()) {
            player.setX(x * tileSize);
            player.setY(y * tileSize);
//TODO criar companion
            player.setFirstSpawn(true);
            player.setParado(true);
            gerenciadorEntidades.addEntidade(player);
            dadosGame.setPlayer(player);
        }
    }

    public static void restartGame() {
//		Game.entities.clear();
//		Game.enemies.clear();
//		Game.entities = new ArrayList<Entity>();
//		Game.enemies = new ArrayList<Enemy>();
//		Game.spritesheet = new Spritesheet("/spritesheet.png");
//		Game.player = new Player(0,0,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE,Game.spritesheet.getSprite(32, 0,Configuracoes.TILE_SIZE,Configuracoes.TILE_SIZE));
//		Game.entities.add(Game.player);
//		Game.world = new World("/"+level);
//		return;
    }

    public void startGame(DadosGame dadosGame, GerenciadorEntidades gerenciadorEntidades) {
        this.tileSize = dadosGame.getTileSize();
        try {
            BufferedImage map = ImageIO.read(Objects.requireNonNull(getClass().getResource(dadosGame.getPath())));
            int[] pixels = new int[map.getWidth() * map.getHeight()];
            dadosGame.setWordWidth(map.getWidth());
            dadosGame.setWordHeight(map.getHeight());
            map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
            for (int xx = 0; xx < dadosGame.getWordWidth(); xx++) {
                for (int yy = 0; yy < dadosGame.getWordHeight(); yy++) {
                    int pixelAtual = pixels[xx + (yy * dadosGame.getWordWidth())];
                    player(pixelAtual, xx, yy, gerenciadorEntidades, dadosGame);
                    plataformas(pixelAtual, xx, yy, gerenciadorEntidades, dadosGame);
                    predios(pixelAtual, xx, yy, gerenciadorEntidades);
                    escadas(pixelAtual, xx, yy, gerenciadorEntidades, dadosGame);
                    limiteCenario(pixelAtual, xx, yy, gerenciadorEntidades, dadosGame);
                    portoes(pixelAtual, xx, yy, gerenciadorEntidades);
                    latas(pixelAtual, xx, yy, gerenciadorEntidades, dadosGame);
                }
            }
            fundo(gerenciadorEntidades, dadosGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//	public static void changeArea() {
//		Game.refreshListsSTC(false);
//		Game.world = new World("/niveis/area"+Configuracoes.rotear()+".png");
//	}
}
