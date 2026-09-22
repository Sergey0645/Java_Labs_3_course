package task2;

public class ShapeView {

    public void printShapes(Shape[] shapes) {
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void printTotalArea(double area) {
        System.out.println("Сумарна площа всіх фігур: " + area);
    }

    public void printRectangleArea(double area) {
        System.out.println("Сумарна площа прямокутників: " + area);
    }

    public void printTriangleArea(double area) {
        System.out.println("Сумарна площа трикутників: " + area);
    }

    public void printCircleArea(double area) {
        System.out.println("Сумарна площа кіл: " + area);
    }

    public void printTitle(String title) {
        System.out.println();
        System.out.println("===== " + title + " =====");
    }
}