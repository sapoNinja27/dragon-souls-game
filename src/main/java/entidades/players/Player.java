package entidades.players;

import configuracoes.DadosGame;
import entidades.Entidade;
import entidades.cenario.objetosluminosos.ObjetoLuminoso;
import entidades.mascaras.MascaraHitBox;
import entidades.players.tai.Tai;
import enums.*;
import graficos.Spritesheet;
import graficos.UI;
import interfaces.HudCommons;
import interfaces.MenuCommons;
import utils.FonteUtils;
import utils.ImageUtils;
import world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static enums.MovimentoPlayer.*;
import static java.util.Objects.nonNull;
import static utils.ImageUtils.draw;
import static utils.StringUtils.write;

public abstract class Player extends Entidade implements MenuCommons, HudCommons {
    protected int posicao = 0;
    protected boolean jaParou;
    protected int index = 0;
    protected double speed = 5;
    private int limiteAltura = 0;
    private MovimentoPlayer ultimaAcao = RESPIRANDO;
    private MovimentoPlayer acaoAtual = RESPIRANDO;
    private final UI ui = new UI();
    protected Spritesheet spritesheet;

    private int nivel = 1;
    private int xp = 64;
    private int pontosHabilidade = 2;

    //TODO adicionar variação de vida pra cada personagem
    protected int vida = 958, vidaMaxima = 1000;
    //TODO o nome desse atributo varia visualmente para cada personagem, sendo ace concentração, tai furia e sander foco
    protected int mana = 500, manaMaxima = 1000;
    //TODO reduz valor fixo de dano
    protected int defesa = 10;
    //TODO reduz porcentagem de dano
    protected int resistencia = 1;

    protected List<BufferedImage> spritesDireita = new ArrayList<>();
    protected List<BufferedImage> spritesEsquerda = new ArrayList<>();

    private boolean ePrimeiroSpawn;
    private boolean dentro;

    //Atributos de execução de acão de direção
    private boolean direita, cima, esquerda, baixo;
    //Atributos de executando ação
    protected boolean caindo, subindo, pousando, andando, parando, atacando, respirando = true, respirandoEmCombate, investindo;

    private Player playerUm;
    protected boolean isPlayerDois;
    protected boolean isFreeX, isFreeY;
    private int frames = 0;

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getPontosHabilidade() {
        return pontosHabilidade;
    }

    public void setPontosHabilidade(int pontosHabilidade) {
        this.pontosHabilidade = pontosHabilidade;
    }

    public boolean isDireita() {
        return direita;
    }

    public boolean isCima() {
        return cima;
    }

    public boolean isEsquerda() {
        return esquerda;
    }

    public boolean isBaixo() {
        return baixo;
    }

    public void setPlayerUm() {
        this.isPlayerDois = false;
    }

    public void setPlayerDois() {
        this.isPlayerDois = true;
    }

    public Player(int x, int y, DadosGame dadosGame) {
        super(x, y, 0, 0);
        atualizarSprites(dadosGame);
        adicionarMascaras();
    }

    @Override
    public void tick(DadosGame dadosGame) {
        depth = 1;
        verificarAcao();
        updateCamera(dadosGame);
        if (dadosGame.getEstadoGame().equals(TipoGame.NORMAL)) {
            atualizarDirecaoPlayer();

            lifesistem(dadosGame);
            if (!isPlayerDois) {
//				nBot();
            } else {
                bot();
            }
        }
    }

