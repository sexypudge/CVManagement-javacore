package java1.lab7;

public class ITStudent extends Student {

    private double javaScore;
    private double htmlScore;
    private double cssScore;

    public ITStudent(String name, double javaScore, double htmlScore, double cssScore) {
        super(name, "Cong nghe thong tin");
        this.javaScore = javaScore;
        this.htmlScore = htmlScore;
        this.cssScore = cssScore;
    }

    @Override
    public double calculateScore() {
        return (javaScore * 2 + htmlScore + cssScore) / 4;
    }
}
