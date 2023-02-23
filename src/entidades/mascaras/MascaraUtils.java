package entidades.mascaras;

import entidades.Entidade;
import javafx.scene.shape.TriangleMesh;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MascaraUtils {

    public static boolean isColliding(Polygon polygon, Rectangle rectangle){
        List<Rectangle> converted = getSquareShape(polygon);
        return converted.stream().anyMatch(r -> r.intersects(rectangle));
    }
    private static List<Rectangle> getSquareShape(Shape shape){
        if(shape instanceof Rectangle){
            return Collections.singletonList((Rectangle) shape);
        }
        if(shape instanceof Polygon){
            Polygon polygon = (Polygon) shape;
            return Arrays.asList(
                    new Rectangle(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[3] - polygon.xpoints[0], polygon.ypoints[4] - polygon.ypoints[0]),
                    new Rectangle(polygon.xpoints[1], polygon.ypoints[1], polygon.xpoints[2] - polygon.xpoints[1], polygon.ypoints[5] - polygon.ypoints[1])
            );
        }
        return Collections.emptyList();
    }
}
