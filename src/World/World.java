package World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entidades.Entity;
import Entidades.Cenario.DanoDeQueda;
import Entidades.Cenario.EscadaEsgoto;
import Entidades.Cenario.Janela;
import Entidades.Cenario.LataLixo;
import Entidades.Cenario.Limite_Cenario;
import Entidades.Cenario.Plataforma;
import Entidades.Cenario.Porta;
import Entidades.Cenario.Portao;
import Entidades.Cenario.PosteLuz;
import Main.Game;

public class World {
	private int borda=0;
	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	private int cont=0;
	private boolean fillColor=true;
	private boolean canoOn=false;
	private int left_dir=0,right_dir=1;
	private int dir=left_dir;
	private int beiradas=1;
	private int cor=0;
	private Color[] predios1= {
			new Color(0xFFE55137)
			,new Color(0xFFE7BCC1),
			new Color(0xFFFFAF6E),
			new Color(0xFF7A6A70),//
			new Color(0xFF6A4B74),
			new Color(0xFF6ABE74),
			new Color(0xFF745E7A),
			new Color(0xFFEA5E7A),
			new Color(0xFFA04564),
			new Color(0xFF9E6C4B)};
	public World(String path){
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Tile.TILE_FLOOR);
					//PLATAFORMAS
					//PLATAFORMAS
					//PLATAFORMAS
					//PLATAFORMAS
					//PLATAFORMAS
					//PLATAFORMAS
					if(pixelAtual == 0xFFFFFFFF){
						//chao de asfalto
						cont ++;
						if(cont%10==0 || cont==0) {
							PosteLuz post = new PosteLuz(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
							Game.entities.add(post);
						}
						Plataforma pack = new Plataforma(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						pack.setTipo(3);
						Game.entities.add(pack);
					}else if(pixelAtual == 0xFFF2F2F2){
						//chao de esgoto
						Plataforma pack = new Plataforma(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						pack.setTipo(6);
						Game.entities.add(pack);	
					}else if(pixelAtual == 0xFFE5E5E5){
						//chao de esgoto coM canos
						Plataforma pack = new Plataforma(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						if(canoOn) {
							pack.setTipo(4);
							canoOn=false;
						}else {
							pack.setTipo(5);
							canoOn=true;
						}
						Game.entities.add(pack);
					}else if(pixelAtual == 0xFFAA004A){
						//bueiro
						Plataforma pack = new Plataforma(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						pack.setTipo(1);
						Game.entities.add(pack);
						Game.bueiros.add(pack);
					}else if(pixelAtual == 0xFFD8D8D8){
						//Telhado
						Plataforma pack = new Plataforma(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						pack.invisivel();
						Game.entities.add(pack);
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,
								Tile.colorir(Game.cenario.getSprite(1*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),Color.cyan));
					}else 
						//PLAYER
						//PLAYER
						//PLAYER
						//PLAYER
						//PLAYER
					if(pixelAtual == 0xFF0026FF) {
						if(Game.Ambiente=="Cidade") {
							tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,6*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
						}else if(Game.Ambiente=="Esgoto") {
							tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
									Tile.colorir
									(Game.cenario.getSprite
											(0*Game.TILE_SIZE,4*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),new Color(0xFF3B4235)));
						}else if(Game.Ambiente=="Terraço") {
							if(Game.dia) {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,
										Tile.colorir(Game.cenario.getSprite(1*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),Color.cyan));
							}
						}
						Game.player.setX(xx*Game.TILE_SIZE);
						Game.player.setY(yy*Game.TILE_SIZE);
						Game.player2.setX(xx*Game.TILE_SIZE-20);
						Game.player2.setY(yy*Game.TILE_SIZE-3);
					}else 
						//PREDIO
						//PREDIO
						//PREDIO
						//PREDIO
						//PREDIO
						//PREDIO
						//PREDIO
						//PREDIO
						//PREDIO
					if(pixelAtual == 0xFF494949) {
						//parte de baixo PREDIO
						
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,6*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
					}else   if(pixelAtual == 0xFF333333) {
						//parte de cima PREDIO
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,5*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
					}else if(pixelAtual == 0xFF8E8E34) {
						//dano de queda
						DanoDeQueda pack = new DanoDeQueda(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(pack);
						
					}else if(pixelAtual == 0xFFFF0000) {
						//limite cenario
						Limite_Cenario pack = new Limite_Cenario(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						pack.setParede(borda);
						borda++;
						Game.entities.add(pack);
						
					}else if(pixelAtual == 0xFF72722A) {
						//beirada de cima-esquerda dano de queda
						if(dir==left_dir) {
							tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
									Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
						}else {
							tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
									Entity.inverter(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])));
						}
						DanoDeQueda pack = new DanoDeQueda(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(pack);
						
					}else   if(pixelAtual == 0xFF4C4C1C) {
						//beirada de baixo-esquerda dano de queda
						fillColor=false;
						if(dir==left_dir) {
							tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
									Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,8*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
						}else {
							tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
									Entity.inverter(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,8*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])));
						}
						DanoDeQueda pack = new DanoDeQueda(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(pack);
					}else     if(pixelAtual == 0xFF666625) {
						//parte de baixo dano de queda
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,6*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
					}else   if(pixelAtual == 0xFF7F7F2F) {
						//parte de cimadanod e queda
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,5*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));

