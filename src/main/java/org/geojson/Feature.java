package org.geojson;

import android.os.Parcel;

public class Feature extends GeoJsonObject {

    private GeoJsonObject geometry;
    private String id;

    public Feature() {
    }

    public Feature(Parcel in) {
        super(in);
        this.geometry = in.readParcelable(GeoJsonObject.class.getClassLoader());
        this.id = in.readString();
    }

    public GeoJsonObject getGeometry() {
        return geometry;
    }

    public void setGeometry(GeoJsonObject geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected ParcelId getParcelId() {
        return ParcelId.feature;
    }

    @Override
    public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
        return geoJsonObjectVisitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Feature))
            return false;
        Feature feature = (Feature) o;
        if (geometry != null ? !geometry.equals(feature.geometry) : feature.geometry != null) {
            return false;
        }
        return !(id != null ? !id.equals(feature.id) : feature.id != null);
    }

    @Override
    public int hashCode() {
        int result = geometry != null ? geometry.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Feature{" + "geometry=" + geometry + ", id='" + id + "'}";
    }

    /*
        Parcelable implementation
     */

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.geometry, flags);
        dest.writeString(this.id);
    }
    /*
        Parcelable implementation
     */

}
