package main.entidades;

import lombok.Setter;
import main.entidades.cenario.Fundo;
import main.entidades.cenario.LimiteCenario;
import main.entidades.cenario.moveis.ObjetosComMovimento;
import main.entidades.cenario.moveis.Transito;
import main.DadosGame;
import main.entidades.cenario.estaticos.Plataforma;
import main.entidades.cenario.iluminacao.ObjetoLuminoso;
import main.entidades.inimigos.Inimigo;
import main.entidades.players.Player;
import main.enums.DirecaoPlayer;
import main.enums.MovimentoPlayer;
import main.enums.TipoGame;
import main.enums.TipoMascara;
import lombok.Getter;
import main.world.Camera;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static main.enums.TipoMascara.*;

@Getter
public class Entidade {
    protected final int width;
    protected final int height;
    protected final int depth;
    protected final double speed;
    protected double vida;
    protected int vidaMaxima;
    protected double x;
    protected double y;

    protected final List<Mascara> mascaras = new ArrayList<>(0);

    protected int sombreamento = 180;
    protected int sombras = 30;

    protected final Set<Colisao> colisoes = new HashSet<>(0);
    @Setter
    protected boolean moved;

    protected DirecaoPlayer direcao = DirecaoPlayer.DIREITA;
    protected final int drawLimitX = 1000;
    protected final int drawLimitY = 500;
    protected final Random rand = new Random();

    protected boolean morto = false;
    public static final Comparator<Entidade> nodeSorter = Comparator.comparingInt(n0 -> n0.depth);

    public Entidade(int x, int y, int width, int height, int depth, double speed, int vidaMaxima) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.speed = speed;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
    }

    protected Entidade entidadeColisora(String alias) {
        return colisoes
                .stream()
                .filter(colisao -> colisao.getAlvo().equals(alias))
                .map(colisao -> colisao.getEntidadeAlvo())
                .findFirst()
                .orElse(null);
    }

    public void limparMascaras() {
        this.mascaras.clear();
    }

    protected void adicionarMascara(Mascara mascara) {
        this.mascaras.add(mascara);
    }

    public int getX() {
        return (int) this.x;
    }

    public int getY() {
        return (int) this.y;
    }

    protected double getSpeed(){
        return direcao.equals(DirecaoPlayer.DIREITA) ? speed : -speed;
    }
    public boolean drawDistance(Player player) {
        return (distanciaX(player.getX()) < getDrawLimitX() && distanciaY(player.getY()) < getDrawLimitY()) ||
                this instanceof Player ||
                this instanceof ObjetosComMovimento ||
                this instanceof Fundo ||
                this instanceof LimiteCenario;
    }

    public void atualizarSombreamento(int distancia, boolean isDia) {
        if (isDia) {
            sombras = 130;
            sombreamento = 5;
        } else {
            sombras = Math.abs(distancia / 2 - 255);
            sombreamento = distancia / 2;
            if (sombreamento > 180) {
                sombreamento = 180;
            }
            if (sombras < 50) {
                sombras = 50;
            }
        }
    }

    public void atualizarGravidade(Plataforma chaoMaisProximo) {
        if (this instanceof Player && y != chaoMaisProximo.getY() - height) {
            Player player = (Player) this;
            if (!player.getGerenciadorMovimentos().getAcaoAtual().equals(MovimentoPlayer.SUBINDO)) {
                player.getGerenciadorMovimentos().setarAnimacao(MovimentoPlayer.CAINDO);
            }
        }
    }

    public void verificarColisao(Entidade entidade) {
        for (Mascara atual : mascaras) {
            for (Mascara alvo : entidade.mascaras) {
                Rectangle rectangleAtual = new Rectangle(
                        getX() + atual.getX(), getY() + atual.getY(),
                        atual.getWidth(), atual.getHeight());
                Rectangle rectangleAlvo = new Rectangle(
                        entidade.getX() + alvo.getX(), alvo.getY() + entidade.getY(),
                        alvo.getWidth(), alvo.getHeight());
                Colisao colisao = Colisao.builder().entidadeAlvo(entidade).alvo(alvo.getAlias()).atual(atual.getAlias()).build();
                if (rectangleAtual.intersects(rectangleAlvo)) {
                    colisoes.add(colisao);
                } else {
                    colisoes.remove(colisao);
                }
            }
        }
    }

    public double distanciaX(int destino) {
        return Math.abs(this.x - destino);
    }

    public double distanciaY(int destino) {
        return Math.abs(y - destino);
    }

    public int calculateDistance(Entidade destino) {
        return (int) (Math.sqrt(this.x - destino.getX()) * (this.x - destino.getX()) + (this.y - destino.getY()) * (this.y - destino.getY()));
    }

    public boolean isClasseRelativa(Class<?> atual, Class<?> alvo) {
        return atual.isInstance(alvo) || atual.isAssignableFrom(alvo);
    }

    public boolean isClasseRelativa(Class<?> alvo) {
        if (alvo.isInstance(Transito.class)) {
            return true;
        }
        return isClasseRelativa(this.getClass(), alvo);
    }

    protected boolean colidindoComPlayer(Player player) {
        return colisoes.stream().anyMatch(colisao -> colisao.getEntidadeAlvo().equals(player));
    }

    public void tick(DadosGame dadosGame) {
        if (dadosGame.getEstadoGame().equals(TipoGame.NORMAL)) {
            lifesistem(dadosGame);
        }
    }

    private void lifesistem(DadosGame dadosGame) {
        if (vida <= 0) {
            if (this instanceof Player) {
                vida = getVidaMaxima();
            }
            acaoMorte();
//            dadosGame.gameOver();
        }
        if (this instanceof Player) {
            vida += 0.1;
            if(vida >= vidaMaxima){
                vida = vidaMaxima;
            }
        }
    }

    protected void acaoMorte() {

    }

    public boolean temMascara() {
        return !mascaras.isEmpty();
    }

    public void render(Graphics g, DadosGame dadosGame) {
        g.setColor(Color.red);
        if (temMascara() && dadosGame.getEstadoGame().equals(TipoGame.NORMAL)) {
            for (Mascara mascara : mascaras) {
                g.drawRect(
                        getX() - Camera.x + mascara.getX(),
                        getY() - Camera.y + mascara.getY(),
                        mascara.getWidth(),
                        mascara.getHeight());
            }
        }

    }

    public boolean isObjetoLuminoso() {
        return this instanceof ObjetoLuminoso;
    }

    public boolean isObjetoComMovimento() {
        return this instanceof ObjetosComMovimento;
    }

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
