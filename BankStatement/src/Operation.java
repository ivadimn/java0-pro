import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operation {
    private String typeAccout;
    private String account;
    private String currency;
    private Date dateOperation;
    private String wiring;
    private String description;
    private String shortDescription;
    private double prihod;
    private double rashod;

    private final DateFormat format = new SimpleDateFormat("dd.MM.yy");

    public Operation(String typeAccout, String account, String currency, String dateOperation, String wiring,
                     String description, String prihod, String rashod) throws ParseException {
        this.typeAccout = typeAccout;
        this.account = account;
        this.currency = currency;
        this.dateOperation = format.parse(dateOperation);
        this.wiring = wiring;
        this.description = description;
        this.prihod = Double.parseDouble(prihod);
        this.rashod = Double.parseDouble(rashod);
        this.shortDescription = setShortDescription();
    }

    public String getDescription() {
        return description;
    }
    public String getShortDescription() {
        return shortDescription;
    }

    public String setShortDescription() {
        Pattern p = Pattern.compile("\\s{4,}");
        Matcher m = p.matcher(this.description);
        String[] ds =  m.replaceAll(";").split(";");
        //p = Pattern.compile("\\\\[\\sA-Za-z]+[\\s]*[A-Za-z>\\s_\\d]+$");
        p = Pattern.compile("[\\\\/]{1}[A-Za-z>\\s_\\d]+$");
        m = p.matcher(ds[1]);
        if (m.find()) {
            return m.group().substring(1);
        }
        else {
            return ds[1];
        }
    }

    public double getPrihod() {
        return prihod;
    }

    public double getRashod() {
        return rashod;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s", typeAccout, account, currency,
                format.format(dateOperation), wiring, description, String.valueOf(prihod), String.valueOf(rashod));
    }
}
