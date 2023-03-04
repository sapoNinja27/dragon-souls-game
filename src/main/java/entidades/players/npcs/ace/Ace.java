//package entidades.players.ace;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//
//import entidades.players.principal.Player;
//import utils.ImageUtils;
//
//import java.util.HashMap;
//import java.util.List;
//
//import configuracoes.Configuracao;
//import graficos.Spritesheet;
//import world.Camera;
//
//import static utils.ImageUtils.inverter;
//
//public class Ace extends Player {
//	public int framesDash = 0,maxFramesDash = 5,indexDash = 19,maxIndexDash = 24;
//	public int framesDashS = 0,maxFramesDashS2 = 15,maxFramesDashS = 4,indexDashS = 20,maxIndexDashS = 23;
//	private int nivelFoco = 0, maxNivelFoco = 4;
//
//	public Ace(int x, int y) {
//		super(x, y);
//	}
//
//	public int getNivelFoco() {
//		return nivelFoco;
//	}
//
//	public int getMaxNivelFoco() {
//		return maxNivelFoco;
//	}
//
//	/*
//	* define os sprites e animações do player
//	* */
//	@Override
//	public void atualizarSprites(){
//		List<BufferedImage> respirando = new ArrayList<>();
//		List<BufferedImage> correndo = new ArrayList<>();
//		List<BufferedImage> pulando = new ArrayList<>();
//		spritesheet = new Spritesheet("/personagens/ace.png");
//		for(int i = 0; i < 35; i++){
//			if(respirando.size() < 4){
//				respirando.add(spritesheet.getSprite(
//						Configuracao.TILE_SIZE * i,
//						0,
//						Configuracao.TILE_SIZE,
//						Configuracao.TILE_SIZE)
//				);
//			}
//			if(correndo.size() < 9){
//				correndo.add(spritesheet.getSprite(
//						Configuracao.TILE_SIZE * i,
//						Configuracao.TILE_SIZE,
//						Configuracao.TILE_SIZE,
//						Configuracao.TILE_SIZE)
//				);
//			}
//			if(pulando.size() < 6){
//				pulando.add(spritesheet.getSprite(
//						Configuracao.TILE_SIZE * i,
//						Configuracao.TILE_SIZE * 2,
//						Configuracao.TILE_SIZE, Configuracao.TILE_SIZE)
//				);
//			}
//		}
//		spritesDireita.addAll(respirando);
//		spritesDireita.addAll(correndo);
//		spritesDireita.addAll(pulando);
//
//		spritesEsquerda.addAll(inverter(spritesDireita));
//		super.atualizarSprites();
//	}
//
//	@Override
//	public String tipoPlayer(){
//		return "Ace";
//	}
//
//	@Override
//	public BufferedImage getIcone(){
//		return icone[1];
//	}
//
//	@Override
//	public HashMap<String, Color> getCoresSet(){
//		HashMap<String, Color> cores = new HashMap<>();
//		cores.put("bordaMenu", new Color(0, 152, 79));
//		cores.put("corBarraVida", new Color(0, 152, 79));
//		cores.put("corBarraVidaVazia", new Color(0, 38, 17));
//		cores.put("corBarraMana", new Color(133, 148, 144));
//		cores.put("corBarraManaVazia", new Color(34, 38, 36));
//
//		return cores;
//	}
//	@Override
//	public void tick() {
//		super.tick();
//		framesDash++;
//		if(framesDash >= 30){
//			nivelFoco ++;
//			framesDash = 0;
//			if(nivelFoco > maxNivelFoco){
//				nivelFoco = 0;
//			}
//		}
//	}
//
//	@Override
//	public void render(Graphics g) {
//		Color color;
//		int opacidade = sombras;
//		if(opacidade < 80){
//			opacidade = 80;
//		}
//		if(nivelFoco > 2){
//			if(nivelFoco == maxNivelFoco){
//				color = (new Color(239, 239, 29, opacidade));
//			} else {
//				color = (new Color(218, 218, 185, opacidade));
//			}
//			g.drawImage(
//					ImageUtils.sombreamento(spriteAtual().get(index), color),
//					this.getX() + posicao - Camera.x - nivelFoco,
//					this.getY() - Camera.y - nivelFoco,
//					Configuracao.TILE_SIZE + (nivelFoco * 2),
//					Configuracao.TILE_SIZE + (nivelFoco * 2),
//					null
//			);
//		}
//		super.render(g);
//	}
//	@Override
//	public void drawHud(Graphics g) {
//		for(int i = 1; i <= getMaxNivelFoco(); i++){
//			g.setColor(Color.BLACK);
//			g.fillRect(79 + (i * 25), 84, 18, 8);
//			if(i == getMaxNivelFoco()){
//				if(getNivelFoco() == i){
//					g.setColor(new Color(239, 239, 29));
//				} else {
//					g.setColor(new Color(162, 162, 17));
//				}
//			} else {
//				if(getNivelFoco() >= i){
//					g.setColor(new Color(218, 218, 185));
//				} else {
//					g.setColor(new Color(122, 122, 101));
//				}
//			}
//			g.fillRect(79 + (i * 25), 84, 16, 6);
//		}
//	}
//}
