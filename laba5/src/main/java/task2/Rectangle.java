package task2;

public class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Прямокутник побудовано");
    }

    @Override
    public String toString() {
        return "Прямокутник: " +
                "колір = " + shapeColor +
                ", ширина = " + width +
                ", висота = " + height +
                ", площа = " + calcArea();
    }
}