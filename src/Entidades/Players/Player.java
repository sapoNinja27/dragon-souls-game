package Entidades.Players;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Configuration.Configuracoes;
import Entidades.Entidade;
import Entidades.ObjetoLuminoso;
import Graficos.Spritesheet;
import Graficos.UI;
import Menu.ImageUtils;
import Menu.MascaraHitBox;
import World.Camera;
import World.World;
import enums.*;

public class Player extends Entidade {
	protected int posicao = 0;
	protected boolean jaParou;
	protected int index = 0;
	protected double speed = 5;
	private int limiteAltura = 0;
	private String ultimaAcao = "respirando";
	private String acaoAtual = "respirando";
	private final UI ui;
	protected Spritesheet spritesheet;

	//TODO adicionar variação de vida pra cada personagem
	protected int vida = 958, vidaMaxima = 1000;
	//TODO o nome desse atributo varia visualmente para cada personagem, sendo ace concentração, tai furia e sander foco
	protected int mana = 500, manaMaxima = 1000;
	//TODO reduz valor fixo de dano
	protected int defesa = 10;
	//TODO reduz porcentagem de dano
	protected int resistencia = 1;

	protected List<BufferedImage> spritesDireita = new ArrayList<>();
	protected  List<BufferedImage> spritesEsquerda = new ArrayList<>();

	private boolean ePrimeiroSpawn;
	private boolean dentro;

	//Atributos de execução de acão de direção
	private boolean direita, cima, esquerda, baixo;
	//Atributos de executando ação
	public boolean caindo, subindo, pousando, andando, parando, atacando, respirando = true, respirandoEmCombate, investindo;

	private Player playerUm;
	protected boolean isPlayerDois;
	protected boolean isFreeX, isFreeY;
	private int frames = 0;




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

	public void setPlayerUm(){
		this.isPlayerDois = false;
	}

	public void setPlayerDois(){
		this.isPlayerDois = true;
	}



	public Player(int x, int y) {
		super(x, y, 0, 0);
		atualizarSprites();
		ui = new UI();
	}

	public void tick(){
		depth = 1;
		ui.tick(this);
		verificarAcao();

		adicionarMascaras();
		atualizarDirecaoPlayer();

		lifesistem();
		if(!isPlayerDois) {
			updateCamera();
//				nBot();
		}else {
			bot();
		}
	}

	public void render(Graphics g) {
		if(!isPlayerDois){
			ui.render(g);
		}
		sombrasChao(g, spriteAtual().get(index));
		g.drawImage(spriteAtual().get(index), this.getX() + posicao - Camera.x,this.getY() - Camera.y, 64, 64, null);
		atualizarIluminacao(g, spriteAtual());
	}

	public void atualizarSprites(){

	}



	protected List<BufferedImage> spriteAtual(){
		return direcaoPlayer.equals(DirecaoPlayer.DIREITA) ? spritesDireita : spritesEsquerda;
	}

	public void teleportar(int x, int y) {
		if(!isPlayerDois){
			setX(x);
			setY(y);
		} else {
			if(this.direcaoPlayer.equals(DirecaoPlayer.ESQUERDA)) {
				setX(x+20);
				setY(y);
			}
			if(this.direcaoPlayer.equals(DirecaoPlayer.DIREITA)) {
				setX(x-20);
				setY(y);
			}
		}
	}

	public void verificarAcao() {
		if (direita || esquerda) {
			if (isFreeY) {
				setarAnimacao("andando");
			}
			mover(direcaoPlayer.equals(DirecaoPlayer.DIREITA) ? xDouble() + speed : xDouble() - speed);
		} else {
			if (isFreeY && andando) {
				setarAnimacao("parando");
			}
		}
		if (parando) {
			mover(direcaoPlayer.equals(DirecaoPlayer.DIREITA) ? xDouble() + speed / 2 : xDouble() - speed / 2);
		}
		controlaMovimentosPulo();
		executarAnimacao();
	}
	private void controlaMovimentosPulo(){
		if (cima && !ultimaAcao.equals("caindo")) {
			setarAnimacao("subindo");
		} else if(limiteAltura != 0){
			setarAnimacao("caindo");
		}
		if(limiteAltura == 96){
			cima = false;
			setarAnimacao("caindo");
		}
		if(limiteAltura == 0 && ultimaAcao.equals("caindo")){
			setarAnimacao("pousando");
		}
	}
	public void executarAnimacao() {
		if(andando){
			caminhar();
		}
		if(parando){
			parar();
		}
		if(respirando){
			respirar();
		}
		if(subindo){
			pular();
		}
		if(caindo){
			cair();
		}
		if(pousando){
			pousar();
		}
	}

	private void pular(){
		index = Math.max(index, 13);
		frames++;
		limiteAltura += 4;
		this.y -= 4;
		if(frames >= 12){
			index++;
			frames = 0;
		}
		if(index >= 15){
			index = 13;
		}
	}

	private void cair(){
		index = Math.max(index, 15);
		frames++;
		limiteAltura -= 4;
		this.y += 4;
		if(frames >= 8){
			index++;
			frames = 0;
		}
		if(index >= 18){
			index = 15;
		}
	}

