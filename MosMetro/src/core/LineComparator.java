package core;

import java.util.Comparator;

public class LineComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        double num1 = getDoubleNumber(o1);
        double num2 = getDoubleNumber(o2);
        return Double.compare(num1, num2);
    }

    private double getDoubleNumber(String n) {
        String clear = n.replaceAll("\\D", "");
        return n.length() == clear.length() ? Double.valueOf(clear) : Double.valueOf(clear) + 0.5;
    }
}
