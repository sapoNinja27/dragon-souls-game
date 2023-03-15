package main.entidades.players;

import main.entidades.Entidade;
import main.entidades.Mascara;
import main.entidades.cenario.estaticos.Plataforma;
import main.entidades.players.habilidades.Habilidade;
import main.utils.MatematicaUtils;
import main.utils.Spritesheet;
import lombok.Getter;
import lombok.Setter;
import main.DadosGame;
import main.enums.*;
import main.utils.Fontes;
import main.utils.ImageUtils;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static java.lang.Math.max;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static main.enums.MovimentoPlayer.*;
import static main.utils.ImageUtils.draw;
import static main.utils.StringUtils.write;
import static main.world.Camera.updateCamera;

@Getter
public class Player extends Entidade {
    private final GerenciadorMovimentos gerenciadorMovimentos = new GerenciadorMovimentos();
    private final UI ui = new UI();
    private final HabilitySet habilidades = new HabilitySet();
    protected Spritesheet spritesheet;

    private int nivel = 1;
    private int xp = 64;
    private int pontosHabilidade = 2;

    private int mana = 0, manaMaxima = 1000;
    private int danoBase = 30;
    private int dano;
    private boolean exausto = false;
    private int defesaBase = 7;
    private int defesa;
    private int resistenciaBase = 1;
    private int resistencia;

    private boolean showHudHelp = false;

    @Override
    public String toString() {
        return "{" +
                "x:" + x +
                "y:" + y +
                "nivel:" + nivel +
                "xp:" + xp +
                "pontosHabilidade:" + pontosHabilidade +
                "vida:" + vida +
                "vidaMaxima:" + vidaMaxima +
                "mana:" + mana +
                "manaMaxima:" + manaMaxima +
                "defesa:" + defesa +
                "resistencia:" + resistencia +
                "}";
    }


