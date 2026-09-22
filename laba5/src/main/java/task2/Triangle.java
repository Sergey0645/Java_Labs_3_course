package task2;

public class Triangle extends Shape {

    private double base;
    private double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return base * height / 2;
    }

    @Override
    public void draw() {
        System.out.println("Трикутник побудовано");
    }

    @Override
    public String toString() {
        return "Трикутник: " +
                "колір = " + shapeColor +
                ", основа = " + base +
                ", висота = " + height +
                ", площа = " + calcArea();
    }
}