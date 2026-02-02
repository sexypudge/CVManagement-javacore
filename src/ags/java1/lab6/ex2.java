package java1.lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class ex2 {

    private ArrayList<Product> productList = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void inputProducts() {
        while (true) {
            System.out.print("Nhap ten san pham: ");
            String name = input.nextLine();

            System.out.print("Nhap gia san pham: ");
            double price = Double.parseDouble(input.nextLine());

            System.out.print("Nhap hang san pham: ");
            String brand = input.nextLine();

            productList.add(new Product(name, price, brand));

            System.out.print("Ban co muon nhap tiep khong? (y/n): ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    public void displayProducts() {
        for (Product p : productList) {
            p.display();
            System.out.println("---------------------");
        }
    }

    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        manager.inputProducts();
        manager.displayProducts();
    }
}
