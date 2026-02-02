package java1.lab6;

import java.util.Scanner;

public class NameProcessor {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhap ho ten: ");
        String fullName = input.nextLine().trim();

        int firstSpacePos = fullName.indexOf(" ");
        int lastSpacePos = fullName.lastIndexOf(" ");

        String lastName = fullName.substring(0, firstSpacePos).toUpperCase();
        String middleName = fullName.substring(firstSpacePos + 1, lastSpacePos);
        String firstName = fullName.substring(lastSpacePos + 1).toUpperCase();

        System.out.println("Ho: " + lastName);
        System.out.println("Ten dem: " + middleName);
        System.out.println("Ten: " + firstName);
    }
}
