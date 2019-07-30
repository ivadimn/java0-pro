import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.#");
        double[] temperature = new double[30];
        for (int i = 0; i < temperature.length; i++) {
            temperature[i] = 32.0 + Math.random() * 8;

        }
        printArray(temperature);
        System.out.println("Средняя температура по больнице - " + average(temperature));
        System.out.println("Количество здоровых пациентов - " + getCountHealthy(temperature));
    }

    public static double average(double[] array) {
        double av = 0;
        for (int i = 0; i < array.length; i++) {
            av += array[i];
        }
        return av / array.length;
    }

    public static int getCountHealthy(double[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 36.2 && array[i] <= 36.9) {
                count++;
            }
        }
        return count;
    }

    public static void printArray(double[] array ) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%.1f ", array[i]);
        }
        System.out.println();
    }
}
