package org.geojson;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.List;

public class Polygon extends Geometry<List<Coordinate>> {

    public Polygon() {
    }

    public Polygon(List<Coordinate> polygon) {
        add(polygon);
    }

    public Polygon(Coordinate... polygon) {
        add(Arrays.asList(polygon));
    }

    public void setExteriorRing(List<Coordinate> points) {
        coordinates.add(0, points);
    }

    @JsonIgnore
    public List<Coordinate> getExteriorRing() {
        assertExteriorRing();
        return coordinates.get(0);
    }

    @JsonIgnore
    public List<List<Coordinate>> getInteriorRings() {
        assertExteriorRing();
        return coordinates.subList(1, coordinates.size());
    }

    public List<Coordinate> getInteriorRing(int index) {
        assertExteriorRing();
        return coordinates.get(1 + index);
    }

    public void addInteriorRing(List<Coordinate> points) {
        assertExteriorRing();
        coordinates.add(points);
    }

    public void addInteriorRing(Coordinate... points) {
        assertExteriorRing();
        coordinates.add(Arrays.asList(points));
    }

    private void assertExteriorRing() {
        if (coordinates.isEmpty())
            throw new RuntimeException("No exterior ring definied");
    }

    @Override
    protected ParcelId getParcelId() {
        return ParcelId.polygon;
    }

    @Override
    public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
        return geoJsonObjectVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Polygon{} " + super.toString();
    }
}
