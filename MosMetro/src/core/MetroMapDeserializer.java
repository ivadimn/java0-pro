package core;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.*;

public class MetroMapDeserializer implements JsonDeserializer<MetroMap> {

    private MetroMap metroMap = new MetroMap();
    private List<Station> stations = new ArrayList<Station>();

    @Override
    public MetroMap deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {


        JsonObject jsonMap = jsonElement.getAsJsonObject();
        JsonArray jLines = jsonMap.getAsJsonArray("lines");

        TreeMap<String, Line> lines = new TreeMap<>(new LineComparator());
        for (JsonElement element : jLines) {
            Line line = context.deserialize(element, Line.class);
            lines.put(line.getNumber(), line);
        }

        JsonObject jStations = jsonMap.getAsJsonObject("stations");
        for (String num : lines.keySet()) {
            Line line = lines.get(num);
            JsonArray jstArray = jStations.getAsJsonArray(num);
            for (JsonElement element : jstArray) {
                Station station = new Station(element.getAsString(), line);
                line.addStation(station);
                stations.add(station);
            }
        }
        metroMap.setLines(lines);
        metroMap.setConnections(getConnections(jsonMap.getAsJsonArray("connections")));
        metroMap.setStations(stations);
        return metroMap;
    }

    private Connections getConnections(JsonArray jConnections) {
        Connections connections = new Connections();

        for (JsonElement jConns : jConnections) {
            JsonArray jconn = jConns.getAsJsonArray();
            TreeSet<Station> connStation = new TreeSet<>();
            for (JsonElement element : jconn) {
                String num = element.getAsJsonObject().get("line").getAsString();
                String st = element.getAsJsonObject().get("station").getAsString();
                connStation.add(getStation(num, st));
            }
            connections.addConnection(connStation);
        }
        return connections;
    }

    private Station getStation(String num, String sconnection) {
        String UpConn = sconnection.toUpperCase();
        Optional<Station> optionalStation = stations.stream()
                .filter(s -> UpConn.indexOf(s.getName().toUpperCase()) >= 0 && num.equalsIgnoreCase(s.getLine().getNumber())).findFirst();
        return optionalStation.isPresent() ? optionalStation.get() : null;
    }
}
