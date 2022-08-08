package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import Menu.MascaraHitBox;
import enums.DirecaoPlayer;

public class Entidade {

	protected int sombreamento = 50;
	protected int sombras = 200;
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected boolean colidindo;
	protected int depth;
	protected List<MascaraHitBox> mascaras;
	protected DirecaoPlayer direcaoPlayer = DirecaoPlayer.DIREITA;
	protected boolean parado;
	protected int drawLimitX = 1000;
	protected int drawLimitY = 500;

	public Entidade(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		mascaras = new ArrayList<>();
	}
	public static Comparator<Entidade> nodeSorter = Comparator.comparingInt(n0 -> n0.depth);

	public void limparMascaras(){
		this.mascaras.clear();
	}

	public void adicionarMascara(MascaraHitBox mascara){
		this.mascaras.add(mascara);
	}

	public void mover(double x) {
		this.x = x;
	}

	public double xDouble() {
		return this.x;
	}

	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}


	public boolean isParado() {
		return parado;
	}

	public void setParado(boolean parado) {
		this.parado = parado;
	}

	public DirecaoPlayer getDirecaoPlayer() {
		return direcaoPlayer;
	}

	public void setDirecaoPlayer(DirecaoPlayer direcaoPlayer) {
		this.direcaoPlayer = direcaoPlayer;
	}

	public void teleportarPlayer(Entidade player) {
//		if (player.direcaoPlayer.equals(DirecaoPlayer.DIREITA)) {
//			player.setX(this.getX() - 15 + larg);
//			player.parado = true;
//		}
//		if (playerdirecaoPlayer.equals(DirecaoPlayer.ESQUERDA)) {
//			player.setX(this.getX() + (dist * Configuracoes.TILE_SIZE) - 47 - larg);
//			player.parado = true;
//		}
	}

	public double distanciaX(int destino) {
		return Math.abs(this.x-destino);
	}

	public double distanciaY(int destino) {
		return Math.abs(y-destino);
	}

	public double calculateDistance(int x, int y) {
		return Math.sqrt(this.x-x)*(this.x-x)+(this.y-y)*(this.y-y);
	}

	public boolean dependeControleDePosicaoPlayer(){
		return false;
	}
	public Rectangle getMascara(){
		return new Rectangle(
				this.getX() + this.mascaras.get(0).getPosicaoX(),
				this.getY()+ this.mascaras.get(0).getPosicaoY(),
				this.mascaras.get(0).getAutura(),
				this.mascaras.get(0).getLargura());
	}

	public List<Rectangle> getMascaras(List<MascaraHitBox> mascarasEscolhidas){
		return mascarasEscolhidas.stream().map(mascaraHitBox -> new Rectangle(
				this.getX() + mascarasEscolhidas.get(0).getPosicaoX(),
				this.getY()+ mascarasEscolhidas.get(0).getPosicaoY(),
				mascarasEscolhidas.get(0).getAutura(),
				mascarasEscolhidas.get(0).getLargura())).collect(Collectors.toList());
	}

	public void checarColisao(Entidade player) {
		colidindo = corpoColidindo(player);
	}

	public void checarInteracao(Entidade player){

	}

	public boolean corpoColidindo(Entidade entidade){
		Rectangle mascaraAtual = getMascara();
		Rectangle mascaraAlvo = entidade.getMascara();
		return mascaraAtual.intersects(mascaraAlvo);
	}

	public boolean corpoColidindo(Entidade entidade, List<String> mascaras){
		List<MascaraHitBox> mascarasEscolhidas = entidade.mascaras.stream().filter(s -> {
			for (String mask: mascaras
				 ) {
				if(s.getNome().equals(mask)){
					return true;
				}
			}
			return false;
		}).collect(Collectors.toList());
		Rectangle mascaraAtual = getMascara();
		List<Rectangle> mascarasAlvo = entidade.getMascaras(mascarasEscolhidas);
		return mascarasAlvo.stream().anyMatch(mascaraAtual::intersects);
	}

	public void tick(){

	}

	public void render(Graphics g) {

	}
	
}
