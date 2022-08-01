package Configuration;

import Entidades.Players.Player;
import enums.TipoAmbiente;
import enums.TipoGame;
import enums.TipoMenu;

public class Configuracoes {
	public static Boolean musica = true;
	public static Boolean efeitos = true;
	public static Boolean idioma = true;
	public static Integer volume = 0;
	public static int vida;
	public static int posx;
	public static int posy;
	public static final int WIDTH = 180 * 4;
	public static final int HEIGHT = 90 * 4;
	public static final int SCALE = 2;
	public static TipoMenu estadoMenu = TipoMenu.INICIAL;
	public static TipoGame estadoGame = TipoGame.MENU;
	public static boolean dia = true;
	public static int TILE_SIZE = 64;
	public static TipoAmbiente local = TipoAmbiente.RUA;
	private static int rota = 1;
	public static Player p1;
	public static Player p2;
	public static int rotear() {
		rota++;
		if (rota > 3) {
			rota = 1;
		}
		return rota;

	}
	public static String nextRota() {
		String caminho="";
		if(rota==1) {
			caminho="Industrial";
		}
		if(rota==2) {
			caminho="Domiciliar";
		}
		if(rota==3) {
			caminho="Hospitalar";
		}
		return caminho;

	}
}
