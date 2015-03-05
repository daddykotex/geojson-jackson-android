package org.geojson;

import java.util.List;

public class MultiLineString extends Geometry<List<Coordinate>> {

    public MultiLineString() {
    }

    public MultiLineString(List<Coordinate> line) {
        add(line);
    }

    @Override
    protected ParcelId getParcelId() {
        return ParcelId.multiline_string;
    }

    @Override
    public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
        return geoJsonObjectVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "MultiLineString{} " + super.toString();
    }
}
