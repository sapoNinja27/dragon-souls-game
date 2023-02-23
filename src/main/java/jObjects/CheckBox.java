//package jObjects;
//
//import java.awt.AlphaComposite;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//
///**
// * Caixa De Checagem
// *
// * @apiNote : cria um pequeno bot�o selecionavel, que muda de estado ao clique
// * @apiNote Configura��es do objeto s�o feitas no init() da pagina
// */
//public class CheckBox {
//	private int x, y, mx, my;
//	private boolean mouseOver, mousePressed, clicouDentro;
//	private boolean clicou;
//
//	/**
//	 * Cria a caixa de checagem
//	 *
//	 * @param x : posi��o horizontal
//	 * @param y : posi��o vertical
//	 */
//	public CheckBox(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//
//	/**
//	 * Checa se esta marcada ou n�o
//	 */
//	public boolean checked() {
//		return clicou;
//	}
//
//	/**
//	 * Fun��es tick s�o chamadas indefinidamente durante a aplica��o atualiza a
//	 * posi��o do mouse em rela��o ao objeto
//	 */
//	public void tick() {
//		mx = game.menu.getMouseX();
//		my = game.menu.getMouseY();
//		if (game.menu.pressionou()) {
//			if (mx > x - 12 && mx < x + 10 && my > y - 9 && my < y + 10) {
//				clicouDentro = true;
//			}
//		}
//		if (game.menu.soltou()) {
//			// soltou
//			if (mx > x - 12 && mx < x + 10 && my > y - 9 && my < y + 10) {
//				// dentro
//				if (clicouDentro) {
//					clicouDentro = false;
//					mouseOver = true;
//					clicou = !clicou;
//				}
//			} else {
//				// fora
//				mouseOver = false;
//
//			}
//		} else {
//			// so movendo
//			if (mx > x - 12 && mx < x + 10 && my > y - 19 && my < y + 10) {
//				// mouse em cima
//				mouseOver = true;
//			} else {
//				// fora
//				mouseOver = false;
//			}
//		}
//	}
//
//	/**
//	 * Renderiza o objeto
//	 *
//	 * @param g : tipo grafico do java instanciado no main
//	 */
//	public void render(Graphics g) {
//		tick();
//		int borda = 1;
//		Graphics2D g2 = (Graphics2D) g;
//		g.setColor(Color.black);
//		g.fillRoundRect(x - borda, y - borda, 10 + borda * 2, 10 + borda * 2, 2, 50);
//		g.setColor(new Color(150, 150, 180));
//		g.fillRoundRect(x, y, 10, 10, 2, 50);
//		g.setColor(Color.black);
//		if (mousePressed) {
//			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
//		} else if (mouseOver) {
//			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
//		} else {
//			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
//		}
//		g.fillRoundRect(x, y, 10, 10, 2, 50);
//		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
//		if (clicou) {
//			g.setFont(new Font("arial", Font.BOLD, 13));
//			g.drawString("x", x + 1, y + 9);
//		}
//	}
//}
