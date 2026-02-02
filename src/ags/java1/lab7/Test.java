package java1.lab7;

public class Test {

    public static void main(String[] args) {

        Student sv1 = new ITStudent("Nguyen Van A", 8, 7.5, 7);
        Student sv2 = new BusinessStudent("Tran Thi B", 7, 8.5);

        sv1.display();
        System.out.println("---------------------");
        sv2.display();
    }
}
