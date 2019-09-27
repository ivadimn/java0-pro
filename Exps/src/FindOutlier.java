import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FindOutlier {
    static int find(int[] integers) {
        if (Arrays.stream(integers).filter(i -> i % 2 != 0).count() == 1) {
            return Arrays.stream(integers).filter(i -> i % 2 != 0).findAny().getAsInt();
        }
        else {
            return Arrays.stream(integers).filter(i -> i % 2 == 0).findAny().getAsInt();
        }
    }

    public static int rowSumOddNumbers(int n) {
        int start = 1 + n * (n - 1);
        int summa = 0;
        for (int i = 0; i < n; i++) {
            summa += start + (2 * i);
        }
        return summa;
    }
}
