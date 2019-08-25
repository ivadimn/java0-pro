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
     System.out.println("Сейчас время - " + HOUR_FORMAT.format(new Date()));
     Airport airport = Airport.getInstance();
     Stream<Terminal> terminalStream = airport.getTerminals().stream();
     terminalStream.forEach(t -> {
         t.getFlights().stream().filter(f ->  {
             Calendar cal = Calendar.getInstance();
             cal.setTime(f.getDate());   //дата вылета
             Calendar now = Calendar.getInstance();
             Calendar now_plus2 = Calendar.getInstance();
             now_plus2.add(Calendar.HOUR, 2);
             return f.getType() == Flight.Type.DEPARTURE
                     &&  (cal.after(now) && cal.before(now_plus2));
         }).forEach(System.out::println);

     });
    }




}
