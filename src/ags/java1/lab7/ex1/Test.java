package java1.lab7.ex1;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== HINH CHU NHAT ===");
        System.out.print("Nhap chieu rong: ");
        double w = input.nextDouble();
        System.out.print("Nhap chieu dai: ");
        double h = input.nextDouble();

        Rectangle rect = new Rectangle(w, h);
        rect.printInfo();

        System.out.println("\n=== HINH VUONG ===");
        System.out.print("Nhap canh: ");
        double side = input.nextDouble();

        Square sq = new Square(side);
        sq.printInfo();
    }
}