	private void pousar(){
		index = Math.max(index, 18);
		frames++;
		if(frames >= 20){
			setarAnimacao("respirando");
			frames = 0;
		}
	}

	private void caminhar(){
		index = Math.max(index, 4);
		frames++;
		if(frames >= 6){
			index++;
			frames = 0;
		}
		if(index >= 12){
			index = 5;
		}
	}

	private void parar(){
		index = 12;
		frames++;
		if(frames >= 15){
			frames = 0;
			index = 0;
			setarAnimacao("respirando");
		}
	}

	private void respirar(){
		index = Math.max(index, 0);
		frames++;
		if(frames >= 15){
			index++;
			frames = 0;
		}
		if(index >= 4){
			index = 0;
		}
	}

	public void executarAcao(Boolean isFree, AcaoPlayer acaoPlayer){
		this.isFreeY = isFree;
		if (acaoPlayer.equals(AcaoPlayer.DIREITA)){
			direita = true;
		}
		if (acaoPlayer.equals(AcaoPlayer.ESQUERDA)){
			esquerda = true;
		}

		if (acaoPlayer.equals(AcaoPlayer.PARAR)){
			esquerda = false;
			direita = false;
		}

		if (acaoPlayer.equals(AcaoPlayer.DASH)){

		}
		if(acaoPlayer.equals(AcaoPlayer.CIMA)){
			cima = true;
		}
		if(acaoPlayer.equals(AcaoPlayer.ULTIMATE)){

		}
		if (acaoPlayer.equals(AcaoPlayer.SOCO_FRACO)){

		}

		if (acaoPlayer.equals(AcaoPlayer.PARAR_PULO)){
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

	public void updateCamera() {
		Camera.x = Camera.clamp(250 + this.getX() - (Configuracoes.WIDTH / 2), 0,
				World.WIDTH * Configuracoes.TILE_SIZE - Configuracoes.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Configuracoes.HEIGHT / 2) - 53, 0,
				World.HEIGHT * Configuracoes.TILE_SIZE - Configuracoes.HEIGHT);
	}


	public void lifesistem() {
		if (vida <= 0) {
			vida = 0;
			Configuracoes.estadoGame = TipoGame.MENU;
			Configuracoes.estadoMenu = TipoMenu.GAMEOVER;
		}
	}

	public void atualizarSombreamento(ObjetoLuminoso objetoLuminoso){
		if (distanciaX(objetoLuminoso.getX()) < 300 && distanciaY( objetoLuminoso.getY()) < 500) {
			sombreamento = (int)distanciaX(objetoLuminoso.getX()) * 255 / 300;
			sombras =  Math.abs(sombreamento - 255);
		}
		if(sombreamento > 204){
			sombreamento = 204;
		}
	}

	public void atualizarIluminacao(Graphics g, List<BufferedImage> sprite) {
		g.drawImage(ImageUtils.sombreamento(sprite.get(index), new Color(0,0,0, sombreamento)), this.getX() + posicao - Camera.x, this.getY() - Camera.y, null);
	}

	public void sombrasChao(Graphics g, BufferedImage sprite) {
		if (!subindo && !caindo) {
			g.drawImage(ImageUtils.inverterV(ImageUtils.sombreamento(sprite, new Color(0,0,0, sombreamento))), this.getX() + posicao - Camera.x,
					this.getY() - Camera.y + Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE,
					Configuracoes.TILE_SIZE / 2, null);
		} else {
			g.drawImage(ImageUtils.sombreamento(sprite, new Color(0,0,0, sombreamento)), this.getX() + posicao - Camera.x + 10,
					this.getY() - Camera.y + Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE,
					Configuracoes.TILE_SIZE / 2, null);
		}

	}




	public void adicionarMascaras() {
		MascaraHitBox padrao = new MascaraHitBox("padrao", 20, 11, 20 ,52);
//		MascaraHitBox padrao = new MascaraHitBox("padrao", 20, 11, 20 ,52);
		adicionarMascara(padrao);

	}

	public void setPlayer(Player player){
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
	private void setarAnimacao(String movimento){
		ultimaAcao = acaoAtual;
		acaoAtual = caindo ? "caindo" :
					subindo ? "subindo" :
					pousando ? "pousando" :
					andando ? "andando" :
					parando ? "parando" :
					atacando ? "atacando" :
					respirando ? "respirando" :
					respirandoEmCombate ? "respirandoEmCombate" :
					investindo ? "investindo" : "";

		caindo = movimento.equals("caindo");
		subindo = movimento.equals("subindo");
		pousando = movimento.equals("pousando");
		andando = movimento.equals("andando");
		parando = movimento.equals("parando");
		atacando = movimento.equals("atacando");
		respirando = movimento.equals("respirando");
		respirandoEmCombate = movimento.equals("respirandoEmCombate");
		investindo = movimento.equals("investindo");
	}

	public Tai asTai() {
		return (Tai) this;
	}

	public Ace asAce() {
		return (Ace) this;
	}

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
		return this instanceof Ace;
	}

	public boolean isSander() {
		return false;
	}
}
