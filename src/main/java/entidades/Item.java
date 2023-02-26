package entidades;

import enums.TipoItem;
import lombok.*;

import java.awt.*;
import java.awt.image.BufferedImage;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    private String nome;
    private TipoItem tipoItem;
    private BufferedImage icone;

    public void render(Graphics g, int x, int y) {
        g.fillRect(x + (100 + 9), y + 131 * (100 + 8), 100, 100);
        g.drawImage(icone, x, y, 100, 100, null);
    }

}
