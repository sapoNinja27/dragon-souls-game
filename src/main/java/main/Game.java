package main;

import main.interfaces.mouse.Mouse;
import main.enums.TipoAcao;
import main.processamento.GerenciadorEstado;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    public JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    private BufferedImage image;
    private final DadosGame dadosGame = new DadosGame();
    private final GerenciadorEstado gerenciadorEstado = new GerenciadorEstado();
    private final Mouse mouse = new Mouse();


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
        mouse.tick(frame);
        gerenciadorEstado.tick(mouse);
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
        if(nonNull(e)){
            mouse.mouseClicked(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        mouse.mouseEntered();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (nonNull(e)) {
            mouse.mouseMoved(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouse.mouseExited();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (nonNull(e)) {
            mouse.mousePressed();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (nonNull(e)) {
            mouse.mouseReleased(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouse.mouseDragged(e.getX(), e.getY());
    }
}
