import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] digs  = {1, 3, 5, 7, 9, 11, 23, 71, 13, 6};
        int[] digs1  = {1, 3, 5, 7, 9, 11, 23,  13,  8};
        int[] digs2  = {0, 2, 4, 6, 8, 10, 34, 76, 18, 33};
        int[] digs4  = {Integer.MAX_VALUE, 0, 1};
        System.out.println(FindOutlier.find(digs));
        System.out.println(FindOutlier.find(digs1));
        System.out.println(FindOutlier.find(digs2));
        System.out.println(FindOutlier.rowSumOddNumbers(42));


    }
}
