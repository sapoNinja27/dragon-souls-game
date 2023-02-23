package utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static void write(Graphics g, String descricao, int font, int x, int y){
        List<String> descricoes = new ArrayList<>();
        StringBuilder frase = new StringBuilder();
        for (int letra = 0; letra < descricao.length(); letra++){
            boolean last = false;
            frase.append(descricao.substring(letra, letra + 1));
            if(descricao.length() == letra + 1){
                last = true;
            }
            boolean podeEscrever = frase.length() >= 27 && Character.isSpaceChar(descricao.charAt(letra + 1)) || last;

            if (podeEscrever){
                descricoes.add(frase.toString());
                frase = new StringBuilder();
            }
        }
        for (int i = 0; i < descricoes.size(); i++) {
            ImageUtils.draw(g, descricoes.get(i), x, y + 50 + (i * font + font/2), Color.WHITE, font);
        }
    }
}
