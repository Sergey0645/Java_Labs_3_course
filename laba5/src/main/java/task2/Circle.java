package task2;

public class Circle extends Shape {

    private double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Коло побудовано");
    }

    @Override
    public String toString() {
        return "Коло: " +
                "колір = " + shapeColor +
                ", радіус = " + radius +
                ", площа = " + calcArea();
    }
}