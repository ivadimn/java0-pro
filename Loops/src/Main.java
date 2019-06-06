public class Main {

    public static final int MIN_NUMBER_1 = 200000;
    public static final int MAX_NUMBER_1 = 210000;

    public static final int MIN_NUMBER_2 = 220000;
    public static final int MAX_NUMBER_2 = 235000;

    public static void main(String[] args) {

        for (int i = MIN_NUMBER_1; i <= MAX_NUMBER_1 ; i++) {
            System.out.println("Ticket № " + i);
        }
        System.out.println("==============================================");
        int numberTicket = MIN_NUMBER_2;
        while(numberTicket <= MAX_NUMBER_2) {
            System.out.println("Ticket № " + numberTicket);
            numberTicket++;
        }
    }
}
