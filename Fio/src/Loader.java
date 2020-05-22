import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class Loader  {
    public static void main(String[] args) throws IOException {

        System.out.println("Введите Фамилию Имя Отчество :");
        String fullName = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

        String[] fio = fullName.split("[\\d\\s,.|?:;]+");
        if (fio.length >= 3 ) {
            System.out.println("Фамимля :  " + fio[0]);
            System.out.println("Имя :      " + fio[1]);
            System.out.println("Отчество : " + fio[2]);
        }
        else {
            System.out.println("Чего-то забыли ввести!!!");
        }
    }
}
