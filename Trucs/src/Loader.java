import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {
    private static final int TRUC_CAPACITY = 12;
    private static final int CONTAINER_CAPACITY = 27;

    public static void main(String[] args) throws IOException {
        int countBox;
        int remainBox;
        int countContainer;
        int remainContainer;
        int countTrucs;
        System.out.println("Введите количество ящиков: ");

        countBox = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine().trim());

        countContainer = countBox / CONTAINER_CAPACITY;
        remainBox = countBox % CONTAINER_CAPACITY;
        if ( remainBox > 0) {
            countContainer++;
        }
        else {
            remainBox = CONTAINER_CAPACITY;
        }
        countTrucs = countContainer / TRUC_CAPACITY;
        remainContainer = countContainer % TRUC_CAPACITY;
        if (remainContainer > 0) {
            countTrucs++;
        }
        else {
            remainContainer = TRUC_CAPACITY;
        }

        System.out.println("Потребуется " + countContainer + " контейнеров");
        System.out.println("Потребуется " + countTrucs + " грузовиков");

        int counterBox = 1;
        int counterContainer = 1;

        for (int i = 1; i <= countTrucs; i++) {
            System.out.println("Грузовик " + i);
            for (int j = 1; j <= (i == countTrucs ? remainContainer : TRUC_CAPACITY); j++) {
                System.out.println("\tКонтайнер " + counterContainer);

                for (int k = 1; k <= (counterContainer == countContainer ? remainBox : CONTAINER_CAPACITY ); k++) {
                    System.out.println("\t\tЯщик " + counterBox++);
                }
                counterContainer++;
            }
        }


    }
}
