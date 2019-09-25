import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String fileName = "data/movementList.csv";

    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
            int numCols = lines.get(0).split(",").length;
            lines.remove(0);
            //lines.stream().forEach(System.out::println);
            getListOperation(lines, numCols);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Operation> getListOperation(List<String> lines, int cols) {
        List<Operation> opers = new ArrayList<>();
        for (String s : lines) {
            String[] elements = s.split(",");
            if (elements.length != cols) {
                System.out.println("Wrong lines : " + s);
            }
        }
        return opers;
    }

    public static Operation parseWrongLine(String[] es, int cols) {
        String[] elements = new String[cols];
        int nin = 0;
        String ws;
        for (String s: es) {
            if(s.indexOf("\"") == 0 && nin == 0) {
                ws = s.substring(1);
                nin++;
            }
            else if ((s.indexOf("\"") == s.length() - 1) && nin == 1) {

            }
        }
        return null;
    }
}
