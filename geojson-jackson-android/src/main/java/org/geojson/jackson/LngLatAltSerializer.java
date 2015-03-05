package org.geojson.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.geojson.Coordinate;

import java.io.IOException;

public class LngLatAltSerializer extends JsonSerializer<Coordinate> {

    @Override
    public void serialize(Coordinate value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        jgen.writeStartArray();
        jgen.writeNumber(value.getLongitude());
        jgen.writeNumber(value.getLatitude());
        if (value.hasAltitude())
            jgen.writeNumber(value.getAltitude());
        jgen.writeEndArray();
    }
}
