package org.geojson;

import java.util.List;

public class MultiPolygon extends Geometry<List<List<Coordinate>>> {

    public MultiPolygon() {
    }

    public MultiPolygon(Polygon... polygons) {
        for (Polygon polygon : polygons) {
            add(polygon);
        }
    }

    public MultiPolygon add(Polygon polygon) {
        coordinates.add(polygon.getCoordinates());
        return this;
    }

    @Override
    protected ParcelId getParcelId() {
        return ParcelId.multipolygon;
    }

    @Override
    public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
        return geoJsonObjectVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "MultiPolygon{} " + super.toString();
    }
}
