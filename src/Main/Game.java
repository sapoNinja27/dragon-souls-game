package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Entidades.Ace;
import Entidades.BulletShoot;
import Entidades.Enemy;
import Entidades.Entity;
import Entidades.EscadaEsgoto;
import Entidades.Plataforma;
import Entidades.Player;
import Entidades.Porta;
import Entidades.Portao;
import Entidades.Tai;
import Graficos.Spritesheet;
import Graficos.UI;
import Menu.Menu;
import World.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener{
	public static int TILE_SIZE=64;
	private static final long serialVersionUID = 1L;
	boolean savegame;
	public static boolean podeClicar=true;
	public static String Ambiente="Terraço";//terraço-esgotos-cidade
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 180*4;
	public static final int HEIGHT = 90*4;
	public static final int SCALE = 2;
	
	private int CUR_LEVEL = 1;
	private BufferedImage image;
	public static boolean dia=true;
	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static List<Porta> portas;
	public static List<Porta> portaTerraco;
	public static List<Portao> portoes;
	public static List<EscadaEsgoto> escadasDeEsgoto;
	public static List<Plataforma> bueiros;
	public static List<BulletShoot> bullets;
	//personagens
	public static Spritesheet ace;
	public static Spritesheet demonTai;
	public static Spritesheet tai;
	public static Spritesheet iron;
	public static Spritesheet light;
	public static Spritesheet rouxie;
	public static Spritesheet sander;
	//menus
	public static Spritesheet fundo;
	public static Spritesheet fundoT;
	public static Spritesheet fundoA;
	public static Spritesheet fundoS;
	public static Spritesheet Menu;
	public static Spritesheet icones;
	public static Spritesheet tinyIcons;
	//cenario
	public static Spritesheet cenario;
	
	
	public static World world;
	
	public static Player player;
	public static Player player2;
	
	public static Random rand;
	public UI ui;
	
	public static String gameState = "MENU";
	private boolean showMessageGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;
	public static Cutscene cen;
	public static Menu menu;
	public static int[] pixels;
	public static int[] lightMap;
	
	public Game(){
//		requestFocus();
		rand = new Random();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
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
		portas = new ArrayList<Porta>();
		portaTerraco = new ArrayList<Porta>();
		portoes = new ArrayList<Portao>();
		bueiros = new ArrayList<Plataforma>();
		escadasDeEsgoto=new ArrayList<EscadaEsgoto>();
		//personagens
		ace = new Spritesheet("/personagens/ace.png");
		demonTai = new Spritesheet("/personagens/demonTai.png");
		iron = new Spritesheet("/personagens/iron.png");
		light = new Spritesheet("/personagens/light.png");
		rouxie = new Spritesheet("/personagens/rouxie.png");
		sander = new Spritesheet("/personagens/sander.png");
		tai = new Spritesheet("/personagens/tai.png");
		//menus
		tinyIcons = new Spritesheet("/menus/tinyIcons.png");
		icones = new Spritesheet("/menus/icones.png");
		Menu = new Spritesheet("/menus/Menu.png");
		fundo = new Spritesheet("/menus/fundo.png");
		fundoA = new Spritesheet("/menus/fundoace.png");
		fundoT = new Spritesheet("/menus/fundotai.png");
		fundoS = new Spritesheet("/menus/fundosander.png");
		//cenario
		cenario = new Spritesheet("/cenario/cenario.png");
		
		
		
		
		player = new Tai(0,0,TILE_SIZE,TILE_SIZE,tai.getSprite(TILE_SIZE*0, 0, TILE_SIZE, TILE_SIZE));
		player2=new Ace(0,0,TILE_SIZE,TILE_SIZE,null);
		entities.add(player);
		entities.add(player2);
		world = new World("/niveis/mapaMundi.png");
		
		
		menu = new Menu();
		cen=new Cutscene();
	}
	
	public void initFrame(){
		frame = new JFrame("Dragon Soul I");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		Image imagem=null;
		try {
			LocalDate myObj = LocalDate.now(); 
			String data= String.valueOf(myObj);
			String mes= data.substring(5, 7);
			String dia= data.substring(8, 10);
//			imagem=ImageIO.read(getClass().getResource("/icone-jogo/icon.png"));
			if(mes.equals("12") && dia.equals("25")) {
				imagem=ImageIO.read(getClass().getResource("/icone-jogo/iconNatalino.png"));
			}else {
				imagem=ImageIO.read(getClass().getResource("/icone-jogo/icon.png"));
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		Toolkit toolkit =Toolkit.getDefaultToolkit();
		Image image= toolkit.getImage(getClass().getResource("/icone-jogo/icon.png"));
		Cursor c= toolkit.createCustomCursor(image, new Point(0,0), "img");
		
		frame.setIconImage(imagem);
//		frame.setAlwaysOnTop(true);
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
			if(savegame) {
				savegame=false;
				String[] opt1= {"level"};
				int[] opt2= {1};
				menu.saveGame(opt1,opt2,0);
				System.out.println("salvo");
			}
			if(cen.CcRun()) {
				cen.tick();
			}
			this.restartGame = false;	
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
			for(int i = 0; i < portas.size(); i++) {
				portas.get(i).tick();
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
//			ui.render(g);
			if(cen.CcRun()) {
				cen.render(g);
			}
		}
		if(gameState == "GAME_OVER") {
			//menugover.render(g);
		}else if(gameState == "MENU") {
			menu.render(g);
		}
		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		g.setFont(new Font("arial",Font.BOLD,20));
		
		
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
				System.out.println("FPS:"+ frames);
				frames=0;
				timer+=1000;
			}
			
		}
		
		stop();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_X){
			if(podeClicar) {
				podeClicar=false;
				player.clicouBueiros=true;
				player.clicouPortas=true;
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_P){
			if(Game.player.personagem=="Tai") {
				player.trocaPersonagem("Ace");
			}else {
				player.trocaPersonagem("Tai");
			}
		}
		if(!cen.CcRun()) {
			if(e.getKeyCode() == KeyEvent.VK_UP){
				
			}
//			if(e.getKeyCode() == KeyEvent.VK_LEFT){
//				player.camL=true;
//			}
//			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//				player.camR=true;
//			}
		}
		if(!cen.CcRun()) {
			if(e.getKeyCode() == KeyEvent.VK_D){
				player.atacando=false;
				player.combat=false;
				player.frames=0;
				player.right = true;
				player.parado=false;
				player.parando=false;
				player.moved=true;
//				if(player.isFreeX()!="esquerda") {
//					player.parede=false;
//				}
				
			}
			if(e.getKeyCode() == KeyEvent.VK_E){
				player.parado=false;
				player.dash=true;
				player.combat=false;
				player.frames=0;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_Q){
				player.framesAtk = 0;
				player.parado=false;
				player.combat=false;
				player.frames=0;
				if(player.dash) {
					player.dashS=true;
				}else if(player.subindo || player.caindo){
					//jump atackk
				}else {
					player.atacando = true;					
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				player.left = true;
				player.parado=false;
				player.parando=false;
				player.moved=true;
				player.combat=false;
				player.frames=0;
//				if(player.isFreeX()!="direita") {
//					player.parede=false;
//				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			savegame=true;
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
		}else if(e.getKeyCode() == KeyEvent.VK_S) {
			if(cen.CcRun()){
				cen.down();
			}
			
			
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(cen.CcRun()) {
				cen.next();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState = "MENU";
			menu.pause = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_X){
			podeClicar=true;
		}
		if(!cen.CcRun()) {
			if(e.getKeyCode() == KeyEvent.VK_UP){
				
			}
//			if(e.getKeyCode() == KeyEvent.VK_LEFT){
//				player.camL=false;
//				player.camx=0;
//			}
//			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//				player.camR=false;
//				player.camx=0;
//			}
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
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		menu.mx=e.getX()/2;
		menu.my=e.getY()/2;
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		menu.clicou=true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		menu.mx=e.getX()/2;
		menu.my=e.getY()/2;
		menu.clicou=false;	
		menu.soltou=true;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//  nao
		
	}

	
}
