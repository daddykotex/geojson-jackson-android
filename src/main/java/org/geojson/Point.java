package org.geojson;

import android.os.Parcel;

public class Point extends GeoJsonObject {

    private Coordinate coordinates;

    public Point() {
    }

    public Point(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public Point(double longitude, double latitude) {
        coordinates = new Coordinate(longitude, latitude);
    }

    public Point(double longitude, double latitude, double altitude) {
        coordinates = new Coordinate(longitude, latitude, altitude);
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }


    @Override
    protected ParcelId getParcelId() {
        return ParcelId.point;
    }

    @Override
    public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
        return geoJsonObjectVisitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Point point = (Point) o;
        return !(coordinates != null ? !coordinates.equals(point.coordinates) : point.coordinates != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Point{" + "coordinates=" + coordinates + "} " + super.toString();
    }


    /*
        Parcelable implementation
     */

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.coordinates, flags);
    }
    /*
        Parcelable implementation
     */
}
