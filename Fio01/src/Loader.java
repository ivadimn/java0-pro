import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {

    public static final String[] PARTS = {"бар", "бен", "ибн", "мак"};
    public static final String[] LABELS = {"Фамилия", "Имя", "Отчество"};


    public static void main(String[] args) throws IOException {

        System.out.println("Введите Фамилию Имя Отчество :");
        String fullName = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        String [] fio = splitFullName(fullName);

        switch(fio.length) {
            case 0:
            case 1:
                System.out.println("Слишком мало слов !!!");
                break;
            case 2:
                printFio(fio);
                break;
            case 3:
                if (isNational(fio[1])) {
                    System.out.println("Имя : " + fio[0]);
                    System.out.println("Отчество : " + fio[1] + " " + fio[2]);
                }
                else {
                    printFio(fio);
                }
                break;
            case 4:
                if (fio[3].equalsIgnoreCase("оглы")) {
                    System.out.println("Фамилия : " + fio[0]);
                    System.out.println("Имя : " + fio[1]);
                    System.out.println("Отчество : " + fio[2] + " " + fio[3]);
                }
                else {
                    System.out.println("Слишком много слов !!!");
                }
                break;
             default:
                 System.out.println("Слишком много слов !!!");

        }
    }

    public static void printFio(String[] fio) {
        for (int i = 0; i < fio.length; i++) {
            System.out.println(String.format("%s : %s", LABELS[i], fio[i]));
        }
    }

    public static boolean isNational(String part) {
        for (String p : PARTS) {
            return p.equalsIgnoreCase(part);
        }
        return false;
    }


    public static String[] splitFullName(String fullName) {

        String [] fio = new String[0];
        int fioIndex = 0;
        int fromIndex = 0;
        int index = 0;
        while(index < fullName.length()) {
            if (Character.isLetter(fullName.charAt(index))) {
                fromIndex = index;
                while(index < fullName.length() && Character.isLetter(fullName.charAt(index)))
                    index++;
                fio = getNewArray(fio, fioIndex);
                fio[fioIndex++] = fullName.substring(fromIndex, index);
            }
            else {
                index++;
            }
        }
        return fio;
    }


    public static String[] getNewArray(String [] array, int currentSize) {
        String [] newArray = new String[currentSize + 1];
        for (int i = 0; i < currentSize; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}
