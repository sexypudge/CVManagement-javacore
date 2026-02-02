package java1.lab7.ex1;

public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    @Override
    public void printInfo() {
        System.out.println("Canh hinh vuong: " + width);
        System.out.println("Chu vi: " + getPerimeter());
        System.out.println("Dien tich: " + getArea());
    }
}
