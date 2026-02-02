package java1.lab7;

public abstract class Student {

    protected String name;
    protected String major;

    public Student(String name, String major) {
        this.name = name;
        this.major = major;
    }

    public abstract double calculateScore();

    public String getRank() {
        double score = calculateScore();

        if (score < 5) return "Yeu";
        if (score < 6.5) return "Trung binh";
        if (score < 7.5) return "Kha";
        if (score < 9) return "Gioi";
        return "Xuat sac";
    }

    public void display() {
        System.out.println("Ho ten: " + name);
        System.out.println("Nganh: " + major);
        System.out.println("Diem: " + calculateScore());
        System.out.println("Hoc luc: " + getRank());
    }
}
