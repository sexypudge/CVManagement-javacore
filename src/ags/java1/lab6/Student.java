package java1.lab6;

public class Student {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String idCard;

    public Student(String fullName, String email, String phoneNumber, String idCard) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
    }

    public void showInfo() {
        System.out.println("Ho ten: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("So dien thoai: " + phoneNumber);
        System.out.println("CMND: " + idCard);
    }
}
