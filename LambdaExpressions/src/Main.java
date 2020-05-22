import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();
        Collections.sort(staff, (s1, s2) -> {
            int r = s1.getSalary() - s2.getSalary();
            return (r != 0) ? r : s1.getName().compareTo(s2.getName());
        });

        for (Employee e : staff) {
            System.out.println(e.toString());
        }

        System.out.println("Максимальняа зарплата принятого в 2017 году");
        staff.stream().filter(s ->  {
            Calendar cal = Calendar.getInstance();
            cal.setTime(s.getWorkStart());
            return cal.get(Calendar.YEAR) == 2017;
        }).max(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);

    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }

    private static void getMaxSalary(int year) {

    }
}