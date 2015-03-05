package org.geojson;

public class MultiPoint extends Geometry<Coordinate> {

    public MultiPoint() {
    }

    public MultiPoint(Coordinate... points) {
        super(points);
    }

    @Override
    protected ParcelId getParcelId() {
        return ParcelId.multipoint;
    }


    @Override
    public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
        return geoJsonObjectVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "MultiPoint{} " + super.toString();
    }
}
