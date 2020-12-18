package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entidades.Cenario_Interagivel;
import Entidades.Enemy;
import Entidades.Entity;
import Entidades.Plataforma;
import Entidades.Player;
import Entidades.Pupkin_Boss;
import Entidades.light_npc;
import Entidades.roux_npc;
import Entidades.soldier_npc;
import Graficos.Spritesheet;
import Main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	
	
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
					if(pixelAtual == 0xFFFFFFFF){
						Plataforma pack = new Plataforma(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(pack);
					}else  if(pixelAtual == 0xFFFF6A00){
						Cenario_Interagivel pack = new Cenario_Interagivel(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,"prateleira",null);
						Game.entities.add(pack);
						
						
						Cenario_Interagivel pack2 = new Cenario_Interagivel(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,"caixa",null);
						Game.entities.add(pack2);
					}else if(pixelAtual == 0xFF000000){
						Cenario_Interagivel pack = new Cenario_Interagivel(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,"lampada",null);
						Game.entities.add(pack);
					}else if(pixelAtual == 0xFF0026FF) {
						//Player
						Game.player.setX(xx*Game.TILE_SIZE);
						Game.player.setY(yy*Game.TILE_SIZE);
						Game.player2.setX(xx*Game.TILE_SIZE);
						Game.player2.setY(yy*Game.TILE_SIZE);
					}else   if(pixelAtual == 0xFF910000) {
						soldier_npc sd= new soldier_npc(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(sd);
						
						roux_npc rn= new roux_npc(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(rn);
						light_npc ln= new light_npc(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(ln);
						
						Pupkin_Boss pb= new Pupkin_Boss(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
						Game.entities.add(pb);
					}else   if(pixelAtual == 0xFFFF7F7F) {
						//Life Pack
//						Lifepack pack = new Lifepack(xx*Game.TILE_SIZE,yy*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,Entity.LIFEPACK_EN);
//						Game.entities.add(pack);
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
		int xstart = Camera.x >> 10;
		int ystart = Camera.y >> 10;
		
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
