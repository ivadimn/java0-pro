import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class RouteCalculatorTest extends TestCase {

    private String[] lines = {"Первая линия", "Вторя линия", "Третья линия"};
    private String[] stations1 = {"Алексеевская_1", "Рижская_1-2", "Сухаревская_1", "Тургеневская_1-3", "Новокузнецкая_1"};
    private String[] stations2 = {"Войковская_2", "Сокол_2", "Аэропорт_2-1", "Динамо_2", "Белорусская_2-3", "Тверская_2"};
    private String[] stations3 = {"Ясенево_3", "Теплый стан_3", "Коньково_3-1", "Беляево_3", "Калужская_3-2", "Профсоюзная_3"};

    RouteCalculator routeCalculator;
    StationIndex stationIndex = new StationIndex();
    @Override
    protected void setUp() throws Exception {
        for (int i = 0; i < lines.length; i++) {
            stationIndex.addLine(new Line(i + 1, lines[i]));
        }
        Line line = stationIndex.getLine(1);
        for (String s : stations1) {
            Station station = new Station(s, line);
            line.addStation(station);
            stationIndex.addStation(station);
        }
        line = stationIndex.getLine(2);;
        for (String s : stations2) {
            Station station = new Station(s, line);
            line.addStation(station);
            stationIndex.addStation(station);
        }
        line = stationIndex.getLine(3);;
        for (String s : stations3) {
            Station station = new Station(s, line);
            line.addStation(station);
            stationIndex.addStation(station);
        }
        List<Station> cs1 = new ArrayList<>();
        cs1.add(stationIndex.getStation("Рижская_1-2", 1));
        cs1.add(stationIndex.getStation("Аэропорт_2-1", 2));
        stationIndex.addConnection(cs1);

        List<Station> cs2 = new ArrayList<>();
        cs2.add(stationIndex.getStation("Белорусская_2-3", 2));
        cs2.add(stationIndex.getStation("Калужская_3-2", 3));
        stationIndex.addConnection(cs2);

        List<Station> cs3 = new ArrayList<>();
        cs3.add(stationIndex.getStation("Коньково_3-1", 3));
        cs3.add(stationIndex.getStation("Тургеневская_1-3", 1));
        stationIndex.addConnection(cs3);
        routeCalculator = new RouteCalculator(stationIndex);

    }

    public void testgetShortestRouteOnTheLine() {
        Station from = stationIndex.getStation("Ясенево_3", 3);
        Station to = stationIndex.getStation("Беляево_3", 3);
        Station s1 = stationIndex.getStation("Теплый стан_3", 3);
        Station s2 = stationIndex.getStation("Коньково_3-1", 3);
        List<Station> route = routeCalculator.getShortestRoute(from, to);
        assertEquals(from, route.get(0));
        assertEquals(s1, route.get(1));
        assertEquals(s2, route.get(2));
        assertEquals(to, route.get(3));
    }

    public void testgetShortestRouteWithOneConnection() {
        Station from = stationIndex.getStation("Динамо_2", 2);
        Station to = stationIndex.getStation("Беляево_3", 3);
        Station s1 = stationIndex.getStation("Белорусская_2-3", 2);
        Station s2 = stationIndex.getStation("Калужская_3-2", 3);
        List<Station> route = routeCalculator.getShortestRoute(from, to);
        assertEquals(from, route.get(0));
        assertEquals(s1, route.get(1));
        assertEquals(s2, route.get(2));
        assertEquals(to, route.get(3));
    }


    public void testgetShortestRouteWithTwoConnections() {
        Station from = stationIndex.getStation("Тверская_2", 2);
        Station s1 = stationIndex.getStation("Белорусская_2-3", 2);
        Station s2 = stationIndex.getStation("Калужская_3-2", 3);
        Station s3 = stationIndex.getStation("Беляево_3", 3);
        Station s4 = stationIndex.getStation("Коньково_3-1", 3);
        Station s5 = stationIndex.getStation("Тургеневская_1-3", 1);
        Station to = stationIndex.getStation("Новокузнецкая_1", 1);

        List<Station> route = routeCalculator.getShortestRoute(from, to);
        assertEquals(from, route.get(0));
        assertEquals(s1, route.get(1));
        assertEquals(s2, route.get(2));
        assertEquals(s3, route.get(3));
        assertEquals(s4, route.get(4));
        assertEquals(s5, route.get(5));
        assertEquals(to, route.get(6));
    }

    public void testcalculateDuration() {
        List<Station> route = new ArrayList<>();
        route.add(stationIndex.getStation("Тверская_2", 2));
        route.add(stationIndex.getStation("Белорусская_2-3", 2));
        route.add(stationIndex.getStation("Калужская_3-2", 3));
        route.add(stationIndex.getStation("Беляево_3", 3));
        route.add(stationIndex.getStation("Коньково_3-1", 3));
        route.add(stationIndex.getStation("Тургеневская_1-3", 1));
        route.add(stationIndex.getStation("Новокузнецкая_1", 1));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 17;
        assertEquals(expected, actual);
    }

    public void testcalculateDuration1() {
        List<Station> route = new ArrayList<>();
        route.add(stationIndex.getStation("Тверская_2", 2));
        route.add(stationIndex.getStation("Белорусская_2-3", 2));
        route.add(stationIndex.getStation("Динамо_2", 2));
        route.add(stationIndex.getStation("Аэропорт_2-1", 2));
        route.add(stationIndex.getStation("Рижская_1-2", 1));
        route.add(stationIndex.getStation("Сухаревская_1", 1));
        route.add(stationIndex.getStation("Тургеневская_1-3", 1));
        route.add(stationIndex.getStation("Новокузнецкая_1", 1));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 18.5;
        assertEquals(expected, actual);
    }
}
