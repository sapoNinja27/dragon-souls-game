package main.entidades;

import lombok.Setter;
import main.entidades.cenario.Fundo;
import main.entidades.cenario.LimiteCenario;
import main.entidades.cenario.moveis.ObjetosComMovimento;
import main.entidades.cenario.moveis.Transito;
import main.DadosGame;
import main.entidades.cenario.estaticos.Plataforma;
import main.entidades.cenario.iluminacao.ObjetoLuminoso;
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

import static main.enums.TipoMascara.*;

@Getter
public class Entidade {
    private final int width;
    private final int height;
    protected int depth;
    private final List<Mascara> mascaras = new ArrayList<>(0);
    @Setter
    protected double x;
    @Setter
    protected double y;

    protected int sombreamento = 180;
    protected int sombras = 30;
    protected boolean colidindo;
    protected Entidade entidadeColisora;
    protected boolean moved;

    protected DirecaoPlayer direcao = DirecaoPlayer.DIREITA;
    protected final int drawLimitX = 1000;
    protected final int drawLimitY = 500;
    protected final Random rand = new Random();

    public Entidade(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static final Comparator<Entidade> nodeSorter = Comparator.comparingInt(n0 -> n0.depth);

    public void limparMascaras() {
        this.mascaras.clear();
    }

    public void adicionarMascara(Mascara mascara) {
        this.mascaras.add(mascara);
    }

    public void mover(double x) {
        this.x = x;
    }

    public int getX() {
        return (int) this.x;
    }

    public double xDouble() {
        return this.x;
    }

    public int getY() {
        return (int) this.y;
    }

    public DirecaoPlayer getDirecao() {
        return direcao;
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

    public void atualizarColisao(Entidade alvo) {
        attColisao(buscarColisao(alvo), alvo);
    }

    public void atualizarGravidade(Plataforma chaoMaisProximo) {
        if (this instanceof Player && y != chaoMaisProximo.getY() - height) {
            Player player = (Player) this;
            if (!player.getGerenciadorMovimentos().getAcaoAtual().equals(MovimentoPlayer.SUBINDO)) {
                player.getGerenciadorMovimentos().setarAnimacao(MovimentoPlayer.CAINDO);
            }
        }
    }

    private boolean verificarColisao(Entidade entidade, TipoMascara mAtual, TipoMascara mAlvo) {
        boolean colidindo = false;
        for (Rectangle atual : getMascaras(mAtual)) {
            for (Rectangle alvo : entidade.getMascaras(mAlvo)) {
                colidindo = atual.intersects(alvo);
                if (colidindo) {
                    break;
                }
            }
        }
        return colidindo;
    }

    public boolean buscarColisao(Entidade alvo) {
        return verificarColisao(alvo, HITBOX, HURTBOX) ||
                verificarColisao(alvo, HITBOX, HITBOX);
    }

    private void attColisao(boolean colidindo, Entidade entidadeColisora) {
        this.colidindo = colidindo;
        this.entidadeColisora = entidadeColisora;
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

    public boolean dependeControleDePosicaoPlayer() {
        return false;
    }

    private List<Rectangle> getMascaras(TipoMascara... tipo) {
        return mascaras
                .stream()
                .filter(mascara -> Arrays.asList(tipo).contains(mascara.getTipoMascara()))
                .map(mascara -> new Rectangle(getX() + mascara.getX(), getY() + mascara.getY(), mascara.getWidth(), mascara.getHeight()))
                .collect(Collectors.toList());
    }

    public void tick(DadosGame dadosGame) {

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
}
