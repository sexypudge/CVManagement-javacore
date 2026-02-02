package java1.lab6;

public class Product {

    private String productName;
    private double price;
    private String brand;

    public Product() {
    }

    public Product(String productName, double price, String brand) {
        this.productName = productName;
        this.price = price;
        this.brand = brand;
    }

    public void display() {
        System.out.println("Ten san pham: " + productName);
        System.out.println("Gia: " + price);
        System.out.println("Hang: " + brand);
    }
}
