package task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {

    public static void saveShapes(Shape[] shapes, String fileName)
            throws IOException {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(
                             new FileOutputStream(fileName))) {

            outputStream.writeObject(shapes);
        }
    }

    public static Shape[] loadShapes(String fileName)
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(fileName))) {

            return (Shape[]) inputStream.readObject();
        }
    }
}