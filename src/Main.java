import Methods.ReadWrite;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReadWrite readWrite = new ReadWrite();
        System.out.println("Введите путь до файла с исходными данными");
        String filePath = sc.nextLine();
        System.out.println("Введите вложенную размерность");
        int dimension = Integer.valueOf(sc.nextLine());
        HashMap<Double, Integer> map = readWrite.read(filePath, dimension);
        //System.out.println("Введите путь до файла с результирующими данными");
        //String resFilePath = sc.nextLine();
        //readWrite.writePatternCount(resFilePath);
    }
}
