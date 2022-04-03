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
import java.io.Serial;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import Configuration.Configuracoes;
import Menu.GerenciadorEstado;
import Graficos.Spritesheet;
import Menu.Loading;
import enums.TipoAcao;
import jObjects.Mouse;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	@Serial
	private static final long serialVersionUID = 1L;
	public  JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	private BufferedImage image;
	private final Spritesheet mouse = new Spritesheet("/cursor.png");;
	public GerenciadorEstado gerenciadorEstado;


	private void setConfig() {
		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(
				new Dimension(Configuracoes.WIDTH * Configuracoes.SCALE, Configuracoes.HEIGHT * Configuracoes.SCALE));
		try{
			initFrame();
		}catch (Exception ignored){}
		image = new BufferedImage(Configuracoes.WIDTH, Configuracoes.HEIGHT, BufferedImage.TYPE_INT_RGB);
	}

	public Game() {
		setConfig();
		gerenciadorEstado.iniciar();
	}

	public void attMouse() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		if (Mouse.hover) {
			Image image = mouse.getSprite(32, 0, 32, 32);
			Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
			frame.setCursor(c);
		} else {
			Image image = mouse.getSprite(0, 0, 32, 32);
			Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
			frame.setCursor(c);
		}

	}

	public void initFrame() throws IOException {
		frame = new JFrame("Dragon Soul I");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		Image imagem = null;
		LocalDate myObj = LocalDate.now();
		String data = String.valueOf(myObj);
		String mes = data.substring(5, 7);
		String dia = data.substring(8, 10);
		if (mes.equals("12") && dia.equals("25")) {
			imagem = ImageIO.read(getClass().getResource("/icone-jogo/iconNatalino.png"));
		} else {
			imagem = ImageIO.read(getClass().getResource("/icone-jogo/icon.png"));
		}
		frame.setIconImage(imagem);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public void tick() {
		Configuracoes.dia = false;
		attMouse();
		gerenciadorEstado.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, Configuracoes.WIDTH, Configuracoes.HEIGHT);

		gerenciadorEstado.render(g);

		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,
				Configuracoes.WIDTH * Configuracoes.SCALE,
				Configuracoes.HEIGHT * Configuracoes.SCALE,
				null);
		Loading.render(g);
		bs.show();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amongOfTicks = 60.0;
		double delta = 0;
		double ns = 1000000000 / amongOfTicks;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS:" + frames);
				frames = 0;
				timer += 1000;
			}
		}
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gerenciadorEstado.gerenciarCliques(e, TipoAcao.PRESSIONAR);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gerenciadorEstado.gerenciarCliques(e, TipoAcao.SOLTAR);
	}

	@Override
	public void keyTyped(KeyEvent e) {

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
			Mouse.setCordinates(e.getX() / 2, e.getY() / 2);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e != null) {
			Mouse.pressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e != null) {
			Mouse.setCordinates(e.getX() / 2, e.getY() / 2);
			Mouse.pressed = false;
			Mouse.released = true;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

}
