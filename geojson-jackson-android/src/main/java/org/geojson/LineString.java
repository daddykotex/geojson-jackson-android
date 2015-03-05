package org.geojson;

public class LineString extends MultiPoint {

    public LineString() {
    }

    public LineString(Coordinate... points) {
        super(points);
    }

    @Override
    protected ParcelId getParcelId() {
        return ParcelId.line_string;
    }

    @Override
    public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
        return geoJsonObjectVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "LineString{} " + super.toString();
    }

}
