package main;

import configuracoes.DadosGame;
import enums.TipoAcao;
import graficos.Spritesheet;
import jObjects.Mouse.Mouse;
import processamento.GerenciadorEstado;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    public JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    private BufferedImage image;
    private final Spritesheet mouse = new Spritesheet("/cursor.png");
    private final DadosGame dadosGame = new DadosGame();
    private final GerenciadorEstado gerenciadorEstado = new GerenciadorEstado();


    private void setConfig() {
        requestFocus();
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(
                new Dimension(dadosGame.getScaleWidth(), dadosGame.getScaleHeight()));
        try {
            initFrame();
        } catch (Exception ignored) {}
        image = new BufferedImage(dadosGame.getWidth(), dadosGame.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public Game() {
        setConfig();
        gerenciadorEstado.iniciar(dadosGame);
    }

    public void attMouse() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        if (Mouse.over) {
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
        Image imagem;
        LocalDate myObj = LocalDate.now();
        String data = String.valueOf(myObj);
        String mes = data.substring(5, 7);
        String dia = data.substring(8, 10);
        if (mes.equals("12") && dia.equals("25")) {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResource("/icone-jogo/iconNatalino.png")));
        } else {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResource("/icone-jogo/icon.png")));
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
        attMouse();
        gerenciadorEstado.tick();
    }

    public void render() {
        if (this.getBufferStrategy() == null) {
            this.createBufferStrategy(3);
            return;
        }
        gerenciadorEstado.render(image, this.getBufferStrategy());
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
            Mouse.setCordinates(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
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
            Mouse.setCordinates(e.getX(), e.getY());
            Mouse.pressed = false;
            Mouse.released = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e != null) {
            Mouse.setCordinates(e.getX(), e.getY());
        }
    }

}
