import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {

    public static void main(String[] args) {
        String text = "LONDON — In a last-ditch effort to try to get Parliament " +
                "to pass her plan for Britain to leave the European Union, Prime " +
                "Minister Theresa May on Wednesday offered to step down and allow " +
                "another prime minister who has the confidence of her party and " +
                "lawmakers to negotiate the final details." +
                "Mrs. May's stunning overture to her fellow Conservatives came just as " +
                "Parliament tried to sideline her and come up with its own plan for Brexit, " +
                "as the process of leaving the bloc is known." +
                "But when lawmakers held a series of nonbinding votes on Wednesday night on " +
                "eight different options for Britain's future relationship with the European " +
                "Union, none mustered a majority." +
                "Mrs. May is so unpopular and has lost so much authority within her party that " +
                "her offer to step down, if her plan is approved, was greeted with relief by Tory lawmakers.";



        Pattern pattern = Pattern.compile("\\w+'?\\w");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
