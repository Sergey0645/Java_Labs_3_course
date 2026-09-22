package task2;

public class ShapeController {

    private ShapeModel model;
    private ShapeView view;

    public ShapeController(ShapeModel model, ShapeView view) {
        this.model = model;
        this.view = view;
    }

    public void showShapes() {
        view.printTitle("УСІ ФІГУРИ");

        for (Shape shape : model.getShapes()) {
            shape.draw();
        }

        view.printShapes(model.getShapes());
    }

    public void showTotalArea() {
        view.printTotalArea(model.calculateTotalArea());
    }

    public void showRectangleArea() {
        view.printRectangleArea(model.calculateRectangleArea());
    }

    public void showTriangleArea() {
        view.printTriangleArea(model.calculateTriangleArea());
    }

    public void showCircleArea() {
        view.printCircleArea(model.calculateCircleArea());
    }

    public void sortByArea() {
        model.sortByArea();

        view.printTitle("ВІДСОРТОВАНО ЗА ПЛОЩЕЮ");
        view.printShapes(model.getShapes());
    }

    public void sortByColor() {
        model.sortByColor();

        view.printTitle("ВІДСОРТОВАНО ЗА КОЛЬОРОМ");
        view.printShapes(model.getShapes());
    }

    public void saveShapes(String fileName) {
        try {
            FileManager.saveShapes(model.getShapes(), fileName);
            System.out.println("Фігури успішно збережено у файл.");

        } catch (Exception e) {
            System.out.println("Помилка збереження файлу: "
                    + e.getMessage());
        }
    }

    public void loadShapes(String fileName) {
        try {
            Shape[] loadedShapes = FileManager.loadShapes(fileName);
            model.setShapes(loadedShapes);

            System.out.println("Фігури успішно завантажено з файлу.");

        } catch (Exception e) {
            System.out.println("Помилка читання файлу: "
                    + e.getMessage());
        }
    }
}