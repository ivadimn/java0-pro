import java.util.Date;

public class Operation {
    private String typeAccout;
    private String account;
    private String currency;
    private Date dateOperation;
    private String wiring;
    private String description;
    private double prihod;
    private double rashod;

    public Operation(String typeAccout, String account, String currency, Date dateOperation, String wiring,
                     String description, double prihod, double rashod) {
        this.typeAccout = typeAccout;
        this.account = account;
        this.currency = currency;
        this.dateOperation = dateOperation;
        this.wiring = wiring;
        this.description = description;
        this.prihod = prihod;
        this.rashod = rashod;
    }

    public String getDescription() {
        return description;
    }

    public double getPrihod() {
        return prihod;
    }

    public double getRashod() {
        return rashod;
    }
}
