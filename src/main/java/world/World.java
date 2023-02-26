package world;

import configuracoes.DadosGame;
import entidades.cenario.*;
import entidades.cenario.limitescenario.LimiteDeCenarioAbismo;
import entidades.cenario.limitescenario.ParedeInvisivel;
import entidades.cenario.objetosluminosos.PosteLuz;
import entidades.players.Player;
import graficos.Spritesheet;
import processamento.GerenciadorEntidades;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class World {
    private int tileSize;
    private final Spritesheet cenario = new Spritesheet("/cenario/cenario.png");
    private final Predio[] predios = {
            new Predio(1, 0, 7, 7, new Spritesheet("/cenario/predioBranco.png")),
            new Predio(3, 0, 0, 9, new Spritesheet("/cenario/stone.png")),
            new Predio(8, 0, 0, 7, new Spritesheet("/cenario/predioBranco.png"))
    };
//	private Predio[] predios2 = {
//			new Predio(2,0, 0, 6, 4, Texturas.MARMORE),
//			new Predio(4,0, 0, 25, 4, Texturas.MARMORE),
//			new Predio(5,0, 0, 12, 4, Texturas.MARMORE), 
//			new Predio(6,0, 0, 11, 4, Texturas.MARMORE),
//			new Predio(7,0, 0, 32, 4, Texturas.MARMORE), 
//			new Predio(9,0, 0, 7, 4, Texturas.MARMORE)
//	};

    private void fundo(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades) {
//		tiles[pixelAtual] = new FundoTile(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, Tile.TILE_FLOOR);
    }

    private void latas(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades, DadosGame dadosGame) {
        if (pixelAtual == 0xFF5E0000) {
            LataLixo pack = new LataLixo(x * tileSize, y * tileSize, cenario, dadosGame);
            gerenciadorEntidades.addEntidade(pack);
        }
    }

    private void portoes(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades) {
        if (pixelAtual == 0xFF7F0000) {
            Portao pack = new Portao(x * tileSize, y * tileSize, cenario);
            gerenciadorEntidades.addEntidade(pack);
        }
    }

    private void limiteCenario(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades, DadosGame dadosGame) {
        if (pixelAtual == 0xFF8E8E34) {
            LimiteDeCenarioAbismo pack = new LimiteDeCenarioAbismo(x * tileSize,
                    y * tileSize, tileSize, tileSize);
            gerenciadorEntidades.addEntidade(pack);
        } else if (pixelAtual == 0xFFFF0000) {
            ParedeInvisivel pack = new ParedeInvisivel(x * tileSize, y * tileSize, dadosGame);
            gerenciadorEntidades.addEntidade(pack);

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
            Plataforma pack = new Plataforma(x * tileSize, y * tileSize,
                    cenario.getSprite((2) * tileSize, 3 * tileSize,
                            tileSize, tileSize));
            gerenciadorEntidades.addEntidade(pack);
        } else if (pixelAtual == 0xFFF2F2F2) {
            // chao de esgoto
            Plataforma pack = new Plataforma(x * tileSize, y * tileSize,
                    (cenario).getSprite((5) * tileSize, 3 * tileSize,
                            tileSize, tileSize));
            gerenciadorEntidades.addEntidade(pack);
        } else if (pixelAtual == 0xFFE5E5E5) {
            // chao de esgoto com canos
            BufferedImage[] img = {
                    (cenario).getSprite(3 * tileSize, 3 * tileSize,
                            tileSize, tileSize),
                    (cenario).getSprite(4 * tileSize, 3 * tileSize,
                            tileSize, tileSize)};
            Plataforma plat = new Plataforma(x * tileSize, y * tileSize, null);
            CanoEsgoto pack = new CanoEsgoto(x * tileSize, y * tileSize, img);
            gerenciadorEntidades.addEntidade(pack);
            gerenciadorEntidades.addEntidade(plat);
        } else if (pixelAtual == 0xFFAA004A) {
            // bueiro
            PosteLuz post = new PosteLuz(x * tileSize, y * tileSize, cenario, dadosGame);
            gerenciadorEntidades.addEntidade(post);
            BufferedImage[] img = {
                    (cenario).getSprite(0, 3 * tileSize,
                            tileSize, tileSize),
                    (cenario).getSprite(tileSize, 3 * tileSize,
                            tileSize, tileSize)};

            Bueiro pack = new Bueiro(x * tileSize, y * tileSize, img);
            Plataforma plat = new Plataforma(x * tileSize, y * tileSize, null);
            gerenciadorEntidades.addEntidade(pack);
            gerenciadorEntidades.addEntidade(plat);
        } else if (pixelAtual == 0xFFD8D8D8) {
            // Telhado
            Plataforma pack = new Plataforma(x * tileSize, y * tileSize, null);
            gerenciadorEntidades.addEntidade(pack);
        }

    }

    private void predios(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades) {
        if (pixelAtual == 0xFF494949) {
            predios[0].setX(x * tileSize);
            predios[0].setY(y * tileSize);
            Predio pack = predios[0];
            pack.generateObjects(1);
            gerenciadorEntidades.addEntidade(pack);
        }
    }

    private void player(int pixelAtual, int x, int y, GerenciadorEntidades gerenciadorEntidades, DadosGame dadosGame) {
        Player player = dadosGame.getPlayer();
        if (pixelAtual == 0xFF0026FF && !player.isPrimeiroSpawn()) {
            player.setX(x * tileSize);
            player.setY(y * tileSize);
//            player.setX(x * tileSize - 20);
//            player.setY(y * tileSize + 3);
            player.setPrimeiroSpawn();
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

//			Tile[] tiles = new Tile[map.getWidth() * map.getHeight()];
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
                    fundo(pixelAtual, xx, yy, gerenciadorEntidades);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//	public static void changeArea() {
//		Game.refreshListsSTC(false);
//		Game.world = new World("/niveis/area"+Configuracoes.rotear()+".png");
//	}
}
