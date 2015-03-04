package org.geojson.jackson;

import android.os.Bundle;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Set;

public class BundleSerializer extends JsonSerializer<Bundle> {

    @Override
    public void serialize(Bundle value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        Set<String> keys = value.keySet();
        if (keys != null && !keys.isEmpty()) {
            for (String key : keys) {
                gen.writeObjectField(key, value.get(key));
            }
        }
        gen.writeEndObject();
    }
}
