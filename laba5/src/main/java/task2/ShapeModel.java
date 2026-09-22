package task2;

import java.util.Arrays;

public class ShapeModel {

    private Shape[] shapes;

    public ShapeModel() {
        shapes = new Shape[] {
                new Rectangle("Red", 5, 4),
                new Circle("Blue", 3),
                new Triangle("Green", 6, 4),
                new Rectangle("Yellow", 7, 2),
                new Circle("Red", 5),
                new Triangle("Blue", 8, 3),
                new Rectangle("White", 4, 6),
                new Circle("Black", 2),
                new Triangle("Yellow", 10, 5),
                new Rectangle("Green", 3, 3),
                new Circle("White", 4),
                new Triangle("Red", 5, 7)
        };
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }

    public double calculateTotalArea() {
        double totalArea = 0;

        for (Shape shape : shapes) {
            totalArea += shape.calcArea();
        }

        return totalArea;
    }

    public double calculateRectangleArea() {
        double totalArea = 0;

        for (Shape shape : shapes) {
            if (shape instanceof Rectangle) {
                totalArea += shape.calcArea();
            }
        }

        return totalArea;
    }

    public double calculateTriangleArea() {
        double totalArea = 0;

        for (Shape shape : shapes) {
            if (shape instanceof Triangle) {
                totalArea += shape.calcArea();
            }
        }

        return totalArea;
    }

    public double calculateCircleArea() {
        double totalArea = 0;

        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                totalArea += shape.calcArea();
            }
        }

        return totalArea;
    }

    public void sortByArea() {
        Arrays.sort(shapes, new ShapeAreaComparator());
    }

    public void sortByColor() {
        Arrays.sort(shapes, new ShapeColorComparator());
    }
}