package java1.lab8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberTest {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Double> values = new ArrayList<>();

        System.out.println("Nhap cac so (nhap -1 de dung):");

        while (true) {
            double number = input.nextDouble();
            if (number == -1) break;
            values.add(number);
        }

        System.out.println("Tong: " + NumberUtils.sum(values));
        System.out.println("Gia tri nho nhat: " + NumberUtils.min(values));
        System.out.println("Gia tri lon nhat: " + NumberUtils.max(values));
    }
}
