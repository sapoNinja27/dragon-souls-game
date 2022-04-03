package Menu;

import java.awt.*;
import java.io.*;

public class GerenciadorSave {



    public void applySave(String str) {
        String[] spl = str.split("/");
        for (int i = 0; i < spl.length; i++) {
//			String[] spl2 = spl[i].split(":");
//			switch (spl2[0]) {
//			case "level":
//				World.restartGame("level" + spl2[1] + ".png");
//				Configuracoes.estadoGame = TipoGame.NORMAL;
//				Configuracoes.estadoMenu = TipoMenu.HABILIDADES;
//				break;
//			}
        }
    }

    public String loadGame(int encode) {
        String line = "";
        File file = new File("save.txt");
        if (file.exists()) {
            try {
                String singleLine = null;
                BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
                try {
                    while ((singleLine = reader.readLine()) != null) {
                        String[] trans = singleLine.split(":");
                        char[] val = trans[1].toCharArray();
                        trans[1] = "";
                        for (int i = 0; i < val.length; i++) {
                            val[i] -= encode;
                            trans[1] += val[i];
                        }
                        line += trans[0];
                        line += ":";
                        line += trans[1];
                        line += "/";
                    }
                } catch (IOException e) {

                }
            } catch (FileNotFoundException e) {

            }
        }
        return line;
    }

    public void saveGame(String[] val1, int[] val2, int encode) {
        BufferedWriter write = null;
        try {
            write = new BufferedWriter(new FileWriter("save.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < val1.length; i++) {
            String current = val1[i];
            current += ":";
            char[] value = Integer.toString(val2[i]).toCharArray();
            for (int n = 0; n < value.length; n++) {
                value[n] += encode;
                current += value[n];
            }
            try {
                write.write(current);
                if (i < val1.length - 1) {
                    write.newLine();
                }
            } catch (IOException e) {

            }
            try {
                write.flush();
                write.close();
            } catch (IOException e) {

            }
        }
    }
}