    public void desenharInfo(int x, int y, Graphics g, DadosGame dadosGame, Habilidade habilidade) {
        x = x - 10;
        String titulo = "Tai";
        String descricao = "Tai aumenta sua fúria em combate." +
                "\n" +
                "\n" +
                "Acúmulos de fúria conceden a ele:" +
                "\n" +
                "* 15% da fúria atual como resistências" +
                "\n" +
                "* 10 de armadura" +
                "\n" +
                "* 30 de dano de ataque" +
                "\n" +
                "\n" +
                "Ao atingir o máximo de fúria, o " +
                "\n" +
                "medidor de fúria decai, junto com" +
                "\n" +
                "os atributos, e só poderá voltar a" +
                "\n" +
                "ser acumulada ao esvaziar" +
                "\n" +
                "completamente.";
        String custo = "";
        MovimentoPlayer animacao = RESPIRANDO;

        if (nonNull(habilidade)) {
            titulo = habilidade.getTitulo();
            descricao = habilidade.getDescricao();
            custo = habilidade.getCusto();
            animacao = habilidade.getMovimentoPlayer();
        }

        render(g, dadosGame);

        setarAnimacao(animacao);
        Font font = FonteUtils.CrimsonText(TipoFonte.REGULAR, 25);
        draw(g, titulo, x + 1075, y + 257, Color.WHITE, font);
        write(g, descricao, font.deriveFont(18f), x + 1075, y + 250);
        draw(g, custo, x + 1075, y + 530, Color.YELLOW, font.deriveFont(15f));
//		draw(g, "45", x + 1300, y + 500, Color.orange, 10);
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        int tileSize = dadosGame.getTileSize(2);
        int x = 1150;
        int y = 132;
        if (dadosGame.getEstadoGame().equals(TipoGame.NORMAL)) {
            tileSize = tileSize / 2;
            if (!isPlayerDois) {
                ui.render(g, dadosGame.getPlayer());
            }
            x = this.getX() + posicao - Camera.x;
            y = this.getY() - Camera.y;
        }
        sombrasChao(g, spriteAtual().get(index), x, y, tileSize);
        g.drawImage(spriteAtual().get(index), x, y, tileSize, tileSize, null);
        atualizarIluminacao(g, spriteAtual(), x, y, tileSize);
    }

    protected List<BufferedImage> spriteAtual() {
        return direcaoPlayer.equals(DirecaoPlayer.DIREITA) ? spritesDireita : spritesEsquerda;
    }

    public void teleportar(int x, int y) {
//        if (!isPlayerDois) {
//            setX(x);
//            setY(y);
//        } else {
//            if (this.direcaoPlayer.equals(DirecaoPlayer.ESQUERDA)) {
//                setX(x + 20);
//                setY(y);
//            }
//            if (this.direcaoPlayer.equals(DirecaoPlayer.DIREITA)) {
//                setX(x - 20);
//                setY(y);
//            }
//        }
    }

    public void verificarAcao() {
        if (direita || esquerda) {
            if (isFreeY) {
                setarAnimacao(ANDANDO);
            }
            mover(direcaoPlayer.equals(DirecaoPlayer.DIREITA) ? xDouble() + speed : xDouble() - speed);
        } else {
            if (isFreeY && andando) {
                setarAnimacao(PARANDO);
            }
        }
        if (parando) {
            mover(direcaoPlayer.equals(DirecaoPlayer.DIREITA) ? xDouble() + speed / 2 : xDouble() - speed / 2);
        }
        controlaMovimentosPulo();
        executarAnimacao();
    }

    private void controlaMovimentosPulo() {
        if (cima) {
            setarAnimacao(SUBINDO);
        } else if (limiteAltura != 0) {
            setarAnimacao(CAINDO);
        }
        if (limiteAltura == 96) {
            cima = false;
            setarAnimacao(CAINDO);
        }
        if (limiteAltura == 0) {
            setarAnimacao(POUSANDO);
        }
    }

    public void executarAnimacao() {
        if (andando) {
            caminhar();
        }
        if (parando) {
            parar();
        }
        if (respirando) {
            respirar();
        }
        if (subindo) {
            pular();
        }
        if (caindo) {
            cair();
        }
        if (pousando) {
            pousar();
        }
    }

    private void pular() {
        index = Math.max(index, 13);
        frames++;
        limiteAltura += 4;
        this.y -= 4;
        if (frames >= 12) {
            index++;
            frames = 0;
        }
        if (index >= 15) {
            index = 13;
        }
    }

    private void cair() {
        index = Math.max(index, 15);
        frames++;
        limiteAltura -= 4;
        this.y += 4;
        if (frames >= 8) {
            index++;
            frames = 0;
        }
        if (index >= 18) {
            index = 15;
        }
    }

    private void pousar() {
        index = Math.max(index, 18);
        frames++;
        if (frames >= 20) {
            setarAnimacao(RESPIRANDO);
            frames = 0;
        }
    }

