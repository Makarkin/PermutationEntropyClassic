import Methods.ReadWrite;
import Methods.ValueAndIndex.ValueAndIndex;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReadWrite readWrite = new ReadWrite();

        System.out.println("Введите путь до файла с исходными данными");
        String filePath = sc.nextLine();

        System.out.println("Введите путь до файла с результирующими данными с локальной энтропией");
        String resFilePath1 = sc.nextLine();

        System.out.println("Введите путь до файла с результирующими данными с взвешенной локальной энтропией");
        String resFilePath2 = sc.nextLine();

        System.out.println("Введите вложенную размерность");
        int dimension = Integer.valueOf(sc.nextLine());

        try {
            readWrite.calculateAll(dimension, filePath, resFilePath1, resFilePath2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
