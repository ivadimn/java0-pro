import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static String fileName = "data/movementList.csv";
    private static List<Operation> operationList;
    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
            int numCols = lines.get(0).split(",").length;
            lines.remove(0);
            operationList = getListOperation(lines, numCols);

            System.out.println(" Общий приход = " + operationList.stream().mapToDouble(Operation::getPrihod).sum());
            System.out.println(" Общий расход = " + operationList.stream().mapToDouble(Operation::getRashod).sum());
            Map<String, Double> byItems = calculateRashodByItems();
            System.out.println("Разбивка расходов ");
            for (String key : byItems.keySet()) {
                System.out.println(key + " - " + byItems.get(key));
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static List<Operation> getListOperation(List<String> lines, int cols) {
        List<Operation> opers = new ArrayList<>();
        for (String s : lines) {
            try {
                String[] op = s.split(",");
                if (op.length == cols) {
                    opers.add(new Operation(op[0], op[1], op[2], op[3], op[4], op[5], op[6], op[7]));
                } else {
                     opers.add(parseWrongLine(s));
                }
            }
            catch (ParseException ex) {
                ex.printStackTrace();
            }


        }
        return opers;
    }

    public static Operation parseWrongLine(String s) throws ParseException{
        Pattern p = Pattern.compile("\"[0-9]+,[0-9]{1,2}\"");
        Matcher m = p.matcher(s);

        while(m.find()) {
            String num = m.group().substring(1, m.group().length()-1);
            num = num.replace(",", ".");
            s = s.replace(m.group(), num);
        }
        String[] op = s.split(",");
        return new Operation(op[0], op[1], op[2], op[3], op[4], op[5], op[6], op[7]);
    }

    public static Map<String, Double> calculateRashodByItems() {
        Map<String, Double> byItems = new TreeMap<>();
        for (Operation o : operationList) {
            String key = o.getShortDescription();
            if (byItems.containsKey(key)) {
                byItems.put(key, byItems.get(key) + o.getRashod());
            }
            else {
                byItems.put(key, o.getRashod());
            }
        }
        return byItems;
    }
}
