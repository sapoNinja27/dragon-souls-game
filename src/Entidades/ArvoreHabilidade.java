package Entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Graficos.Spritesheet;
import Main.Game;
import World.Camera;

public class ArvoreHabilidade{
	private BufferedImage sprite[];
	private Spritesheet chama;
	public ArvoreHabilidade() {
		sprite= new BufferedImage[24];
		chama=new Spritesheet("/arvoreDesmontada.png");
		sprite[0]=chama.getSprite(0,0,265,265);
		for(int i = 0;i<3;i++) {
			sprite[i+1]=chama.getSprite(265*i,265,265,265);
		}
		for(int i = 0;i<8;i++) {
			sprite[i+4]=chama.getSprite(265*i,265*2,265,265);
		}
		for(int i = 0;i<11;i++) {
			sprite[i+12]=chama.getSprite(265*i,265*3,265,265);
		}
	}
	public void tick(){
		
	}
	public void render(Graphics g) {
		g.drawImage(sprite[0],120,250,265/2,265/2,null);

		g.drawImage(sprite[1],114,161,265/2,265/2,null);
		g.drawImage(sprite[2],178,198,265/2,265/2,null);
		g.drawImage(sprite[3],208,232,265/2,265/2,null);
		

		g.drawImage(sprite[4],99,88,265/2,265/2,null);
		g.drawImage(sprite[5],119,103,265/2,265/2,null);
		g.drawImage(sprite[6],162,108,265/2,265/2,null);
		g.drawImage(sprite[7],200,139,265/2,265/2,null);
		g.drawImage(sprite[8],234,162,265/2,265/2,null);
		g.drawImage(sprite[9],262,189,265/2,265/2,null);
		g.drawImage(sprite[10],277,219,265/2,265/2,null);
		g.drawImage(sprite[11],290,229,265/2,265/2,null);
		

		g.drawImage(sprite[12],107,27,265/2,265/2,null);
		g.drawImage(sprite[13],129,31,265/2,265/2,null);
		g.drawImage(sprite[14],161,53,265/2,265/2,null);
		g.drawImage(sprite[15],200,68,265/2,265/2,null);
		g.drawImage(sprite[16],226,85,265/2,265/2,null);
		g.drawImage(sprite[17],253,103,265/2,265/2,null);
		g.drawImage(sprite[18],279,123,265/2,265/2,null);
		g.drawImage(sprite[19],317,152,265/2,265/2,null);
		g.drawImage(sprite[20],327,187,265/2,265/2,null);
		g.drawImage(sprite[21],348,201,265/2,265/2,null);
		g.drawImage(sprite[22],352,222,265/2,265/2,null);
		
		
		g.setColor(Color.red);
		g.setFont(new Font("arial",Font.BOLD,10));
		g.drawString("6", 132, 347);
	}
}
