package org.geojson;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Crs implements Parcelable {

    private String type = "name";
    private Bundle properties = new Bundle();

    public Crs() {
    }

    public Crs(Parcel in) {
        Crs crs = new Crs();
        crs.setProperties(in.readBundle());
    }

    public String getType() {
        return type;
    }

    public Bundle getProperties() {
        return properties;
    }

    public void setProperties(Bundle bundle) {
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
        dest.writeBundle(this.properties);
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
