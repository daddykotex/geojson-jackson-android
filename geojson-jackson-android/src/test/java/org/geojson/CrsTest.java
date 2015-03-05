package org.geojson;

import android.os.Parcel;

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
        crs.setProperties(new HashMap<String, Object>());
        crs.writeToParcel(parcel, 0);

        Crs crsFromParcel = Crs.CREATOR.createFromParcel(parcel);

        assertEquals(crs.getType(), crsFromParcel.getType());
        assertEquals(crs.getProperties().size(), crsFromParcel.getProperties().size());
    }

    @Test
    public void testReadWriteToParcelComplex() throws Exception {

        final Map<String, Object> properties = new HashMap<>();
        properties.put("String", "StringValue");
        Crs crs = new Crs();
        crs.setProperties(properties);

        Parcel parcel = MockParcel.obtain();
        crs.writeToParcel(parcel, 0);

        Crs crsFromParcel = Crs.CREATOR.createFromParcel(parcel);

        assertEquals(crs.getType(), crsFromParcel.getType());
        assertEquals(crs.getProperties().size(), crsFromParcel.getProperties().size());
        assertEquals(crs.getProperties().get("String"), crs.getProperties().get("String"));
    }

    @Test
    public void testReadWriteToParcelMultipleType() throws Exception {

        final Map<String, Object> properties = new HashMap<>();
        properties.put("String", "StringValue");
        properties.put("Integer", 1);
        properties.put("Double", 2d);
        properties.put("Long", 3l);
        properties.put("Float", 4f);

        Crs crs = new Crs();
        crs.setProperties(properties);

        Parcel parcel = MockParcel.obtain();
        crs.writeToParcel(parcel, 0);

        Crs crsFromParcel = Crs.CREATOR.createFromParcel(parcel);

        assertEquals(crs.getType(), crsFromParcel.getType());
        assertEquals(crs.getProperties().size(), crsFromParcel.getProperties().size());
        assertEquals(crs.getProperties().get("String"), crs.getProperties().get("String"));
        assertEquals(crs.getProperties().get("Integer"), crs.getProperties().get("Integer"));
        assertEquals(crs.getProperties().get("Double"), crs.getProperties().get("Double"));
        assertEquals(crs.getProperties().get("Float"), crs.getProperties().get("Float"));
    }
}