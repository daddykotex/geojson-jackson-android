package org.geojson.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.geojson.GeometryCollection;
import org.geojson.LineString;
import org.geojson.Coordinate;
import org.geojson.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeometryCollectionTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void itShouldSerialize() throws Exception {
        GeometryCollection gc = new GeometryCollection();
        gc.add(new Point(100, 0));
        gc.add(new LineString(new Coordinate(101, 0), new Coordinate(102, 1)));
        assertEquals("{\"type\":\"GeometryCollection\","
                        + "\"geometries\":[{\"type\":\"Point\",\"coordinates\":[100.0,0.0]},"
                        + "{\"type\":\"LineString\",\"coordinates\":[[101.0,0.0],[102.0,1.0]]}]}",
                mapper.writeValueAsString(gc));
    }
}
