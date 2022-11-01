package jObjects;

import jObjects.Mouse.Mouse;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Optional;

/**
 * Botão
 * 
 * @apiNote Cria um botão customizavel mais simples de modificar
 * @apiNote Configurações do objeto assim como sua inclusão na lista do main são
 *          feitas no init() da pagina
 */
public class Botao {
	private int x, y, w, h, aw, ah;
	private Color cor = Color.white;
	private String text;
	private boolean mouseOver, mousePressed = false;
	private boolean overPressed;
	private int borda;
	private int spacingX, spacingY;
	private boolean clicked;
	private int mx, my;
	private boolean lastBotao = false;

	/**
	 * Cria um botão
	 * 
	 * @param x      : posição horizontal
	 * @param y      : posição vertical
	 * @param w  : tamanho horizontal
	 * @param h : tamanho vertical
	 */
	public Botao(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	/**
	 * Cria um botão mais elaborado
	 * 
	 * @param x      : posição horizontal
	 * @param y      : posição vertical
	 * @param w  : tamanho horizontal
	 * @param h : tamanho vertical
	 * @param text   : texto do botão
	 * @param cor    : cor do botão
	 * @param borda  : tamanho da borda
	 */
	public Botao(int x, int y, int w, int h, String text, Color cor, int borda, int px, int py, int aw, int ah) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.text = text;
		this.cor = cor;
		this.borda = borda;
		this.spacingX = px;
		this.spacingY = py;
		this.aw = aw;
		this.ah = ah;
	}

	/**
	 * Ajusta quão redondo é o botão, o padrão é 0
	 * 
	 * @param w : Valor de "rounded" horizontal
	 * @param h : Valor de "rounded" vertical
	 */
	public void setRound(int w, int h) {
		this.aw = w;
		this.ah = h;
	}

	/**
	 * Adiciona o texto do botão
	 * 
	 * @param text : texto
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Adiciona a cor do botão, o padrão é branco
	 * 
	 * @param cor : cor
	 */
	public void setCor(Color cor) {
		this.cor = cor;
	}

	/**
	 * Aumenta a grossura da borda
	 * 
	 * @param borda : tamanho da borda
	 */
	public void setBorda(int borda) {
		this.borda = borda;
	}

	public void setSpacing(int x, int y) {
		this.spacingX = x;
		this.spacingY = y;
	}

	/**
	 * Retorna verdadeiro quando o botão é pressionado
	 */

	public boolean isClicked() {
		if (clicked) {
			clicked = false;
			return true;
		}
		return false;
	}

	/**
	 * Retorna verdadeiro quando o mouse esta acima do botão
	 */
	public boolean mouseOver() {
		return mouseOver;
	}

	/**
	 * Funções tick são chamadas indefinidamente durante a aplicação atualiza a
	 * posição do mouse em relação ao objeto
	 */
	public void tick() {
		mx = Mouse.getX();
		my = Mouse.getY();
		if (mouseOver) {
			Mouse.over = true;
		} else {
			if (lastBotao) {
				Mouse.over = false;
				lastBotao = false;
			}
		}
		if (Mouse.pressed) {
			if (mx > x && mx < x + w && my > y && my < y + h) {
				overPressed = true;
				mousePressed = true;
				lastBotao = true;
			} else {
				overPressed = false;
				mouseOver = false;
			}
		} else if (Mouse.released) {
			mousePressed = false;
			if (isHover(mx, my)) {
				if (overPressed) {
					Mouse.released = false;
					overPressed = false;
					Mouse.over = false;
					clicked = true;
				}
				mouseOver = true;
			} else {
				if (overPressed) {
					Mouse.released = false;
					overPressed = false;
				}
				mouseOver = false;
			}
		} else {
			if (isHover(mx, my)) {
				mouseOver = true;
				lastBotao = true;
			} else {
				mouseOver = false;
			}
		}
	}

	private boolean isHover(int mx, int my){
		return mx > x && mx < x + w && my > y && my < y + h;
	}
	/**
	 * Renderiza o objeto
	 * 
	 * @param g : tipo grafico do java instanciado no main
	 */
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);
		g.fillRoundRect(x - borda, y - borda, w + borda * 2, h + borda * 2, aw, ah);
		g.setColor(cor);
		g.fillRoundRect(x, y, w, h, aw, ah);
		g.setColor(Color.black);
		if (mousePressed) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		} else if (mouseOver) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		} else {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
		}
		g.fillRoundRect(x - borda, y - borda, w + borda * 2, h + borda * 2, aw, ah);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.BOLD, 18));
		g.drawString(Optional.ofNullable(text).orElse("Vazio"), (x + spacingX), y + spacingY);

	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