						DanoDeQueda pack = new DanoDeQueda(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(pack);
					}else  if(pixelAtual == 0xFF262626) {
						//beirada de cima-esquerda
						if(fillColor) {
							if(dir==left_dir) {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										
												Tile.fillBack(
														(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])),0xFFD8F1F7));
								}else {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Entity.inverter(Tile.fillBack(
														(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])),0xFFD8F1F7)));
							}
						}else {
							if(dir==left_dir) {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
							}else {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Entity.inverter(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])));
							}
						}
					}else   if(pixelAtual == 0xFF3F3F3F) {
						//beirada de baixo-esquerda
						if(fillColor) {
							if(dir==left_dir) {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Tile.fillBack((Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,8*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])),0xFFD8F1F7));
								}else {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Entity.inverter(
												Tile.fillBack(
														(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,8*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])),0xFFD8F1F7)));
							}
						}else {
							if(dir==left_dir) {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,8*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
							}else {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Entity.inverter(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,8*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])));
							}
						}
					}else if(pixelAtual == 0xFF191919) {
						//virar a beirada
						fillColor=true;
						beiradas++;
						if(beiradas==2) {
							cor++;
							beiradas=0;
						}
						if(cor>10) {
							cor=0;
						}
						if(dir==left_dir) {
							dir=right_dir;
						}else	if(dir==right_dir) {
							dir=left_dir;
						}
						if(fillColor) {
							if(dir==left_dir) {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										
												Tile.fillBack(
														(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])),0xFFD8F1F7));
								}else {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Entity.inverter(Tile.fillBack(
														(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])),0xFFD8F1F7)));
							}
						}else {
							if(dir==left_dir) {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
							}else {
								tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
										Entity.inverter(Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,7*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor])));
							}
						}
					}else
						//ESGOTOS
						//ESGOTOS
						//ESGOTOS
						//ESGOTOS
						//ESGOTOS
						//ESGOTOS
						//ESGOTOS
						//ESGOTOS	
					if(pixelAtual == 0xFF35F2F2) {
						//parede tunel
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir
								(Game.cenario.getSprite
										(0*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),new Color(0xFF3B4235)));
					}else 
						//PORTAS
						//PORTAS
						//PORTAS
						//PORTAS
						//PORTAS
					if(pixelAtual == 0xFF595959) {
						//porta
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,6*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
						Porta pack = new Porta(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						pack.setCor(predios1[cor]);
						Game.entities.add(pack);
						Game.portas.add(pack);
					}else if(pixelAtual == 0xFFCCCCCC) {
						//porta terraço
						Porta pack = new Porta(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						pack.setTipo(1);
						pack.setCor(predios1[cor]);
						Game.entities.add(pack);
						Game.portaTerraco.add(pack);
						
						Plataforma plat = new Plataforma(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						plat.invisivel();
						Game.entities.add(plat);
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,
								Tile.colorir(Game.cenario.getSprite(1*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),Color.cyan));
					}else if(pixelAtual == 0xFFE50063) {
						//escada baixa
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir(Game.cenario.getSprite(0*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),new Color(0xFF3B4235)));
						EscadaEsgoto pack = new EscadaEsgoto(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						pack.setSubida();
						Game.escadasDeEsgoto.add(pack);
						Game.entities.add(pack);
					}else if(pixelAtual == 0xFF7F0000) {
						//portao
						Portao pack = new Portao(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(pack);
						Game.portoes.add(pack);
					}else
						//ELEMENTOS DO CENARIO
						//ELEMENTOS DO CENARIO
						//ELEMENTOS DO CENARIO
						//ELEMENTOS DO CENARIO
						//ELEMENTOS DO CENARIO
						//ELEMENTOS DO CENARIO
						//ELEMENTOS DO CENARIO
						
					if(pixelAtual == 0xFFFF006E) {
						//escada corpo
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir(Game.cenario.getSprite(0*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),new Color(0xFF3B4235)));
						EscadaEsgoto pack = new EscadaEsgoto(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(pack);
					}else if(pixelAtual == 0xFF5E0000) {
						//Lata de lixo
						LataLixo pack = new LataLixo(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(pack);
					}else if(pixelAtual == 0xFF666666) {
						//janela
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE, 
								Tile.colorir(Game.cenario.getSprite(2*Game.TILE_SIZE,6*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),predios1[cor]));
						Janela pack = new Janela(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						pack.setCor(predios1[cor]);
						Game.entities.add(pack);
					}else 
					//FUNDO
					//FUNDO
					if(pixelAtual == 0xFFD8D84E){
						//fundo ceu
						if(Game.dia) {
							tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,
									Tile.colorir(Game.cenario.getSprite(1*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),Color.cyan));
						}
						
					}else {
						//fundo
						tiles[xx + (yy * WIDTH)] = new FundoTile(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Tile.TILE_FLOOR);
						
					}
					
						
						
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / Game.TILE_SIZE;
		int y1 = ynext / Game.TILE_SIZE;
		
		int x2 = (xnext+Game.TILE_SIZE-1) / Game.TILE_SIZE;
		int y2 = ynext / Game.TILE_SIZE;
		
		int x3 = xnext / Game.TILE_SIZE;
		int y3 = (ynext+Game.TILE_SIZE-1) / Game.TILE_SIZE;
		
		int x4 = (xnext+Game.TILE_SIZE-1) / Game.TILE_SIZE;
		int y4 = (ynext+Game.TILE_SIZE-1) / Game.TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof FundoTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof FundoTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof FundoTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof FundoTile));
	}
	
	public static void restartGame(String level){
//		Game.entities.clear();
//		Game.enemies.clear();
//		Game.entities = new ArrayList<Entity>();
//		Game.enemies = new ArrayList<Enemy>();
//		Game.spritesheet = new Spritesheet("/spritesheet.png");
//		Game.player = new Player(0,0,Game.TILE_SIZE,Game.TILE_SIZE,Game.spritesheet.getSprite(32, 0,Game.TILE_SIZE,Game.TILE_SIZE));
//		Game.entities.add(Game.player);
//		Game.world = new World("/"+level);
//		return;
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >>6;
		int ystart = Camera.y >> 6;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
