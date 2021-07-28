package Main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Entidades.Bosses.Pupkin_Boss;
import Entidades.Npcs.light_npc;
import Entidades.Npcs.roux_npc;
import Entidades.Npcs.soldier_npc;

public class Cutscene {
	int fade, aux;
	int cont=0;
	boolean button;
	boolean flash;
	float op=0f;
	String dialog =" ",dialog2 =" ",dialog3 =" ";
	int avatarL=0,avatarC=0;
	int pisca=0,maxPisca=20;
	boolean boxright, boxleft;
	BufferedImage icon[][]= new BufferedImage[30][10];
	BufferedImage buttonimg;
	public  int right_dir = 0,left_dir = 1;
	private int frames = 0;
	private static boolean[] passo=new boolean[100];
	private static boolean[] cena = new boolean[100];
	private static boolean cutscene=false;
	private static boolean next;
	private static int opc=0;
	private static int maxOpc=20;
	public static void CenaStart(int id) {
		cena[id]=true;	
		cutscene=true;
		passo[0]=true;
	}
	public void esvaziar() {
		next=false;
		boxright=false;
		avatarC=0;
		boxleft=false;
		frames=0;
		dialog="";
		dialog2="";
		dialog3="";
	}
	void fade(boolean go) {
		if(go) {
			fade++;
			if(!passo[aux]) {
				aux++;
				fade(true);
			}else {
				fade=0;
				passo[aux]=false;
				go=false;
				passo[aux+1]=true;
			}
		}
	}
	public  void next() {
		next=true;
	}
	public  void up() {
		opc--;
		if(opc<0) {
			opc=maxOpc;
		}
	}
	public void down() {
		opc++;
		if(opc>maxOpc) {
			opc=0;
		}
	}
	public boolean CcRun() {
		return cutscene;
//		return false;
	}
	public void tick() {
		pisca++;
		if(pisca==maxPisca) {
			if(button) {
				button=false;
			}else {
				button=true;
			}
			
			pisca=0;
		}
		buttonimg=Game.tinyIcons.getSprite(0, 0, 16, 16);
		for(int i=0;i<30;i++) {
			for(int j=0;j<10;j++) {
				icon[i][j]=Game.icones.getSprite(Configuracoes.TILE_SIZE*(i), Configuracoes.TILE_SIZE*j, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
			}
		}
//		testes
//		if(next) {
//			for(int i=0; i<100;i++) {
//				if(passo[i]) {
//					System.out.println(i+" :"+passo[i]);
//				}
//			}
//			next=false;
//			fade(true);
//		}
		if(cena[0]) {
			if(passo[0]) {
//				Game.player.parado=true;
//				Game.player.camx=350;
//				Game.player.mov_das_cena=-70;
				light_npc.dir=left_dir;
				light_npc.setPos(-100,77);
				light_npc.index=4;
				
				Pupkin_Boss.dir=left_dir;
				Pupkin_Boss.parado=true;
				Pupkin_Boss.setPos(0,0);
				
				roux_npc.dir=right_dir;
				roux_npc.setPos(-170,4);
				roux_npc.parado=true;
				
				soldier_npc.dir=left_dir;
				soldier_npc.setPos(-50,0);
				soldier_npc.parado=true;
				if(next) {
					esvaziar();
					Game.player.setX(760);
					fade(true);
				}
				frames++;
				if(frames==2) {
					cont+=5;
					frames=0;
					Game.player.setX(cont);
					if(cont>=760) {
						cont=760;
						fade(true);
					}
				}
			}
			if(passo[1]) {
				boxright=true;
				avatarL=16;
				dialog="Boa novato";
				if(next) {
					esvaziar();
					fade(true);
				}
			}
			if(passo[2]) {
				boxright=true;
				avatarL=16;
				dialog="Agora s� falta a garota";
				if(next) {
					esvaziar();
					fade(true);
				}
			}
			if(passo[3]) {
				frames++;
				if(frames==8) {
					frames=0;
					if(light_npc.index==4) {
						light_npc.index=0;
					}else if(light_npc.index==0) {
						light_npc.index=2;
					}else if(light_npc.index==2) {
						light_npc.index=0;
					}
				}
				boxright=true;
				avatarL=10;
				avatarC=4;
				dialog="Roux, fuja...";
				if(next) {
					esvaziar();
					fade(true);
				}
				
			}
			if(passo[4]) {
				frames++;
				if(frames==8) {
					frames=0;
					if(light_npc.index==4) {
						light_npc.index=0;
					}else if(light_npc.index==0) {
						light_npc.index=2;
					}else if(light_npc.index==2) {
						light_npc.index=0;
					}
				}
				boxright=true;
				avatarL=17;
				dialog="Quieto";
				if(next) {
					esvaziar();
					soldier_npc.index=9;
					fade(true);
				}
			}
			if(passo[5]) {
				soldier_npc.parado=false;
				soldier_npc.setPos(-50-(soldier_npc.index*2),0);
				frames++;
				if(frames==10) {
					frames=0;
					soldier_npc.index++;
					if(soldier_npc.index>=16) {
						soldier_npc.index=17;
						fade(true);
					}
				}
				if(next) {
					esvaziar();
					soldier_npc.setPos(-80,0);
					soldier_npc.index=17;
					fade(true);
				}
			}
			if(passo[6]) {
				soldier_npc.setPos(-80,0);
				frames++;
				if(frames==10) {
					frames=0;
					soldier_npc.index++;
					if(soldier_npc.index>=20) {
						soldier_npc.index=0;
						soldier_npc.parado=true;
						light_npc.index=4;
						fade(true);
					}
				}
				if(next) {
					esvaziar();
					soldier_npc.index=0;
					soldier_npc.parado=true;
					light_npc.index=4;
					fade(true);
				}
			}
			if(passo[7]) {
				boxright=true;
				avatarL=3;
				avatarC=2;
				dialog="Light";
				if(next) {
					esvaziar();
					fade(true);
				}
			}
			if(passo[8]) {
				boxright=true;
				avatarL=3;
				avatarC=3;
				dialog="Eu vou.. Agh";
				if(next) {
					esvaziar();
					soldier_npc.index=4;
					soldier_npc.parado=false;
					fade(true);
				}
			}
			if(passo[9]) {
				roux_npc.index=10;
				roux_npc.parado=false;
				frames++;
				if(frames==10) {
					frames=0;
					soldier_npc.index++;
					soldier_npc.setPos(-80, 0);
					if(soldier_npc.index>=8) {
						soldier_npc.index=7;
						fade(true);
					}
				}
				if(next) {
					esvaziar();
					soldier_npc.index=7;
					fade(true);
				}
			}
			if(passo[10]) {
				boxright=true;
				avatarL=17;
				dialog="N�o vai";
				if(next) {
					esvaziar();
					fade(true);
				}
			}
			if(passo[11]) {
				boxright=true;
				avatarL=16;
				dialog="Ela esta paralizada";
				if(next) {
					esvaziar();
					fade(true);
				}
			}
			if(passo[12]) {
				boxright=true;
				avatarL=16;
				avatarC=1;
				dialog="Mate";
				if(next) {
					esvaziar();
					fade(true);
				}
			}
			if(passo[13]) {
				flash=true;
				frames++;
				if(frames>=3) {
					op+=0.1f;
					if(op>=1f) {
						Game.player.visivel=true;
						Game.player.depth=5;
						op=0f;
						flash=false;
						fade(true);
					}
					if(next) {
						esvaziar();
						Game.player.visivel=true;
						Game.player.depth=5;
						op=0f;
						flash=false;
						fade(true);
					}
				}
				
			}
			if(passo[14]) {
				Game.player.index=28;
				soldier_npc.index=7;
				Game.player.setX(900);
				frames++;
				if(frames>=20) {
					frames=0;
					Game.player.index=29;
					soldier_npc.index=8;
					fade(true);
				}
				if(next) {
					esvaziar();
					Game.player.index=29;
					soldier_npc.index=8;
					fade(true);
				}
			}
			if(passo[15]) {
				boxleft=true;
				avatarL=0;
				avatarC=1;
				dialog="Cuidado onde aponta isso ai";
				dialog2="amig�o";
				if(next) {
					esvaziar();
					cont=0;
					roux_npc.index=4;
					roux_npc.dir=left_dir;
					fade(true);
				}
			}
			if(passo[16]) {
				roux_npc.setPos(-170-cont,4);
				cont+=3;
				frames++;
				if(frames>=6) {
					roux_npc.index++;
					frames=0;
					
					if(cont>=600) {
						cont=0;
						passo[16]=false;
						passo[17]=true;
					}
					if(roux_npc.index>=10) {
						roux_npc.index=4;
					}
				}
				if(next) {
					esvaziar();
					roux_npc.setPos(-170-600,4);
					cont=0;
					passo[16]=false;
					passo[17]=true;
				}
			}
			if(passo[17]) {
				boxright=true;
				avatarL=17;
				avatarC=0;
				dialog="";
				if(next) {
					esvaziar();
					passo[17]=false;
					passo[18]=true;
				}
			}
			if(passo[18]) {
				frames++;
				if(frames>=15) {
					soldier_npc.index--;
					if(soldier_npc.index==7) {
						Game.player.index=28;
					}
					if(next) {
						esvaziar();
						soldier_npc.index=4;	
						soldier_npc.parado=true;
						Game.player.index=24;
						passo[18]=false;
						passo[19]=true;				
					}
					if(soldier_npc.index==4) {
						soldier_npc.parado=true;
						Game.player.index=24;
						passo[18]=false;
						passo[19]=true;
					}
				}
			}
			if(passo[19]) {
				Game.player.depth=6;
				frames++;
				if(frames>=14) {
					frames=0;
					Game.player.index++;
					if(Game.player.index==27) {
						soldier_npc.parado=false;
						passo[19]=false;
						passo[20]=true;	
					}
				}
				if(next) {
					esvaziar();
					Game.player.index=27;
					soldier_npc.parado=false;
					passo[19]=false;
					passo[20]=true;	
				}
			}
			if(passo[20]) {
				boxright=true;
				dialog="Estou pensando";
				if(next) {
					esvaziar();
					passo[20]=false;
					passo[21]=true;
				}
				
			}
			if(passo[21]) {
				boxright=true;
				dialog="Qual era seu plano depois";
				dialog2="que descobrisse que n�o";
				dialog3="pode me machucar?";
				if(next) {
					esvaziar();
					passo[21]=false;
					passo[22]=true;
				}
			}
			if(passo[22]) {
				boxright=true;
				dialog="HAHAHAHAHAHA";
				if(next) {
					esvaziar();
					Game.player.index=25;
					passo[22]=false;
					passo[23]=true;
				}
			}
			if(passo[23]) {
				frames++;
				if(frames>=25) {
					frames=0;
					Game.player.index--;
					if(Game.player.index==24) {
						Game.player.parado=true;
						passo[23]=false;
						passo[24]=true;
					}
				}
				if(next) {
					esvaziar();
					Game.player.parado=true;
					passo[23]=false;
					passo[24]=true;
				}
			}
			if(passo[24]) {
				soldier_npc.parado=true;
				avatarL=0;
				boxleft=true;
				dialog="Eu..";
				if(next) {
					esvaziar();
					Pupkin_Boss.index=4;
					Pupkin_Boss.parado=false;
					passo[24]=false;
					passo[25]=true;
				}
			}
			if(passo[25]) {
				frames++;
				if(frames>=10) {
					frames=0;
					Pupkin_Boss.index++;
					if(Pupkin_Boss.index==8) {
						cont=0;
						passo[25]=false;
						passo[26]=true;
					}
				}
				if(next) {
					esvaziar();
					Pupkin_Boss.index=8;
					cont=0;
					passo[25]=false;
					passo[26]=true;
				}
			}
			if(passo[26]) {
				frames++;
				if(frames>=10) {
					frames=0;
					cont++;
					if(cont==0) {
						Pupkin_Boss.aceso[0]=true;
					}
					if(cont==1) {
						Pupkin_Boss.aceso[1]=true;
					}
					if(cont==2) {
						Pupkin_Boss.aceso[2]=true;
					}
					if(cont==3) {
						Pupkin_Boss.aceso[3]=true;
					}
					if(cont==4) {
						cont=0;
						Game.player.setX(880);
						Game.player.index=32;
						Game.player.parado=false;
						passo[26]=false;
						passo[27]=true;
					}
				}
				if(next) {
					esvaziar();
					cont=0;
					Pupkin_Boss.aceso[3]=true;
					Game.player.setX(880);
					Game.player.index=32;
					Game.player.parado=false;
					passo[26]=false;
					passo[27]=true;
				}
			}
			if(passo[27]) {
				for(int i=0;i<4;i++) {
					Pupkin_Boss.aceso[i]=false;
				}
				frames++;
				if(frames>=20) {
					frames=0;
					Pupkin_Boss.index++;
					if(Pupkin_Boss.index==10) {
						//fuma�a=true;
						passo[27]=false;
						passo[28]=true;
					}
				}
				if(next) {
					esvaziar();
					Pupkin_Boss.index=10;
					//fuma�a=true;
					passo[27]=false;
					passo[28]=true;
				}
			}
			if(passo[28]) {
				boxright=true;
				avatarL=16;
				avatarC=1;
				dialog="Ja estava me irritando";
				if(next) {
					esvaziar();
					passo[28]=false;
					passo[29]=true;
				}
			}
			if(passo[29]) {
				frames++;
				if(frames>=14) {
					frames=0;
					Pupkin_Boss.index++;
					if(Pupkin_Boss.index==12) {
						Pupkin_Boss.parado=true;
						Pupkin_Boss.queimou=true;
						passo[29]=false;
						passo[30]=true;
					}
				}
				if(next) {
					Pupkin_Boss.parado=true;
					Pupkin_Boss.queimou=true;
					passo[29]=false;
					passo[30]=true;
				}
			}
			if(passo[30]) {
				boxright=true;
				avatarL=16;
				avatarC=0;
				dialog="Pare de perder tempo e ";
				dialog2="vai atras da garota";
				if(next) {
					esvaziar();
					soldier_npc.parado=false;
					soldier_npc.index=9;
					passo[30]=false;
					passo[31]=true;
				}
			}
			if(passo[31]) {
				cont++;
				soldier_npc.setPos(-80-(cont),0);
				if(cont>=105) {
					soldier_npc.index=10;
					cont=0;
					passo[31]=false;
					passo[32]=true;
				}
				frames++;
				if(frames>=10) {
					frames=0;
					soldier_npc.index++;
					if(soldier_npc.index==17) {
						soldier_npc.index=9;
					}
				}
				if(next) {
					esvaziar();
					soldier_npc.index=10;
					soldier_npc.setPos(-185,0);
					cont=0;
					passo[31]=false;
					passo[32]=true;
					
				}
			}
			if(passo[32]) {
				soldier_npc.dir=left_dir;
				soldier_npc.setPos(-180,0);
				soldier_npc.parado=true;
				boxright=true;
				avatarL=17;
				dialog="Solta";
				if(next) {
					esvaziar();
					passo[32]=false;
					passo[33]=true;
				}
			}
			if(passo[33]) {
				boxleft=true;
				avatarL=0;
				avatarC=6;
				dialog="N�o";
				if(next) {
					esvaziar();
					soldier_npc.index=17;
					soldier_npc.dir=right_dir;
					soldier_npc.parado=false;
					passo[33]=false;
					passo[34]=true;
				}
			}
			if(passo[34]) {
				boxright=true;
				avatarL=17;
				dialog="MANDEI SOLTAR";
				frames++;
				if(frames>=10) {
					frames=0;
					soldier_npc.index++;
					if(soldier_npc.index==20) {
						soldier_npc.index=18;
					}
				}
				if(next) {
					esvaziar();
					passo[34]=false;
					passo[35]=true;
				}
			}
			if(passo[35]) {
				boxleft=true;
				avatarL=0;
				avatarC=7;
				dialog="";
				frames++;
				if(frames>=10) {
					frames=0;
					soldier_npc.index++;
					if(soldier_npc.index==20) {
						soldier_npc.index=18;
					}
				}
				if(next) {
					esvaziar();
					soldier_npc.parado=true;
					passo[35]=false;
					passo[36]=true;
				}
			}
			if(passo[36]) {
				boxright=true;
				avatarL=17;
				dialog="Morto";
				if(next) {
					esvaziar();
					soldier_npc.dir=left_dir;
					soldier_npc.index=9;
					Game.player.index=99+30;
					soldier_npc.parado=false;
					passo[36]=false;
					passo[37]=true;
				}
			}
			if(passo[37]) {
				cont++;
				soldier_npc.setPos(-180-cont,0);
				frames++;
				if(frames>=10) {
					frames=0;
					soldier_npc.index++;
					if(soldier_npc.index==17) {
						soldier_npc.index=9;
					}
					Game.player.index++;
					if(Game.player.index==99+32) {
						soldier_npc.parado=true;
						passo[37]=false;
						passo[38]=true;
					}
				}
				if(next) {
					esvaziar();
					Game.player.index=99+32;
					soldier_npc.parado=true;
					soldier_npc.setPos(-180-14,0);
					passo[37]=false;
					passo[38]=true;
				}
			}
			if(passo[38]) {
				boxleft=true;
				avatarL=0;
				avatarC=8;
				dialog="AAAAAAAAAAAAAAAAAAAH";
				if(next) {
					esvaziar();
					Game.player.transformado=true;
					Game.player.parado=true;
					passo[38]=false;
					passo[39]=true;
				}
			}
			if(passo[39]) {
				Game.player.transformado=true;
			}


		
			
			
			
			
			
			
			
			
			
		}
	}
	
	
	public void render(Graphics g) {
		int offX=300;
		int offY=280;
		int offX2=608;
		int offY2=280;
		g.setFont(new Font("arial", Font.BOLD, 13));
		Graphics2D g2 = (Graphics2D) g;
		
		if(boxleft) {
			Rectangle borda= new Rectangle(offX-Configuracoes.TILE_SIZE*3,offY, Configuracoes.TILE_SIZE*3, Configuracoes.TILE_SIZE-1);
			g.setColor(Color.white);
			g.fillRect(offX-Configuracoes.TILE_SIZE*4,offY, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(offX-Configuracoes.TILE_SIZE*3,offY, Configuracoes.TILE_SIZE*3, Configuracoes.TILE_SIZE-1);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			g.setColor(Color.black);
			g2.draw(borda);
		}
		if(boxright) {
			Rectangle borda2= new Rectangle(offX2-Configuracoes.TILE_SIZE*3,offY2, Configuracoes.TILE_SIZE*3, Configuracoes.TILE_SIZE-1);
			g.setColor(Color.white);
			g.fillRect(offX2,offY2, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(offX2-Configuracoes.TILE_SIZE*3,offY2, Configuracoes.TILE_SIZE*3, Configuracoes.TILE_SIZE-1);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			g.setColor(Color.black);
			g2.draw(borda2);
		}
		if(boxleft) {
			g.drawString(dialog,offX-15-Configuracoes.TILE_SIZE*3+20,offY+20);
			g.drawString(dialog2,offX-15-Configuracoes.TILE_SIZE*3+20,offY+40);
			g.drawString(dialog3,offX-15-Configuracoes.TILE_SIZE*3+20,offY+60);
			g.drawImage(icon[avatarL][avatarC],offX-Configuracoes.TILE_SIZE*4,offY,(int)(Configuracoes.TILE_SIZE),Configuracoes.TILE_SIZE, null);
			if(button) {
				g.drawImage(buttonimg,offX-20,offY+45,16,16, null);
			}
		}
		if(boxright) {
			g.drawString(dialog,offX2-15-Configuracoes.TILE_SIZE*3+20,offY2+20);
			g.drawString(dialog2,offX2-15-Configuracoes.TILE_SIZE*3+20,offY2+40);
			g.drawString(dialog3,offX2-15-Configuracoes.TILE_SIZE*3+20,offY2+60);
			g.drawImage(icon[avatarL][avatarC],offX2,offY2,(int)(Configuracoes.TILE_SIZE),Configuracoes.TILE_SIZE, null);
			if(button) {
				g.drawImage(buttonimg,offX2-20,offY2+45,16,16, null);
			}
		}
		
		if(flash) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
			g.setColor(Color.white);
			g.fillRect(0, 0, 3000, 30000);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		
		
		
	}
}
