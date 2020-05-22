package core;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class MetroMapSerializer implements JsonSerializer<MetroMap> {

    @Override
    public JsonElement serialize(MetroMap metroMap, Type type, JsonSerializationContext context) {

        JsonObject jsonMap = new JsonObject();
        TreeMap<String, Line> lines = metroMap.getLines();
        JsonObject jLineStations = new JsonObject();
        for (String num : lines.keySet()) {
            Line line = lines.get(num);
            List<String> ls = new ArrayList();
            JsonArray jst = new JsonArray();
            line.getStations().forEach(s -> jst.add(s.getName()));
            jLineStations.add(num, jst);
        }
        jsonMap.add("stations", jLineStations);
        JsonArray jLines = new JsonArray();
        for (Line line: lines.values()) {
            jLines.add(context.serialize(line));
        }
        jsonMap.add("lines", jLines);
        jsonMap.add("connections", context.serialize(metroMap.getConnections()));
        return jsonMap;
    }
}
