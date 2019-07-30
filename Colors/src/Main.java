public class Main {

    static String text = "Каждый охотник желает знать, где сидит фазан";

    public static void main(String[] args) {
        String colors[]  = text.split(",?\\s+");
        printArray(colors);
        System.out.println("--------------------------------");
        invertArray(colors);
        printArray(colors);
    }

    public static void printArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void invertArray(String[] array) {
        String tmp;
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            tmp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = tmp;
        }
    }
}
