package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import Entidades.BulletShoot;
import Entidades.Enemy;
import Entidades.Entity;
import Entidades.Player;
import Graficos.Spritesheet;
import Graficos.UI;
import World.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener{
	public static int TILE_SIZE=64;
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 180*4;
	public static final int HEIGHT = 90*4;
	public static final int SCALE = 2;
	
	private int CUR_LEVEL = 1;
	private BufferedImage image;
	
	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static List<BulletShoot> bullets;
	
	public static Spritesheet spritesheet;
	public static Spritesheet fundo;
	public static Spritesheet icones;
	
	public static World world;
	
	public static Player player;
	
	public static Random rand;
	
	public UI ui;
	
	public static String gameState = "MENU";
	private boolean showMessageGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;
	public static Cutscene cen;
	public Menu menu;
	public static int[] pixels;
	public static int[] lightMap;
	
	public Game(){
		rand = new Random();
		addKeyListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		//Inicializando objetos.
		ui = new UI();
		lightMap = new int[WIDTH*HEIGHT];
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		pixels =((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<BulletShoot>();
		
		spritesheet = new Spritesheet("/spritesheet.png");
		icones = new Spritesheet("/icones.png");
		player = new Player(0,0,TILE_SIZE,TILE_SIZE,Game.spritesheet.getSprite(Game.TILE_SIZE*0, 0, Game.TILE_SIZE, Game.TILE_SIZE));
		entities.add(player);
		world = new World("/level1.png");
		
		
		menu = new Menu();
		cen=new Cutscene();
	}
	
	public void initFrame(){
		frame = new JFrame("Game #1");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.start();
	}
	
	public void tick(){
		if(gameState == "NORMAL") {
			if(cen.CcRun()) {
				cen.tick();
			}
			this.restartGame = false;	
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
			
			for(int i = 0; i < bullets.size(); i++) {
				bullets.get(i).tick();
			}
			
	//		if(enemies.size() == 0) {
	//			String newWorld = "level"+CUR_LEVEL+".png";
	//			World.restartGame(newWorld);
	//		}
		}else if(gameState == "GAME_OVER") {
			
			
			
			if(restartGame) {
				this.restartGame = false;
				this.gameState = "NORMAL";
				CUR_LEVEL = 1;
				String newWorld = "level"+CUR_LEVEL+".png";
				//System.out.println(newWorld);
				World.restartGame(newWorld);
			}
		}else if(gameState == "MENU") {
			
			menu.tick();
		}
	}
	

	

	
	
	
	
	
	public void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0,WIDTH,HEIGHT);
		
		/*Renderização do jogo*/
		//Graphics2D g2 = (Graphics2D) g;
		world.render(g);
		if(gameState == "NORMAL") {
			
			Collections.sort(entities,Entity.nodeSorter);
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}
			for(int i = 0; i < bullets.size(); i++) {
				bullets.get(i).render(g);
			}
			ui.render(g);
			if(cen.CcRun()) {
				cen.render(g);
			}
		}

		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		g.setFont(new Font("arial",Font.BOLD,20));
		
		if(gameState == "GAME_OVER") {
			//menugover.render(g);
		}else if(gameState == "MENU") {
			menu.render(g);
		}
		bs.show();
	}
	
public void run() {
		
		long lastTime = System.nanoTime();
		double amongOfTicks = 60.0;
		double delta = 0;
		double ns = 1000000000 / amongOfTicks;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			
			long now = System.nanoTime();
			delta+= (now-lastTime)/ns;
			lastTime= now;
			if(delta>=1) {
				tick();
				render();
				frames++;
				delta--;				
			}
			if(System.currentTimeMillis() - timer >= 1000) {
//				System.out.println("FPS:"+ frames);
				frames=0;
				timer+=1000;
			}
			
		}
		
		stop();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
		
		if(!cen.CcRun()) {
			if(e.getKeyCode() == KeyEvent.VK_UP){
				
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				player.camL=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				player.camR=true;
			}
		}
		if(!cen.CcRun()) {
			if(e.getKeyCode() == KeyEvent.VK_D){
				
					player.right = true;
					player.parado=false;
					player.parando=false;
					player.moved=true;
					if(player.isFreeX()!="esquerda") {
						player.parede=false;
					}
				
			}
			if(e.getKeyCode() == KeyEvent.VK_E){
				player.parado=false;
				if(player.stamina>15) {
					player.dash=true;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_Q){
				player.parado=false;
				if(player.dash) {
					if(player.stamina>30) {
						player.dashS=true;
					}
				}else if(player.subindo || player.caindo){
					//jump atackk
				}else {
					if(player.atacando) {
						if(player.atack2==false) {
							//player.atack2=true;
						}else {
							//player.atacando2=true;
						}
					}else {
						player.atacando = true;
					}
					
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				player.left = true;
				player.parado=false;
				player.parando=false;
				player.moved=true;
				if(player.isFreeX()!="direita") {
					player.parede=false;
				}
			}
		}
		
		
		if(e.getKeyCode() == KeyEvent.VK_W){
			if(!cen.CcRun()) {
				player.parando=false;
				player.parado=false;
				player.caiu_no_chao=false;
				player.up = true;
			}else {
				cen.up();
			}
			if(gameState == "MENU") {
				menu.up = true;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_S) {
			if(cen.CcRun()){
				cen.down();
			}
			
			if(gameState == "MENU") {
				menu.down = true;
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(cen.CcRun()) {
				cen.next();
			}
			if(gameState == "MENU") {
				menu.enter = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState = "MENU";
			menu.pause = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!cen.CcRun()) {
			if(e.getKeyCode() == KeyEvent.VK_UP){
				
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				player.camL=false;
				player.camx=0;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				player.camR=false;
				player.camx=0;
			}
		}
		if(!cen.CcRun()) {
			if(e.getKeyCode() == KeyEvent.VK_D){
				player.right = false;
				player.moved=false;
				if(!player.left) {
					if(player.isFreeX()!="esquerda") {
						if(!player.caindo && !player.subindo) {
							player.parando=true;
						}
					}
				}

			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				player.left = false;
				player.moved=false;
				if(!player.right) {
					if(player.isFreeX()!="direita") {
						if(!player.caindo && !player.subindo) {
							player.parando=true;
						}
					}
				}

			}
			
			if(e.getKeyCode() == KeyEvent.VK_W){
				player.up = false;
				player.podepular=false;
				if(!player.completou_pulo) {
//					player.caiu_no_chao=true;
//					player.saiu_do_chao=false;
				}
				
				player.moved=false;

			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				player.down = false;
				player.moved=false;
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
