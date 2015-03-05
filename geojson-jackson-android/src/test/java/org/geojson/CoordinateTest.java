package org.geojson;

import android.os.Parcel;

import org.geojson.mocks.MockParcel;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CoordinateTest {

    @Test
    public void should_LngLatAlt_equals_without_alt() {
        Coordinate first = new Coordinate(14.D, 13.D);
        Coordinate second = new Coordinate(14.D, 13.D);
        Assert.assertEquals(second, first);
    }

    @Test
    public void should_LngLatAlt_equals_with_alt() {
        Coordinate first = new Coordinate(14.D, 13.D, 15D);
        Coordinate second = new Coordinate(14.D, 13.D, 15D);
        Assert.assertEquals(second, first);
    }

    @Test
    public void should_not_LngLatAlt_equals_with_alt() {
        Coordinate first = new Coordinate(14.D, 13.D, 15D);
        Coordinate second = new Coordinate(14.D, 13.D, 16D);
        Assert.assertNotEquals(second, first);
    }

    @Test
    public void should_not_LngLatAlt_equals_without_alt() {
        Coordinate first = new Coordinate(14.D, 14.D, 15D);
        Coordinate second = new Coordinate(14.D, 13.D, 16D);
        Assert.assertNotEquals(second, first);
    }

    @Test
    public void testParcelWirteAndRead() throws Exception {
        Coordinate lngLatAlt = new Coordinate();

        Parcel parcel = MockParcel.obtain();
        parcel.writeParcelable(lngLatAlt, 0);

        Coordinate lngLatAltFromParcel = Coordinate.CREATOR.createFromParcel(parcel);

        assertEquals(lngLatAlt, lngLatAltFromParcel);

    }
}