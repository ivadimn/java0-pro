import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Loader {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - E", Locale.ENGLISH);
        Calendar birthday = Calendar.getInstance();
        int currentYear = birthday.get(Calendar.YEAR);
        birthday.set(1965, Calendar.JUNE, 21);
        for (int i = 0; birthday.get(Calendar.YEAR) <= currentYear; i++) {
            System.out.println(i + " - " + dateFormat.format(birthday.getTime()));
            birthday.add(Calendar.YEAR, 1);
        }
    }
}
