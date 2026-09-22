package task2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ShapeModel model = new ShapeModel();
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(model, view);

        Scanner scanner = new Scanner(System.in);

        int choice = -1;

        while (choice != 0) {

            System.out.println();
            System.out.println("===== МЕНЮ =====");
            System.out.println("1 - Показати всі фігури");
            System.out.println("2 - Показати сумарну площу всіх фігур");
            System.out.println("3 - Показати сумарну площу фігур заданого виду");
            System.out.println("4 - Відсортувати фігури за площею");
            System.out.println("5 - Відсортувати фігури за кольором");
            System.out.println("6 - Зберегти фігури у файл");
            System.out.println("7 - Завантажити фігури з файлу");
            System.out.println("0 - Вихід");
            System.out.print("Ваш вибір: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        controller.showShapes();
                        break;

                    case 2:
                        controller.showTotalArea();
                        break;

                    case 3:
                        System.out.println();
                        System.out.println("Оберіть вид фігури:");
                        System.out.println("1 - Прямокутник");
                        System.out.println("2 - Трикутник");
                        System.out.println("3 - Коло");
                        System.out.print("Ваш вибір: ");

                        try {
                            int shapeChoice =
                                    Integer.parseInt(scanner.nextLine());

                            switch (shapeChoice) {
                                case 1:
                                    controller.showRectangleArea();
                                    break;

                                case 2:
                                    controller.showTriangleArea();
                                    break;

                                case 3:
                                    controller.showCircleArea();
                                    break;

                                default:
                                    System.out.println(
                                            "Невірний вибір виду фігури.");
                            }

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Помилка: потрібно ввести число.");
                        }

                        break;

                    case 4:
                        controller.sortByArea();
                        break;

                    case 5:
                        controller.sortByColor();
                        break;

                    case 6:
                        System.out.print(
                                "Введіть шлях та ім'я файлу для збереження: ");

                        String saveFileName = scanner.nextLine();

                        controller.saveShapes(saveFileName);
                        break;

                    case 7:
                        System.out.print(
                                "Введіть шлях та ім'я файлу для завантаження: ");

                        String loadFileName = scanner.nextLine();

                        controller.loadShapes(loadFileName);
                        break;

                    case 0:
                        System.out.println("Програму завершено.");
                        break;

                    default:
                        System.out.println(
                                "Невірний вибір. Спробуйте ще раз.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Помилка: потрібно ввести число.");
            }
        }

        scanner.close();
    }
}