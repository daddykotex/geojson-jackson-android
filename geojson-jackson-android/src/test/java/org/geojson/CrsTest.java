package org.geojson;

import android.os.Bundle;
import android.os.Parcel;

import org.geojson.mocks.MockBundle;
import org.geojson.mocks.MockParcel;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CrsTest {

    @Test
    public void testReadWriteToParcel() throws Exception {
        Crs crs = new Crs();
        Parcel parcel = MockParcel.obtain();
        crs.setProperties(MockBundle.newBundle());
        crs.writeToParcel(parcel, 0);

        Crs crsFromParcel = Crs.CREATOR.createFromParcel(parcel);

        assertEquals(crs.getType(), crsFromParcel.getType());
        assertEquals(crs.getProperties().size(), crsFromParcel.getProperties().size());
    }

    @Test
    public void testReadWriteToParcelComplex() throws Exception {

        final Bundle properties = MockBundle.newBundle();
        properties.putString("String", "StringValue");
        Crs crs = new Crs();
        crs.setProperties(properties);

        Parcel parcel = MockParcel.obtain();
        crs.writeToParcel(parcel, 0);

        Crs crsFromParcel = Crs.CREATOR.createFromParcel(parcel);

        assertEquals(crs.getType(), crsFromParcel.getType());
        assertEquals(crs.getProperties().size(), crsFromParcel.getProperties().size());
        assertEquals(crs.getProperties().getString("String"), crs.getProperties().getString("String"));
    }

    @Test
    public void testReadWriteToParcelMultipleType() throws Exception {

        final Bundle properties = MockBundle.newBundle();
        properties.putString("String", "StringValue");
        properties.putInt("Integer", 1);
        properties.putDouble("Double", 2d);
        properties.putLong("Long", 3l);
        properties.putFloat("Float", 4f);
        Crs crs = new Crs();
        crs.setProperties(properties);

        Parcel parcel = MockParcel.obtain();
        crs.writeToParcel(parcel, 0);

        Crs crsFromParcel = Crs.CREATOR.createFromParcel(parcel);

        assertEquals(crs.getType(), crsFromParcel.getType());
        assertEquals(crs.getProperties().size(), crsFromParcel.getProperties().size());
        assertEquals(crs.getProperties().getString("String"), crs.getProperties().getString("String"));
        assertEquals(crs.getProperties().getInt("Integer"), crs.getProperties().getInt("Integer"));
        assertEquals(crs.getProperties().getDouble("Double"), crs.getProperties().getDouble("Double"), 0);
        assertEquals(crs.getProperties().getFloat("Float"), crs.getProperties().getFloat("Float"), 0);
    }
}