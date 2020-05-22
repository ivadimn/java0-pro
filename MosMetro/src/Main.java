import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.*;
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
    public static List<Station> stations = new ArrayList<Station>();
    public static Connections connections = new Connections();
    public static JSONObject map = new JSONObject();

    public static TreeMap<String, Line> lines = new TreeMap<>(new LineComparator());

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
        printConnections(connections.getConnections());

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
            List<String> li = getLineNumber(elements.get(i).select("td").get(0));
            //у них на странице есть неточность
            //переход со станции Парк Победы Арбатско-Покровской линиии указан не верно - номер линии не правильный
            if (stationList.size() == 0) {
                continue;
            }
            for (String num: li) {
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
            ll.add(new Line(eSortKeys.get(i).text(),
                    eTitles.get(i).attr("title")));
        }
        return ll;
    }

    public static List<String> getLineNumber(Element td) {
        List<String> list = new ArrayList<>();
        Elements eSortKeys = td.select("span.sortkey");
        for (int i = 0; i < eSortKeys.size() - 1; i++) {
            list.add(eSortKeys.get(i).text());
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
            Station station = getStation(eSortKeys.get(i).text(), eTitles.get(i).attr("title"));
            if (station != null) {
                ls.add(station);
            }
        }
        return ls;
    }

    public static Station getStation(String num, String sconnection) {
        String UpConn = sconnection.toUpperCase();
        Optional<Station> optionalStation = stations.stream()
                .filter(s -> UpConn.indexOf(s.getName().toUpperCase()) >= 0 && num.equalsIgnoreCase(s.getLine().getNumber())).findFirst();
        return optionalStation.isPresent() ? optionalStation.get() : null;
    }



    //запись в JSON file
    public static void saveToJson() {

        MetroMap metroMap = new MetroMap();
        metroMap.setLines(lines);
        metroMap.setConnections(connections);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Line.class, new LineSerializer())
                .registerTypeAdapter(MetroMap.class, new MetroMapSerializer())
                .registerTypeAdapter(Connections.class, new ConnectionsSerialize())
                .create();
        String json = gson.toJson(metroMap);
        System.out.println(json);

        try (FileWriter file = new FileWriter(JSON_PATH)) {

            file.write(json);
            file.flush();
            file.close();

            System.out.println("Данные записаны в файл : " + JSON_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //чтение из JSON файла
    private static void readMap()
    {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Line.class, new LineDeserialize())
                .registerTypeAdapter(MetroMap.class, new MetroMapDeserializer())
                .create();
        MetroMap metroMap = gson.fromJson(readFromJsonFile(), MetroMap.class);
        System.out.println("---------------------------------Данные прочитаны----------------------------------");
        printLines(metroMap.getLines());

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

    public static void printLines(TreeMap<String, Line> lines) {
        for (String key : lines.keySet()) {
            System.out.println(lines.get(key).toString());
            lines.get(key).getStations().forEach(s -> System.out.println("\t" + s.toString()));
        }
    }

    public static void printStations(List<Station> stations) {
        stations.forEach(System.out::println);
    }

    public static void printConnections(List<TreeSet<Station>> conns) {
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
