package core;

import com.google.gson.*;

import java.lang.reflect.Type;

public class LineDeserialize implements JsonDeserializer<Line> {
    @Override
    public Line deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonLine = jsonElement.getAsJsonObject();
        return new Line(jsonLine.get("number").getAsString(), jsonLine.get("name").getAsString());
    }
}
