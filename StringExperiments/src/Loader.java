import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String[] args)
    {
        //подсчитать сумму заработков
        String text = "Вася заработал 5300 рублей, Петя -7500 рубля, а Маша - 13000 рублей";
        String[] salary1 = text.replaceAll("[^0-9]+", " ").trim().split(" ");
        int summa1 = 0;
        for (int i = 0; i < salary1.length; i++) {
            summa1 += Integer.parseInt(salary1[i]);
        }
        System.out.println("Общий заработок 1 - " + summa1);

        //Второй способ
        int summa2 = 0;
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            summa2 += Integer.parseInt(matcher.group());
        }
        System.out.println("Общий заработок 2 - " + summa2);
    }
}