package task2;

import java.util.Comparator;

public class ShapeColorComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape shape1, Shape shape2) {
        return shape1.getShapeColor()
                .compareToIgnoreCase(shape2.getShapeColor());
    }
}