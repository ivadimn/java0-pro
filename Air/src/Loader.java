import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.List;

public class Loader {

    public static void main(String[] args) {

        Airport airport = Airport.getInstance();
        List<Aircraft> aircrafts = airport.getAllAircrafts();
        List<Terminal> terminals = airport.getTerminals();
        System.out.println("\t\tАэропорт");
        System.out.println();

        for (Terminal terminal : terminals) {
            System.out.println("\nТерминал - " + terminal.getName());
            List<Flight> flights = terminal.getFlights();
            System.out.println("\t:Табло:");
            for (Flight fl : flights) {
                System.out.println("\t\t" + fl.toString());
            }
            List<Aircraft> aitcrafts = terminal.getParkedAircrafts();
            System.out.println("\tСамолёты на парковке:");
            for (Aircraft aircraft: aircrafts) {
                System.out.println("\t\t" + aircraft.getModel());
            }
        }
    }




}
