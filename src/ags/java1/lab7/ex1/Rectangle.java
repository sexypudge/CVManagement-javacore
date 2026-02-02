package java1.lab7.ex1;

public class Rectangle {

    protected double width;
    protected double height;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getPerimeter() {
        return (width + height) * 2;
    }

    public double getArea() {
        return width * height;
    }

    public void printInfo() {
        System.out.println("Chieu rong: " + width);
        System.out.println("Chieu dai: " + height);
        System.out.println("Chu vi: " + getPerimeter());
        System.out.println("Dien tich: " + getArea());
    }
}
