package World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Configuration.Configuracoes;
import Entidades.Cenario.Bueiro;
import Entidades.Cenario.CanoEsgoto;
import Entidades.Cenario.EscadaEsgoto;
import Entidades.Cenario.LataLixo;
import Entidades.Cenario.LimiteDeCenarioAbismo;
import Entidades.Cenario.ParedeInvisivel;
import Entidades.Cenario.Plataforma;
import Entidades.Cenario.Portao;
import Entidades.Cenario.PosteLuz;
import Entidades.Cenario.Predio;
import Entidades.Enemies.Soldier_Enemy;
import Graficos.Spritesheet;
import Main.Game;

public class World {
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	private int indexPredio = 0;
	private Spritesheet cenario;
	private Predio[] predios = { new Predio(1, 0, 0, 7, 4, new Spritesheet("/cenario/predioBranco.png")),
			new Predio(3, 0, 0, 9, 4, new Spritesheet("/cenario/stone.png")),
			new Predio(8, 0, 0, 7, 4, new Spritesheet("/cenario/predioBranco.png")) };
//	private Predio[] predios2 = {
//			new Predio(2,0, 0, 6, 4, Texturas.MARMORE),
//			new Predio(4,0, 0, 25, 4, Texturas.MARMORE),
//			new Predio(5,0, 0, 12, 4, Texturas.MARMORE), 
//			new Predio(6,0, 0, 11, 4, Texturas.MARMORE),
//			new Predio(7,0, 0, 32, 4, Texturas.MARMORE), 
//			new Predio(9,0, 0, 7, 4, Texturas.MARMORE)
//	};

	public World(String path) {
		cenario= new Spritesheet("/cenario/cenario.png");
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int xx = 0; xx < WIDTH; xx++) {
				for (int yy = 0; yy < HEIGHT; yy++) {
					int pixelAtual = pixels[xx + (yy * WIDTH)];
					player(pixelAtual, xx, yy);
					plataformas(pixelAtual, xx, yy);
					predios(pixelAtual, xx, yy);
					escadas(pixelAtual, xx, yy);
					limiteCenario(pixelAtual, xx, yy);
					portoes(pixelAtual, xx, yy);
					latas(pixelAtual, xx, yy);
					fundo(pixelAtual, xx, yy);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fundo(int pixelAtual, int x, int y) {
//		tiles[pixelAtual] = new FundoTile(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, Tile.TILE_FLOOR);
	}

	private void latas(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFF5E0000) {
			LataLixo pack = new LataLixo(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,cenario);
			Game.entities.add(pack);
		}
	}

	private void portoes(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFF7F0000) {
			Portao pack = new Portao(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,cenario);
			Game.entities.add(pack);
		}
	}

	private void limiteCenario(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFF8E8E34) {
			LimiteDeCenarioAbismo pack = new LimiteDeCenarioAbismo(x * Configuracoes.TILE_SIZE,
					y * Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
			Game.entities.add(pack);
		} else if (pixelAtual == 0xFFFF0000) {
			ParedeInvisivel pack = new ParedeInvisivel(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, WIDTH);
			Game.entities.add(pack);

		}
	}

	private void escadas(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFFE50063) {
			EscadaEsgoto pack = new EscadaEsgoto(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,cenario);
			Game.entities.add(pack);
		}
	}

	private void plataformas(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFFFFFFFF) {
			Plataforma pack = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,
					cenario.getSprite((2) * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE));
			Game.entities.add(pack);
		} else if (pixelAtual == 0xFFF2F2F2) {
			// chao de esgoto
			Plataforma pack = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,
					(cenario).getSprite((5) * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE));
			Game.entities.add(pack);
		} else if (pixelAtual == 0xFFE5E5E5) {
			// chao de esgoto com canos
			BufferedImage[] img = {
					(cenario).getSprite(3 * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE),
					(cenario).getSprite(4 * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE) };
			Plataforma plat = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, null);
			CanoEsgoto pack = new CanoEsgoto(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, img);
			Game.entities.add(pack);
			Game.entities.add(plat);
		} else if (pixelAtual == 0xFFAA004A) {
			// bueiro
			PosteLuz post = new PosteLuz(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,cenario);
			Game.entities.add(post);
			BufferedImage[] img = {
					(cenario).getSprite(0 * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE),
					(cenario).getSprite(1 * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE) };

			Bueiro pack = new Bueiro(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, img);
			Plataforma plat = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, null);
			Game.entities.add(pack);
			Game.entities.add(plat);
		} else if (pixelAtual == 0xFFD8D8D8) {
			// Telhado
			Plataforma pack = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, null);
			Game.entities.add(pack);
		}

	}

	private void predios(int pixelAtual, int x, int y) {

		if (pixelAtual == 0xFF494949) {
			predios[indexPredio].setX(x * Configuracoes.TILE_SIZE);
			predios[indexPredio].setY(y * Configuracoes.TILE_SIZE);
			Predio pack = predios[indexPredio];
			pack.generateObjects(pack.getId());
			Game.predios.add(pack);
			indexPredio++;
		}
	}

	private void player(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFF0026FF && Game.player.primeiroSpawn()) {
			Game.player.setX(x * Configuracoes.TILE_SIZE);
			Game.player.setY(y * Configuracoes.TILE_SIZE);
			Game.player2.setX(x * Configuracoes.TILE_SIZE - 20);
			Game.player2.setY(y * Configuracoes.TILE_SIZE + 3);
			Game.player.setPrimeiroSpawn();
			Game.player2.setPrimeiroSpawn();
			Game.player.parado=true;
			Configuracoes.p1=Game.player;
			Configuracoes.p2=Game.player2;
			Soldier_Enemy en = new Soldier_Enemy(x* Configuracoes.TILE_SIZE+100, y* Configuracoes.TILE_SIZE);
			Game.enemies.add(en);
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
	public static void startGame() {
		Game.refreshListsSTC(true);
		Game.world = new World("/niveis/area1.png");
	}
	
	public static void changeArea() {
		Game.refreshListsSTC(false);
		Game.world = new World("/niveis/area"+Configuracoes.rotear()+".png");
	}

	public void render(Graphics g) {
		int xstart = Camera.x >> 6;
		int ystart = Camera.y >> 6;

		int xfinal = xstart + (Configuracoes.WIDTH >> 4);
		int yfinal = ystart + (Configuracoes.HEIGHT >> 4);

		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}

}
