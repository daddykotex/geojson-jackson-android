package org.geojson;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import java.util.Arrays;

@JsonTypeInfo(property = "type", use = Id.NAME)
@JsonSubTypes({@Type(Feature.class), @Type(Polygon.class), @Type(MultiPolygon.class), @Type(FeatureCollection.class),
        @Type(Point.class), @Type(MultiPoint.class), @Type(MultiLineString.class), @Type(LineString.class)})
@JsonInclude(Include.NON_NULL)
public abstract class GeoJsonObject implements Parcelable {

    protected enum ParcelId {
        feature, geometry_collection, line_string, multiline_string, multipoint, multipolygon, point, polygon, feature_collection
    }

    protected GeoJsonObject() {
    }

    protected GeoJsonObject(Parcel in) {
        this.crs = in.readParcelable(Crs.class.getClassLoader());
        in.readDoubleArray(this.bbox);
        this.properties = in.readBundle();
    }

    private Crs crs;
    private double[] bbox;
    @JsonInclude(Include.NON_EMPTY)
    private Bundle properties = new Bundle();

    public Crs getCrs() {
        return crs;
    }

    public void setCrs(Crs crs) {
        this.crs = crs;
    }

    public double[] getBbox() {
        return bbox;
    }

    public void setBbox(double[] bbox) {
        this.bbox = bbox;
    }

    public void setProperty(String key, Object value) {
        this.setBundleProperty(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getProperty(String key) {
        return (T) properties.get(key);
    }

    public Bundle getProperties() {
        return properties;
    }

    public void setProperties(Bundle properties) {
        this.properties = properties;
    }

    public abstract <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor);

    private void setBundleProperty(final String key, final Object value) {
        if (value instanceof String) {
            this.properties.putString(key, (String) value);
        } else if (value instanceof Integer) {
            this.properties.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            this.properties.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            this.properties.putFloat(key, (Float) value);
        } else if (value instanceof Parcelable) {
            this.properties.putParcelable(key, (Parcelable) value);
        }

        throw new RuntimeException("Unable to add property to bundle");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof GeoJsonObject))
            return false;
        GeoJsonObject that = (GeoJsonObject) o;
        if (!Arrays.equals(bbox, that.bbox)) {
            return false;
        }
        if (crs != null ? !crs.equals(that.crs) : that.crs != null) {
            return false;
        }
        return !(properties != null ? !properties.equals(that.properties) : that.properties != null);
    }

    @Override
    public int hashCode() {
        int result = crs != null ? crs.hashCode() : 0;
        result = 31 * result + (bbox != null ? Arrays.hashCode(bbox) : 0);
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GeoJsonObject{" + "properties=" + properties + "}";
    }

    /*
        Parcelable implementation
     */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getParcelId().toString());
        dest.writeParcelable(this.crs, flags);
        dest.writeDoubleArray(this.bbox);
        dest.writeBundle(this.properties);
    }

    protected abstract ParcelId getParcelId();

    public static final Parcelable.Creator<GeoJsonObject> CREATOR = new Creator<GeoJsonObject>() {
        @Override
        public GeoJsonObject createFromParcel(Parcel in) {
            return readParcel(in);
        }

        @Override
        public GeoJsonObject[] newArray(int size) {
            return new GeoJsonObject[size];
        }
    };

    public static GeoJsonObject readParcel(Parcel in) {
        final ParcelId parcelId = ParcelId.valueOf(in.readString());
        switch (parcelId) {
            case feature:
                return new Feature(in);
            case feature_collection:
                return new FeatureCollection(in);
        }
        throw new RuntimeException("Unable to read parcel");
    }

    /*
        Parcelable implementation
     */
}
