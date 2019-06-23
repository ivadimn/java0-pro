import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {
    public static void main(String[] args) throws IOException {

        System.out.println("Введите номер телефона :");
        String phone = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

        System.out.println(phone.replaceAll("[\\D]+",""));
    }
}
