package main.utils;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MascaraUtils {

    public static boolean isColiding(double posx, double posy, Polygon polygon) {
        int numeroVertices = polygon.npoints;
        boolean estaDentro = false;

        int i, j = numeroVertices - 1;
        for (i = 0; i < numeroVertices; i++) {
            if ((polygon.ypoints[i] < posy && polygon.ypoints[j] >= posy
                    || polygon.ypoints[j] < posy && polygon.ypoints[i] >= posy)
                    && (polygon.xpoints[i] + (posy - polygon.ypoints[i]) / (polygon.ypoints[j] - polygon.ypoints[i])
                    * (polygon.xpoints[j] - polygon.xpoints[i]) < posx)) {
                estaDentro = !estaDentro;
            }
            j = i;
        }
        return estaDentro;
    }
}
