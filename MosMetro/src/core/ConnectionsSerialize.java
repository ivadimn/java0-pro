package core;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.TreeSet;

public class ConnectionsSerialize implements JsonSerializer<Connections> {
    @Override
    public JsonElement serialize(Connections connections, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jConnections = new JsonObject();
        List<TreeSet<Station>> conns = connections.getConnections();
        JsonArray jConnectionsArray = new JsonArray();
        for (TreeSet<Station> sts : conns) {
            JsonArray jconnArray = new JsonArray();
            for (Station st : sts) {
                JsonObject jconn = new JsonObject();
                jconn.addProperty("line", st.getLine().getNumber());
                jconn.addProperty("station", st.getName());
                jconnArray.add(jconn);
            }
            jConnectionsArray.add(jconnArray);
        }
        return jConnectionsArray;
    }
}