    public Player(
            int x,
            int y,
            int width,
            int height,
            int nivel,
            int xp,
            int pontosHabilidade,
            double vida,
            int vidaMaxima,
            int mana,
            int manaMaxima,
            int defesa,
            int resistencia
    ) {
        super(x, y, width, height, 14, 5, vidaMaxima);
        this.nivel = nivel;
        this.vidaMaxima = vidaMaxima;
        this.pontosHabilidade = pontosHabilidade;
        this.vida = vida;
        this.mana = mana;
        this.manaMaxima = manaMaxima;
        this.defesa = defesa;
        this.resistencia = resistencia;
        this.xp = xp;
        adicionarMascaras();
    }

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, 14, 5, 1000);
        adicionarMascaras();
    }

    public void desenharInfo(int x, int y, Graphics g, DadosGame dadosGame, Habilidade habilidade) {
        x = x - 10;
        String titulo = "Tai";
        String descricao = "Tai aumenta sua fúria em combate." + "\n" + "\n" + "Acúmulos de fúria conceden a ele:" + "\n" + "* 15% da fúria atual como resistências" + "\n" + "* 10 de armadura" + "\n" + "* 30 de dano de ataque" + "\n" + "\n" + "Ao atingir o máximo de fúria, o " + "\n" + "medidor de fúria decai, junto com" + "\n" + "os atributos, e só poderá voltar a" + "\n" + "ser acumulada ao esvaziar" + "\n" + "completamente.";
        String custo = "";
        MovimentoPlayer animacao = RESPIRANDO;

        if (nonNull(habilidade)) {
            titulo = habilidade.getTitulo();
            descricao = habilidade.getDescricao();
            custo = habilidade.getCusto();
            animacao = habilidade.getMovimentoPlayer();
        }

        render(g, dadosGame);

        gerenciadorMovimentos.setarAnimacao(animacao, 100);
        Font font = Fontes.CrimsonText(TipoFonte.REGULAR, 25);
        draw(g, titulo, x + 1075, y + 257, Color.WHITE, font);
        write(g, descricao, font.deriveFont(18f), x + 1075, y + 250);
        draw(g, custo, x + 1075, y + 530, Color.YELLOW, font.deriveFont(15f));
//		draw(g, "45", x + 1300, y + 500, Color.orange, 10);
    }

    @Override
    public void tick(DadosGame dadosGame) {
        super.tick(dadosGame);
        verificarAcao(dadosGame);
        gerenciadorMovimentos.tick();
        updateCamera(dadosGame);
        if (dadosGame.getEstadoGame().equals(TipoGame.NORMAL)) {
            furiaSistem();
        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        int tileSize = dadosGame.getTileSize(2);
        int x = 1150;
        int y = 132;
        if (dadosGame.getEstadoGame().equals(TipoGame.NORMAL)) {
            tileSize = tileSize / 2;
            ui.render(g, this);
            x = this.getX() - Camera.x;
            y = this.getY() - Camera.y;
        }
        final int fx = x;
        final int fy = y;
        final int fts = tileSize;
        ImageUtils.applySombreamento(g, spriteAtual(), tileSize, x, y, sombreamento, sombras, () -> g.drawImage(spriteAtual(), fx, fy, fts, fts, null), gerenciadorMovimentos.noAr());
        super.render(g, dadosGame);
    }

    protected BufferedImage spriteAtual() {
        return direcao.equals(DirecaoPlayer.DIREITA) ? gerenciadorMovimentos.getSprite() : ImageUtils.inverter(gerenciadorMovimentos.getSprite());
    }

    private void verificarAcao(DadosGame dadosGame) {
        if (moved) {
//            if (isFreeY()) {
            gerenciadorMovimentos.setarAnimacao(ANDANDO);
//            }
            x += direcao.equals(DirecaoPlayer.DIREITA) ? speed : -speed;
        } else {
            if (
                //isFreeY() &&
                    gerenciadorMovimentos.getAcaoAtual().equals(ANDANDO)) {
                gerenciadorMovimentos.setarAnimacao(PARANDO);
            }
        }
        if (gerenciadorMovimentos.getAcaoAtual().equals(PARANDO)) {
            x += direcao.equals(DirecaoPlayer.DIREITA) ? speed / 2 : -speed / 2;
        }
        if (gerenciadorMovimentos.getAcaoAtual().equals(SUBINDO)) {
            y -= 4;
        }
        if (gerenciadorMovimentos.getAcaoAtual().equals(CAINDO)) {
            y += 6;
        }
    }

    public void executarAcao(AcaoPlayer acaoPlayer) {
        if (acaoPlayer.equals(AcaoPlayer.DIREITA)) {
            direcao = DirecaoPlayer.DIREITA;
            moved = true;
        }
        if (acaoPlayer.equals(AcaoPlayer.ESQUERDA)) {
            direcao = DirecaoPlayer.ESQUERDA;
            moved = true;
        }

        if (acaoPlayer.equals(AcaoPlayer.PARAR)) {
            moved = false;
        }

        if (acaoPlayer.equals(AcaoPlayer.DASH)) {
            gerenciadorMovimentos.setarAnimacao(INVESTINDO);
        }
        if (acaoPlayer.equals(AcaoPlayer.CIMA)) {
            gerenciadorMovimentos.setarAnimacao(SUBINDO);
        }
        if (acaoPlayer.equals(AcaoPlayer.ULTIMATE)) {
            gerenciadorMovimentos.setarAnimacao(HABILIDADE_POSTURA_SELVAGEM);
        }
        if (acaoPlayer.equals(AcaoPlayer.SOCO_FRACO)) {
            gerenciadorMovimentos.setarAnimacao(ATACANDO);
        }

        if (acaoPlayer.equals(AcaoPlayer.HABILIDADE_1)) {
            mana += 30;
            gerenciadorMovimentos.setarAnimacao(HABILIDADE_POSTURA_OFENSIVA);
        }

        if (acaoPlayer.equals(AcaoPlayer.HUD_HELP)) {
            showHudHelp = !showHudHelp;
        }

        if (acaoPlayer.equals(AcaoPlayer.PARAR_PULO)) {
            if (gerenciadorMovimentos.noAr()) {
                gerenciadorMovimentos.setarAnimacao(CAINDO, 0.3);
            }
        }
    }

    public void furiaSistem() {
        if (exausto) {
            mana--;
            if (mana <= 0) {
                mana = 0;
                exausto = false;
            }
        }
        if (mana >= manaMaxima) {
            mana = manaMaxima;
            exausto = true;
        }
        defesa = defesaBase + MatematicaUtils.porcentagem(mana, 25);
        resistencia = resistenciaBase + MatematicaUtils.porcentagem(mana, 5);
        dano = danoBase + MatematicaUtils.porcentagem(mana, 15);
    }

    public void adicionarMascaras() {
        Mascara padrao = Mascara.builder().alias("corpo").x(20).y(11).height(52).width(20).build();
//		MascaraHitBox padrao = new MascaraHitBox("padrao", 20, 11, 20 ,52);
        adicionarMascara(padrao);

    }

    public List<Habilidade> getHabilidades() {
        return habilidades.getHabilidades();
    }

    public boolean disponivelParaGerar() {
        return gerenciadorMovimentos.getAcaoAtual().equals(RESPIRANDO);
    }

    public void sofrerDano(int dano) {
        boolean critico = rand.nextInt(100) < 45;
        final int danoFinal = Math.max(dano - MatematicaUtils.porcentagem(dano, resistencia) - defesa, 10);
        if (!exausto) {
            mana += dano - danoFinal;
        }
        vida -= critico ? danoFinal * 3.5 : danoFinal;
    }
}
