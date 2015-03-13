package org.geojson;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Crs implements Parcelable {

    private String type = "name";
    private Map<String, Object> properties = new HashMap<>();

    public Crs() {
    }

    @SuppressWarnings("unchecked")
    public Crs(Parcel in) {
        this.setProperties(in.readHashMap(null));
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> bundle) {
        this.properties = bundle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Crs)) {
            return false;
        }
        Crs crs = (Crs) o;
        if (properties != null ? !properties.equals(crs.properties) : crs.properties != null) {
            return false;
        }
        return !(type != null ? !type.equals(crs.type) : crs.type != null);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Crs{" + "type='" + type + '\'' + ", properties=" + properties + '}';
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
        dest.writeMap(this.properties);
    }

    public static final Parcelable.Creator<Crs> CREATOR = new Parcelable.Creator<Crs>() {
        @Override
        public Crs createFromParcel(Parcel in) {
            return new Crs(in);
        }

        @Override
        public Crs[] newArray(int size) {
            return new Crs[size];
        }
    };

    /*
        Parcelable implementation
     */
}
