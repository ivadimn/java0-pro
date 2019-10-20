import core.Connections;
import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static final String PATH = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";
    public static final String JSON_PATH = "data/mos-metro-map.json";
    public static TreeMap<Double, Line> lines = new TreeMap<>();
    public static List<Station> stations = new ArrayList<Station>();
    public static Connections connections = new Connections();
    public static JSONObject map = new JSONObject();

    public static void main(String[] args) throws IOException {
        URL url = new URL(PATH);
        Document doc = Jsoup.connect(PATH)
                .maxBodySize(0)
                .get();

        Elements elements = doc.select("table.standard");

        for (Element e : elements) {
            Elements trs = e.select("tr");
            parseLines(trs);
        }

        for (Element e : elements) {
            Elements trs = e.select("tr");
            parseConnections(trs);
        }
        System.out.println("Список пересадок :" );
        printConnections();

        saveToJson();

        readMap();


    }

    //создание списка линий состанциями  и списка станций
    public static void parseLines(Elements elements) {
        List<Line> lineList = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            lineList.clear();
            if (elements.get(i).select("th").size() > 0) {
                continue;
            }
            Element td = elements.get(i).select("td").get(0);
            lineList = getLine(td);

            String sName = getStationName(elements.get(i).select("td").get(1));
            for (Line l : lineList) {
                Line ol = lines.get(l.getNumber());
                if (ol != null) {
                    Station station = new Station(sName, ol);
                    stations.add(station);
                    ol.addStation(station);
                }
                else {
                    Station station = new Station(sName, l);
                    stations.add(station);
                    l.addStation(station);
                    lines.put(l.getNumber(), l);
                }
            }
        }
    }

     //сохдание списка соединений
    public static void parseConnections(Elements elements) {
        List<Station> stationList = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            stationList.clear();
            if (elements.get(i).select("th").size() > 0) {
                continue;
            }
            Element td = elements.get(i).select("td").get(3);
            if (td.attr("data-sort-value").equals("Infinity")) {
                continue;
            }
            stationList = getConnectionStation(td);
            List<Double> li = getLineNumber(elements.get(i).select("td").get(0));
            //у них на странице есть неточность
            //переход со станции Парк Победы Арбатско-Покровской линиии указан не верно - номер линии не правильный
            if (stationList.size() == 0) {
                continue;
            }
            for (Double num: li) {
                Station station = getStation(num, getStationName(elements.get(i).select("td").get(1)));
                TreeSet<Station> connection = new TreeSet<>();
                connection.add(station);
                connection.addAll(stationList);
                connections.addConnection(connection);
            }
        }
    }

    public static List<Line> getLine(Element td) {
        List<Line> ll = new ArrayList<>();
        Elements eSortKeys = td.select("span.sortkey");
        Elements eTitles = td.select("span.sortkey + span[title]");
        for (int i = 0; i < eSortKeys.size() - 1; i++) {
            ll.add(new Line(generateNumber(eSortKeys.get(i).text()),
                    eTitles.get(i).attr("title")));
        }
        return ll;
    }

    public static List<Double> getLineNumber(Element td) {
        List<Double> list = new ArrayList<>();
        Elements eSortKeys = td.select("span.sortkey");
        for (int i = 0; i < eSortKeys.size() - 1; i++) {
            list.add(generateNumber(eSortKeys.get(i).text()));
        }
        return list;
    }

    public static String getStationName(Element td) {
        return td.select("a").get(0).text();
    }

    public static List<Station> getConnectionStation(Element td) {
        List<Station> ls = new ArrayList<>();
        Elements eSortKeys = td.select("span.sortkey");
        Elements eTitles = td.select("span.sortkey + span[title]");
        for (int i = 0; i < eSortKeys.size(); i++) {
            Station station = getStation(generateNumber(eSortKeys.get(i).text()), eTitles.get(i).attr("title"));
            if (station != null) {
                ls.add(station);
            }
        }
        return ls;
    }

    public static Station getStation(double num, String sconnection) {
        String UpConn = sconnection.toUpperCase();
        Optional<Station> optionalStation = stations.stream()
                .filter(s -> UpConn.indexOf(s.getName().toUpperCase()) >= 0 && s.getLine().getNumber() == num).findFirst();
        return optionalStation.isPresent() ? optionalStation.get() : null;
    }

    public static double generateNumber(String n) {
        String clear = n.replaceAll("\\D", "");
        return n.length() == clear.length() ? Double.valueOf(clear) : Double.valueOf(clear) + 0.5;
    }

    //запись в JSON file
    public static void saveToJson() {

        saveStations();
        saveConnections();
        saveLines();

        try (FileWriter file = new FileWriter(JSON_PATH)) {

            file.write(map.toJSONString());
            file.flush();
            file.close();

            System.out.println("Данные записаны в файл : " + JSON_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveStations() {
        //станции
        HashMap<Double, List<String>> st = new HashMap<>();
        for (Double num : lines.keySet()) {
            Line line = lines.get(num);
            List<String> ls = new ArrayList();
            line.getStations().forEach(s -> ls.add(s.getName()));
            st.put(num, ls);
        }
        map.put("stations", st);
    }

    public static void saveConnections() {
        //пересадки
        JSONArray jConns = new JSONArray();
        List<TreeSet<Station>> conns = connections.getConnections();
        for (TreeSet<Station> stats: conns) {
            JSONArray jCon = new JSONArray();
            for (Station station: stats) {
                JSONObject jc = new JSONObject();
                jc.put("line", station.getLine().getNumber());
                jc.put("station", station.getName());
                jCon.add(jc);
            }
            jConns.add(jCon);
        }
        map.put("connections", jConns);
    }

    public static void saveLines() {
        JSONArray jLines = new JSONArray();
        for (Double num : lines.keySet()) {
            JSONObject jLine = new JSONObject();
            jLine.put("number", num);
            jLine.put("name", lines.get(num).getName());
            jLines.add(jLine);
        }
        map.put("lines", jLines);
    }

    //чтение из JSON файла
    private static void readMap()
    {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(readFromJsonFile());

            JSONArray jLines = (JSONArray) jsonData.get("lines");
            TreeMap<Double, Line> lineList = readLines(jLines);

            JSONObject jStations = (JSONObject) jsonData.get("stations");
            readStations(jStations, lineList);
            printLines(lineList);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
   }

    private static TreeMap<Double, Line> readLines(JSONArray jLines) {
        TreeMap<Double, Line> lineList = new TreeMap<>();
        for (int i = 0; i < jLines.size(); i++) {
            JSONObject jLine = (JSONObject) jLines.get(i);
            lineList.put((Double)jLine.get("number"), new Line((Double)jLine.get("number"), (String) jLine.get("name")));
        }
        return lineList;
    }

    private static void readStations(JSONObject jStations, TreeMap<Double, Line> lines) {

        for (Double num : lines.keySet()) {
            Line line = lines.get(num);
            JSONArray stationsArray = (JSONArray) jStations.get(Double.toString(num));
            stationsArray.forEach(sname -> line.addStation(new Station((String) sname, line)));
        }
    }

    private static String readFromJsonFile()
    {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(JSON_PATH));
            lines.forEach(line -> builder.append(line));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public static void printLines(TreeMap<Double, Line> lines) {
        for (Double key : lines.keySet()) {
            System.out.println(lines.get(key).toString());
            lines.get(key).getStations().forEach(s -> System.out.println("\t" + s.toString()));
        }
    }

    public static void printStations() {
        stations.forEach(System.out::println);
    }

    public static void printConnections() {
        List<TreeSet<Station>> conns = connections.getConnections();
        StringBuilder sb = new StringBuilder();
        for (TreeSet<Station> sts : conns) {
            for (Station st : sts) {
                sb.append(st.toString() + " - ");

            }
            System.out.println(sb.toString());
            sb.delete(0, sb.length() - 1);
        }
    }
}
