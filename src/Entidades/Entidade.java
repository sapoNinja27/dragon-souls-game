package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Menu.MascaraHitBox;

public class Entidade {

	protected float sombreamento;
	protected float sombras;
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	protected int depth;
	protected List<MascaraHitBox> mascaras;
	
	public Entidade(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		mascaras = new ArrayList<>();
	}
	public static Comparator<Entidade> nodeSorter = Comparator.comparingInt(n0 -> n0.depth);

	public void adicionarMascara(MascaraHitBox mascara){
		this.mascaras.add(mascara);
	}

	public void correr(double x) {
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
	
	public void tick(){
		
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

	public Rectangle getMascara(){
		return new Rectangle(
				this.getX() + this.mascaras.get(0).getPosicaoX(),
				this.getY()+ this.mascaras.get(0).getPosicaoY(),
				this.mascaras.get(0).getAutura(),
				this.mascaras.get(0).getLargura());
	}

	public boolean corpoColidindo(Entidade entidade){
		Rectangle mascaraAtual = getMascara();
		Rectangle mascaraAlvo = entidade.getMascara();
		return mascaraAtual.intersects(mascaraAlvo);
	}
	
	
	public void render(Graphics g) {

	}
	
}
