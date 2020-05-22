import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class Loader {

    private static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");
    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        Calendar now_plus2 = Calendar.getInstance();
        now_plus2.add(Calendar.HOUR, 2);
        System.out.println("Сейчас время - " + HOUR_FORMAT.format(new Date()));
        Airport.getInstance().getTerminals().stream().flatMap(term -> term.getFlights().stream())
                 .filter(f -> f.getType() == Flight.Type.DEPARTURE
                         &&  f.getDate().getTime() >= now.getTime().getTime()
                         &&  f.getDate().getTime() <= now_plus2.getTime().getTime()
                 ).forEach(System.out::println);

    }




}
