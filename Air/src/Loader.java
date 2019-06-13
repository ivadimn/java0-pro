import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.List;

public class Loader {

    public static void main(String[] args) {
     System.out.println("Общее количество самолётов в аэропорту - " + Airport.getInstance().getAllAircrafts().size());
    }




}
