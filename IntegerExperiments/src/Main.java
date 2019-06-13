public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        System.out.println(sumDigits(234));
        System.out.println(sumDigits(147));
        System.out.println(sumDigits(222222));


    }

    public static Integer sumDigits(Integer number)
    {
        String digits = number.toString();
        int summa = 0;
        for (int i = 0; i < digits.length(); i++) {
            summa += Integer.parseInt(Character.toString(digits.charAt(i)));

        }
        return summa;
    }
}
