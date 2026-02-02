package java1.lab7;

public class BusinessStudent extends Student {

    private double marketingScore;
    private double salesScore;

    public BusinessStudent(String name, double marketingScore, double salesScore) {
        super(name, "Quan tri kinh doanh");
        this.marketingScore = marketingScore;
        this.salesScore = salesScore;
    }

    @Override
    public double calculateScore() {
        return (marketingScore * 2 + salesScore) / 3;
    }
}
