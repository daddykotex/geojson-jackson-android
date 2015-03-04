package org.geojson;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@JsonTypeInfo(property = "type", use = Id.NAME)
@JsonSubTypes({@Type(Feature.class), @Type(Polygon.class), @Type(MultiPolygon.class), @Type(FeatureCollection.class),
        @Type(Point.class), @Type(MultiPoint.class), @Type(MultiLineString.class), @Type(LineString.class)})
@JsonInclude(Include.NON_NULL)
public abstract class GeoJsonObject implements Parcelable {

    private static final ObjectMapper mapper = new ObjectMapper();

    private Crs crs;
    private double[] bbox;
    @JsonInclude(Include.NON_EMPTY)
    private Map<String, Object> properties = new HashMap<String, Object>();

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
        properties.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getProperty(String key) {
        return (T) properties.get(key);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public abstract <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor);

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
        try {
            dest.writeString(mapper.writeValueAsString(this));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected static <T extends GeoJsonObject> T readParcel(Parcel parcel, Class<T> clazz) {
        String json = parcel.readString();
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Parcelable implementation
     */
}
