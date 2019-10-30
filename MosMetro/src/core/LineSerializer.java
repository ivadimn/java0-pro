package core;

import com.google.gson.*;

import java.lang.reflect.Type;

public class LineSerializer implements JsonSerializer<Line> {

    @Override
    public JsonElement serialize(Line line, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonLine = new JsonObject();
        jsonLine.addProperty("number", line.getNumber());
        jsonLine.addProperty("name", line.getName());
        return jsonLine;
    }
}
