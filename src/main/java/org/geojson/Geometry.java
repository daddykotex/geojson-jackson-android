package org.geojson;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Geometry<T> extends GeoJsonObject {

    protected List<T> coordinates = new ArrayList<>();

    public Geometry() {
    }

    @SafeVarargs
    public Geometry(T... elements) {
        Collections.addAll(coordinates, elements);
    }

    public Geometry<T> add(T elements) {
        coordinates.add(elements);
        return this;
    }

    public List<T> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<T> coordinates) {
        this.coordinates = coordinates;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Geometry)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Geometry geometry = (Geometry) o;
        return !(coordinates != null ? !coordinates.equals(geometry.coordinates) : geometry.coordinates != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Geometry{" + "coordinates=" + coordinates + "} " + super.toString();
    }
    /*
        Parcelable implementation
     */

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(this.coordinates);
    }
    /*
        Parcelable implementation
     */

}