    private void caminhar() {
        index = Math.max(index, 4);
        frames++;
        if (frames >= 6) {
            index++;
            frames = 0;
        }
        if (index >= 12) {
            index = 5;
        }
    }

    private void parar() {
        index = 12;
        frames++;
        if (frames >= 15) {
            frames = 0;
            index = 0;
            setarAnimacao(RESPIRANDO);
        }
    }

    private void respirar() {
        index = Math.max(index, 0);
        frames++;
        if (frames >= 15) {
            index++;
            frames = 0;
        }
        if (index >= 4) {
            index = 0;
        }
    }

    public void executarAcao(Boolean isFree, AcaoPlayer acaoPlayer) {
        this.isFreeY = isFree;
        if (acaoPlayer.equals(AcaoPlayer.DIREITA)) {
            direita = true;
        }
        if (acaoPlayer.equals(AcaoPlayer.ESQUERDA)) {
            esquerda = true;
        }

        if (acaoPlayer.equals(AcaoPlayer.PARAR)) {
            esquerda = false;
            direita = false;
        }

        if (acaoPlayer.equals(AcaoPlayer.DASH)) {

        }
        if (acaoPlayer.equals(AcaoPlayer.CIMA)) {
            cima = true;
        }
        if (acaoPlayer.equals(AcaoPlayer.ULTIMATE)) {

        }
        if (acaoPlayer.equals(AcaoPlayer.SOCO_FRACO)) {

        }

        if (acaoPlayer.equals(AcaoPlayer.PARAR_PULO)) {
            cima = false;
//			moved = false;
        }
    }

    public boolean isDentro() {
        return dentro;
    }

    public boolean isPrimeiroSpawn() {
        return ePrimeiroSpawn;
    }

    public void setPrimeiroSpawn() {
        this.ePrimeiroSpawn = true;
    }

    public void atualizarDirecaoPlayer() {
        if (direita) {
            direcaoPlayer = DirecaoPlayer.DIREITA;
        } else if (esquerda) {
            direcaoPlayer = DirecaoPlayer.ESQUERDA;
        }
    }

    public void updateCamera(DadosGame dadosGame) {
        Camera.x = Camera.clamp(250 + this.getX() - (dadosGame.getWidth() / 2), 0,
                dadosGame.getTileSize(dadosGame.getWordWidth()) - dadosGame.getWidth());
        Camera.y = Camera.clamp(this.getY() - (dadosGame.getHeight() / 2) - 53, 0,
                dadosGame.getTileSize(dadosGame.getWordHeight()) - dadosGame.getHeight());
    }


    public void lifesistem(DadosGame dadosGame) {
        if (vida <= 0) {
            vida = 0;
            dadosGame.gameOver();
        }
    }

    public void atualizarSombreamento(ObjetoLuminoso objetoLuminoso, DadosGame dadosGame) {
        if (dadosGame.isDia()) {
            sombras = 130;
            sombreamento = 5;
        } else {
            if (distanciaX(objetoLuminoso.getX()) < 300 && distanciaY(objetoLuminoso.getY()) < 500) {
                sombreamento = (int) distanciaX(objetoLuminoso.getX()) * 255 / 300;
                sombras = Math.abs(sombreamento - 255);
            }
            if (sombreamento > 204) {
                sombreamento = 204;
            }
            if (sombras < 51) {
                sombras = 51;
            }
        }
    }

    public void atualizarIluminacao(Graphics g, List<BufferedImage> sprite, int x, int y, int tileSize) {
        g.drawImage(
                ImageUtils.sombreamento(sprite.get(index), new Color(0, 0, 0, sombreamento)),
                x,
                y,
                tileSize,
                tileSize,
                null);
    }

    public void sombrasChao(Graphics g, BufferedImage sprite, int x, int y, int tileSize) {
        if (!subindo && !caindo) {
            g.drawImage(ImageUtils.inverterV(ImageUtils.sombreamento(sprite, new Color(0, 0, 0, sombras))), x, y + tileSize, tileSize, tileSize / 2, null);
        } else {
            g.drawImage(ImageUtils.sombreamento(sprite, new Color(0, 0, 0, sombras)), x + 10, y + tileSize, tileSize, tileSize / 2, null);
        }

    }


