import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {
    public static void main(String[] args) throws IOException {

        System.out.println("Введите Фамилию Имя Отчество :");
        String fullName = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        String [] fio = new String[3];

        int fioIndex = 0;
        int fromIndex = 0;
        int index = 0;
        while(index < fullName.length()) {
            if (Character.isLetter(fullName.charAt(index))) {
                fromIndex = index;
                while(index < fullName.length() && Character.isLetter(fullName.charAt(index)))
                    index++;
                fio[fioIndex++] = fullName.substring(fromIndex, index);
            }
            else {
                index++;
            }

        }
        System.out.println("Фамимля :  " + fio[0]);
        System.out.println("Имя :      " + fio[1]);
        System.out.println("Отчество : " + fio[2]);


    }
}
