package java1.lab8;

import java.util.ArrayList;
import java.util.List;

public class NumberUtils {

    public static double sum(List<Double> numbers) {
        double total = 0;
        for (Double n : numbers) {
            total += n;
        }
        return total;
    }

    public static double min(List<Double> numbers) {
        double smallest = numbers.get(0);
        for (Double n : numbers) {
            if (n < smallest) {
                smallest = n;
            }
        }
        return smallest;
    }

    public static double max(List<Double> numbers) {
        double largest = numbers.get(0);
        for (Double n : numbers) {
            if (n > largest) {
                largest = n;
            }
        }
        return largest;
    }
}