    public void adicionarMascaras() {
        MascaraHitBox padrao = new MascaraHitBox("padrao", 20, 11, 20, 52);
//		MascaraHitBox padrao = new MascaraHitBox("padrao", 20, 11, 20 ,52);
        adicionarMascara(padrao);

    }

    public void setPlayer(Player player) {
        this.playerUm = isPlayerDois ? player : null;
    }

    public void bot() {
//		movedBot();
        depth = 5;
//		if(distanciaX(Game.player.getX(),Game.player2.getX())<100 && Game.player.up) {
//			up=true;
//			parado=false;
//		}else {
//			up=false;
//
//		}
        if (distanciaX(playerUm.getX()) < 20) {
//			moved = false;
        }
//		if (playerUm.moved) {
//			if (distanciaX(playerUm.getX()) / 20 > 4) {
//				speed = distanciaX(playerUm.getX()) / 25;
//			}
//		} else {
//			if (distanciaX(playerUm.getX()) / 25 > 4) {
//				speed = distanciaX(playerUm.getX()) / 30;
//			}
//		}

//		if (playerUm.dash) {
//			parado = false;
//			dash = true;
//			jaParou = false;
//		} else {
//			dash = false;
//			if (!moved) {
//				if (!jaParou) {
//					parando = true;
//				}
//			}
//
//		}
        if (playerUm.direcaoPlayer == DirecaoPlayer.ESQUERDA) {
            if (playerUm.getX() < getX()) {
                direcaoPlayer = DirecaoPlayer.ESQUERDA;
                if (playerUm.getX() < getX() && distanciaX(playerUm.getX()) > 70) {
                    parado = false;
                    esquerda = true;
                    direita = false;
//					moved = true;
                    jaParou = false;
                } else {
                    if (!jaParou) {
                        esquerda = false;
//						moved = false;
//						parando = true;
                    }
                }
            } else {
                direcaoPlayer = DirecaoPlayer.DIREITA;
            }
        } else if (playerUm.direcaoPlayer == DirecaoPlayer.DIREITA) {
            if (playerUm.getX() > getX()) {
                direcaoPlayer = DirecaoPlayer.DIREITA;
                if (playerUm.getX() > getX() && distanciaX(playerUm.getX()) > 70) {
                    parado = false;
                    direita = true;
                    esquerda = false;
//					moved = true;
                    jaParou = false;
                } else {
                    if (!jaParou) {
                        direita = false;
//						moved = false;
//						parando = true;
                    }
                }
            } else {
                direcaoPlayer = DirecaoPlayer.DIREITA;
            }
        }
    }

    protected void setarAnimacao(MovimentoPlayer movimento) {
        if (movimento.equals(ANDANDO) &&
                (acaoAtual.equals(SUBINDO) || acaoAtual.equals(CAINDO))) {
            return;
        }
        if (movimento.equals(POUSANDO) && !ultimaAcao.equals(CAINDO)) {
            return;
        }
        if (movimento.equals(SUBINDO) &&
                (ultimaAcao.equals(CAINDO) || ultimaAcao.equals(SUBINDO))) {
            return;
        }
        ultimaAcao = acaoAtual;
        acaoAtual = movimento;

        caindo = movimento.equals(CAINDO);
        subindo = movimento.equals(SUBINDO);
        pousando = movimento.equals(POUSANDO);
        andando = movimento.equals(ANDANDO);
        parando = movimento.equals(PARANDO);
        atacando = movimento.equals(ATACANDO);
        respirando = movimento.equals(RESPIRANDO);
        respirandoEmCombate = movimento.equals(RESPIRANDO_EM_COMBATE);
        investindo = movimento.equals(INVESTINDO);
    }

    public Tai asTai() {
        return (Tai) this;
    }

//    public Ace asAce() {
//        return (Ace) this;
//    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVida() {
        return vida;
    }

    public int getMana() {
        return mana;
    }

    public int getManaMaxima() {
        return manaMaxima;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getResistencia() {
        return resistencia;
    }

    public boolean isTai() {
        return this instanceof Tai;
    }

    public boolean isAce() {
        return false;
    }

    public boolean isSander() {
        return false;
    }
}
