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

    private static List<Rectangle> getSquareShape(Shape shape) {
        if (shape instanceof Rectangle) {
            return Collections.singletonList((Rectangle) shape);
        }
        if (shape instanceof Polygon) {
            Polygon polygon = (Polygon) shape;
            return Arrays.asList(
                    new Rectangle(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[3] - polygon.xpoints[0], polygon.ypoints[4] - polygon.ypoints[0]),
                    new Rectangle(polygon.xpoints[1], polygon.ypoints[1], polygon.xpoints[2] - polygon.xpoints[1], polygon.ypoints[5] - polygon.ypoints[1])
            );
        }
        return Collections.emptyList();
    }
}
