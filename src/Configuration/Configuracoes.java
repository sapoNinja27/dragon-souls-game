package Configuration;

import enums.TipoAmbiente;
import enums.TipoGame;
import enums.TipoMenu;

public class Configuracoes {
	public static Boolean musica=true;
	public static Boolean efeitos=true;
	public static Boolean idioma=true;
	public static Integer volume=0;
	public static int vida;
	public static int posx;
	public static int posy;
	public static final int WIDTH = 180*4;
	public static final int HEIGHT = 90*4;
	public static final int SCALE = 2;
	public static TipoMenu estadoMenu=TipoMenu.INICIAL;
	public static TipoGame estadoGame=TipoGame.MENU;
//	private int CUR_LEVEL = 1;
	public static boolean dia=false;
	public static int TILE_SIZE=64;
	public static TipoAmbiente local=TipoAmbiente.RUA;
	
}
