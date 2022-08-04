package World;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import Configuration.Configuracoes;
import Entidades.Cenario.*;
import Entidades.GerenciadorEntidades;
import Entidades.Players.Player;
import Graficos.Spritesheet;

public class World {
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	private final Spritesheet cenario;
	private final String path;
	private final GerenciadorEntidades gerenciadorEntidades = new GerenciadorEntidades();
	private Predio[] predios = { new Predio(1, 0, 7, 7, new Spritesheet("/cenario/predioBranco.png")),
			new Predio(3, 0, 0, 9,new Spritesheet("/cenario/stone.png")),
			new Predio(8, 0, 0, 7, new Spritesheet("/cenario/predioBranco.png")) };
//	private Predio[] predios2 = {
//			new Predio(2,0, 0, 6, 4, Texturas.MARMORE),
//			new Predio(4,0, 0, 25, 4, Texturas.MARMORE),
//			new Predio(5,0, 0, 12, 4, Texturas.MARMORE), 
//			new Predio(6,0, 0, 11, 4, Texturas.MARMORE),
//			new Predio(7,0, 0, 32, 4, Texturas.MARMORE), 
//			new Predio(9,0, 0, 7, 4, Texturas.MARMORE)
//	};

	public World(String path) {
		gerenciadorEntidades.iniciarListas();
		cenario= new Spritesheet("/cenario/cenario.png");
		this.path = path;
	}

	private void fundo(int pixelAtual, int x, int y) {
//		tiles[pixelAtual] = new FundoTile(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, Tile.TILE_FLOOR);
	}

	private void latas(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFF5E0000) {
			LataLixo pack = new LataLixo(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,cenario);
			gerenciadorEntidades.addEntidade(pack);
		}
	}

	private void portoes(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFF7F0000) {
			Portao pack = new Portao(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,cenario);
			gerenciadorEntidades.addEntidade(pack);
		}
	}

	private void limiteCenario(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFF8E8E34) {
			LimiteDeCenarioAbismo pack = new LimiteDeCenarioAbismo(x * Configuracoes.TILE_SIZE,
					y * Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
			gerenciadorEntidades.addEntidade(pack);
		} else if (pixelAtual == 0xFFFF0000) {
			ParedeInvisivel pack = new ParedeInvisivel(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, WIDTH);
			gerenciadorEntidades.addEntidade(pack);

		}
	}

	private void escadas(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFFE50063) {
			EscadaEsgoto pack = new EscadaEsgoto(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,cenario);
			gerenciadorEntidades.addEntidade(pack);
		}
	}

	private void plataformas(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFFFFFFFF) {
			Plataforma pack = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,
					cenario.getSprite((2) * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE));
			gerenciadorEntidades.addEntidade(pack);
		} else if (pixelAtual == 0xFFF2F2F2) {
			// chao de esgoto
			Plataforma pack = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,
					(cenario).getSprite((5) * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE));
			gerenciadorEntidades.addEntidade(pack);
		} else if (pixelAtual == 0xFFE5E5E5) {
			// chao de esgoto com canos
			BufferedImage[] img = {
					(cenario).getSprite(3 * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE),
					(cenario).getSprite(4 * Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE) };
			Plataforma plat = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, null);
			CanoEsgoto pack = new CanoEsgoto(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, img);
			gerenciadorEntidades.addEntidade(pack);
			gerenciadorEntidades.addEntidade(plat);
		} else if (pixelAtual == 0xFFAA004A) {
			// bueiro
			PosteLuz post = new PosteLuz(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE,cenario);
			gerenciadorEntidades.addEntidade(post);
			BufferedImage[] img = {
					(cenario).getSprite(0, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE),
					(cenario).getSprite(Configuracoes.TILE_SIZE, 3 * Configuracoes.TILE_SIZE,
							Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE) };

			Bueiro pack = new Bueiro(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, img);
			Plataforma plat = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, null);
			gerenciadorEntidades.addEntidade(pack);
			gerenciadorEntidades.addEntidade(plat);
		} else if (pixelAtual == 0xFFD8D8D8) {
			// Telhado
			Plataforma pack = new Plataforma(x * Configuracoes.TILE_SIZE, y * Configuracoes.TILE_SIZE, null);
			gerenciadorEntidades.addEntidade(pack);
		}

	}

	private void predios(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFF494949) {
			predios[0].setX(x * Configuracoes.TILE_SIZE);
			predios[0].setY(y * Configuracoes.TILE_SIZE);
			Predio pack = predios[0];
			pack.generateObjects(1);
			gerenciadorEntidades.addEntidade(pack);
		}
	}

	private void player(int pixelAtual, int x, int y) {
		Player player = gerenciadorEntidades.getPlayer();
		if (pixelAtual == 0xFF0026FF && !player.isPrimeiroSpawn()) {
			player.setX(x * Configuracoes.TILE_SIZE);
			player.setY(y * Configuracoes.TILE_SIZE);
			player.setX(x * Configuracoes.TILE_SIZE - 20);
			player.setY(y * Configuracoes.TILE_SIZE + 3);
			player.setPrimeiroSpawn();
			player.setParado(true);
			Configuracoes.p1 = player;
//			Configuracoes.p2 = player2;
			gerenciadorEntidades.setPlayer(player);
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

	public void criarMundo(){
		try {
			BufferedImage map = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
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

	public GerenciadorEntidades getGerenciadorEntidades(){
		return gerenciadorEntidades;
	}
	public void startGame() {
		gerenciadorEntidades.refreshListsSTC(true);
		criarMundo();
	}

//	public static void changeArea() {
//		Game.refreshListsSTC(false);
//		Game.world = new World("/niveis/area"+Configuracoes.rotear()+".png");
//	}
}
