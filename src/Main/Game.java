package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Configuration.Configuracoes;
import Entidades.Entity;
import Entidades.Projetil;
import Entidades.Cenario.EscadaEsgoto;
import Entidades.Cenario.Plataforma;
import Entidades.Cenario.Porta;
import Entidades.Cenario.Portao;
import Entidades.Cenario.ObjetosComMovimento.LixoEsgoto;
import Entidades.Cenario.ObjetosComMovimento.ObjetosComMovimento;
import Entidades.Cenario.ObjetosComMovimento.Transito;
import Entidades.Enemies.Enemy;
import Entidades.Players.Ace;
import Entidades.Players.Player;
import Entidades.Players.Tai;
import Graficos.Spritesheet;
import Graficos.UI;
import Menu.Loading;
import Menu.Menu;
import World.World;
import enums.TipoAmbiente;
import enums.TipoGame;
import enums.TipoMenu;
import jObjects.Mouse;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener{
	private static final long serialVersionUID = 1L;
	public static boolean podeClicar=true;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	private BufferedImage image;
	public static Loading loading;
	public static boolean isLoading;
	public static List<Entity> entities;
	public static List<ObjetosComMovimento> objetos;
	public static List<Enemy> enemies;
	public static List<Porta> portas;
	public static List<Porta> portaTerraco;
	public static List<Portao> portoes;
	public static List<EscadaEsgoto> escadasDeEsgoto;
	public static List<Plataforma> bueiros;
	public static List<Projetil> bullets;
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
	//mouse
	Spritesheet mouse;
	public UI ui;
	public static Cutscene cen;
	public static Menu menu;
	public Game(){
//		requestFocus();
		rand = new Random();
		loading=new Loading();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(Configuracoes.WIDTH*Configuracoes.SCALE,Configuracoes.HEIGHT*Configuracoes.SCALE));
		initFrame();
		//Inicializando objetos.
		ui = new UI();
		image = new BufferedImage(Configuracoes.WIDTH,Configuracoes.HEIGHT,BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Projetil>();
		portas = new ArrayList<Porta>();
		portaTerraco = new ArrayList<Porta>();
		portoes = new ArrayList<Portao>();
		bueiros = new ArrayList<Plataforma>();
		escadasDeEsgoto=new ArrayList<EscadaEsgoto>();
		objetos=new ArrayList<ObjetosComMovimento>();
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
		//mouse
		mouse = new Spritesheet("/cursor.png");
		
		
		
		
		player = new Tai(0,0);
		player2=new Ace(0,0);
		entities.add(player);
		entities.add(player2);
		
		
		menu = new Menu();
		cen=new Cutscene();
	}
	public void gerarObj() {
		if(Configuracoes.local==TipoAmbiente.ESGOTOS) {
			if(Game.rand.nextInt(50)==0){
				ObjetosComMovimento am= new LixoEsgoto(Game.player.getX()+500,3900+Game.rand.nextInt(2)*32);
				am.setSpeed(Game.rand.nextInt(3));
				if(Game.objetos.size()<Game.rand.nextInt(10)) {
					Game.objetos.add(am);
				}
			}
			
		}else if(Configuracoes.local==TipoAmbiente.RUA) {
			if(Configuracoes.dia) {
				if(Game.rand.nextInt(25)==0){
					ObjetosComMovimento am= new Transito(Game.player.getX()-1100,2300+Game.rand.nextInt(5));
					am.setSpeed(Game.rand.nextInt(13));
					if(Game.objetos.size()<Game.rand.nextInt(3)) {
						Game.objetos.add(am);
					}
				}
			}else {
				if(Game.rand.nextInt(100)==0){
					ObjetosComMovimento am= new Transito(Game.player.getX()-1100,2300+Game.rand.nextInt(5));
					am.setSpeed(Game.rand.nextInt(13));
					if(Game.objetos.size()<Game.rand.nextInt(2)) {
						Game.objetos.add(am);
					}
				}
			}
			
			
		}
	}
	/**
	 * Atualiza a imagem do mouse
	 */
	public void attMouse() {
		System.out.println(Mouse.hover);
		if (Mouse.hover) {
			try {
				//hover
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image image = mouse.getSprite(32,0,32,32);
				Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
				frame.setCursor(c);
			} catch (Exception e) {

			}
		} else {
			//standart
			try {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image image = mouse.getSprite(0,0,32,32);
				Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
				frame.setCursor(c);
			} catch (Exception e) {

			}
		}

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
//		Toolkit toolkit =Toolkit.getDefaultToolkit();
//		Image image= toolkit.getImage(getClass().getResource("/icone-jogo/icon.png"));
//		Cursor c= toolkit.createCustomCursor(image, new Point(0,0), "img");
		
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
		attMouse();
		loading.tick();
		if(isLoading) {
			
		}else {
			if(Configuracoes.estadoGame==TipoGame.NORMAL) {
				if(cen.CcRun()) {
					cen.tick();
				}
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
				gerarObj();
				for(int i = 0; i < objetos.size(); i++) {
					objetos.get(i).tick();
				}
			}
//			else if(Configuracoes.estadoGame==TipoGame.GAMEOVER) {
//				
//				
//				
//				if(restartGame) {
//					restartGame = false;
//					Configuracoes.estadoGame=TipoGame.NORMAL;
////					CUR_LEVEL = 1;
////					String newWorld = "level"+CUR_LEVEL+".png";
//					//System.out.println(newWorld);
////					World.restartGame(newWorld);
//				}
//			}
			else if(Configuracoes.estadoGame==TipoGame.MENU) {
				menu.tick();
			}
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
		g.fillRect(0, 0,Configuracoes.WIDTH,Configuracoes.HEIGHT);
		if(Configuracoes.estadoGame==TipoGame.NORMAL) {
//			world.render(g);
			
			Collections.sort(entities,Entity.nodeSorter);
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}
			for(int i = 0; i < bullets.size(); i++) {
				bullets.get(i).render(g);
			}
			for(int i = 0; i < objetos.size(); i++) {
				objetos.get(i).render(g);
			}
//			ui.render(g);
			if(cen.CcRun()) {
				cen.render(g);
			}
		}
		if(Configuracoes.estadoGame==TipoGame.MENU) {
			menu.render(g);
		}
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,Configuracoes.WIDTH*Configuracoes.SCALE,Configuracoes.HEIGHT*Configuracoes.SCALE,null);

		loading.render(g);

		
		
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
			Player.trocaPersonagem(player,player2);
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
			String[] opt1= {"level"};
			int[] opt2= {1};
			menu.saveGame(opt1,opt2,0);
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
			if(Configuracoes.estadoMenu != TipoMenu.INICIAL) {
				if(Configuracoes.estadoGame == TipoGame.MENU) {
					Configuracoes.estadoGame = TipoGame.NORMAL;
				}else if(Configuracoes.estadoGame == TipoGame.NORMAL) {
					Configuracoes.estadoGame = TipoGame.MENU;
				}
			}
			
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
					if(!player.caindo && !player.subindo) {
						player.parando=true;
					}
				}

			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				player.left = false;
				player.moved=false;
				if(!player.right) {
					if(!player.caindo && !player.subindo) {
						player.parando=true;
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
		if (e != null) {
			Mouse.setCordinates(e.getX()/2, e.getY()/2);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e != null ) {
			Mouse.pressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e != null) {
			Mouse.setCordinates(e.getX()/2, e.getY()/2);
			Mouse.pressed = false;
			Mouse.released = true;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	
}
