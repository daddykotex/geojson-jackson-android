package org.geojson.jackson;

import org.geojson.Coordinate;

import java.util.Arrays;
import java.util.List;

public class MockData {

    public static final List<Coordinate> EXTERNAL = Arrays.asList(new Coordinate(100, 0), new Coordinate(101, 0),
            new Coordinate(101, 1), new Coordinate(100, 1), new Coordinate(100, 0));
    public static final List<Coordinate> INTERNAL = Arrays.asList(new Coordinate(100.2, 0.2), new Coordinate(100.8, 0.2),
            new Coordinate(100.8, 0.8), new Coordinate(100.2, 0.8), new Coordinate(100.2, 0.2));
}
