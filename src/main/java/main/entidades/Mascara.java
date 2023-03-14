package main.entidades;

import main.enums.TipoMascara;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mascara {

    private int x;
    private int y;
    private int height;
    private int width;
    private String alias;

}
