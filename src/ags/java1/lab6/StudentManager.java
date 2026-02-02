package java1.lab6;

import java.util.Scanner;

public class StudentManager {

    static Scanner input = new Scanner(System.in);

    static boolean isValidEmail(String email) {
        return email.matches("\\w+@\\w+(\\.\\w+)+");
    }

    static boolean isValidPhone(String phone) {
        return phone.matches("0\\d{9}");
    }

    static boolean isValidIdCard(String id) {
        return id.matches("\\d{9}|\\d{12}");
    }

    public static void main(String[] args) {
        System.out.print("Nhap so luong sinh vien: ");
        int n = Integer.parseInt(input.nextLine());

        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nNhap thong tin sinh vien thu " + (i + 1));

            System.out.print("Ho ten: ");
            String name = input.nextLine();

            String email;
            while (true) {
                System.out.print("Email: ");
                email = input.nextLine();
                if (isValidEmail(email)) break;
                System.out.println("Email khong hop le!");
            }

            String phone;
            while (true) {
                System.out.print("So dien thoai: ");
                phone = input.nextLine();
                if (isValidPhone(phone)) break;
                System.out.println("So dien thoai khong hop le!");
            }

            String idCard;
            while (true) {
                System.out.print("CMND/CCCD: ");
                idCard = input.nextLine();
                if (isValidIdCard(idCard)) break;
                System.out.println("CMND khong hop le!");
            }

            students[i] = new Student(name, email, phone, idCard);
        }

        System.out.println("\n DANH SACH SINH VIEN ");
        for (Student s : students) {
            s.showInfo();
            System.out.println("----------------------");
        }
    }
}
