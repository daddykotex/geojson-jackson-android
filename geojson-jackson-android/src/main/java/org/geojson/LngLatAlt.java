package org.geojson;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.geojson.jackson.LngLatAltDeserializer;
import org.geojson.jackson.LngLatAltSerializer;

@JsonDeserialize(using = LngLatAltDeserializer.class)
@JsonSerialize(using = LngLatAltSerializer.class)
public class LngLatAlt implements Parcelable {

    private double longitude;
    private double latitude;
    private double altitude = Double.NaN;

    public LngLatAlt() {
    }

    public LngLatAlt(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LngLatAlt(double longitude, double latitude, double altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public LngLatAlt(Parcel in) {
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        if (in.dataAvail() > 0) {
            this.altitude = in.readDouble();
        }
    }

    public boolean hasAltitude() {
        return !Double.isNaN(altitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LngLatAlt)) {
            return false;
        }
        LngLatAlt lngLatAlt = (LngLatAlt) o;
        return Double.compare(lngLatAlt.latitude, latitude) == 0 && Double.compare(lngLatAlt.longitude, longitude) == 0
                && Double.compare(lngLatAlt.altitude, altitude) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(longitude);
        int result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(altitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "LngLatAlt{" + "longitude=" + longitude + ", latitude=" + latitude + ", altitude=" + altitude + '}';
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
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.altitude);
    }

    public static final Parcelable.Creator<LngLatAlt> CREATOR = new Parcelable.Creator<LngLatAlt>() {
        @Override
        public LngLatAlt createFromParcel(Parcel in) {
            return new LngLatAlt(in);
        }

        @Override
        public LngLatAlt[] newArray(int size) {
            return new LngLatAlt[size];
        }
    };

    /*
        Parcelable implementation
     */
}
