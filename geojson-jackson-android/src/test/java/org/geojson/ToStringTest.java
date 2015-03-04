package org.geojson;

import android.os.Bundle;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ToStringTest {

    final Bundle mockedBundle = mock(Bundle.class);

    @Before
    public void setUp() throws Exception {
        when(mockedBundle.toString()).thenReturn("{}");
    }

    @Test
	public void itShouldToStringCrs() throws Exception {
        Crs crs = new Crs();
        crs.setProperties(mockedBundle);
		assertEquals("Crs{type='name', properties={}}", crs.toString());
	}

	@Test
	public void itShouldToStringFeature() throws Exception {
		assertEquals("Feature{geometry=null, id='null'}", new Feature().toString());
	}

	@Test
	public void itShouldToStringFeatureCollection() throws Exception {
		assertEquals("FeatureCollection{features=[]}", new FeatureCollection().toString());
	}

	@Test
	public void itShouldToStringPoint() throws Exception {
		Point geometry = new Point(10, 20);
		assertEquals(
				"Point{coordinates=LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}} GeoJsonObject{properties={}}",
				geometry.toString());
	}

	@Test
	public void itShouldToStringPolygon() throws Exception {
		Polygon geometry = new Polygon(new LngLatAlt(10, 20), new LngLatAlt(30, 40), new LngLatAlt(10, 40),
				new LngLatAlt(10, 20));
		assertEquals(
				"Polygon{} Geometry{coordinates=[[LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}, "
						+ "LngLatAlt{longitude=30.0, latitude=40.0, altitude=NaN}, LngLatAlt{longitude=10.0, latitude=40.0, altitude=NaN}, "
						+ "LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}]]} GeoJsonObject{properties={}}",
				geometry.toString());
	}

	@Test
	public void itShouldToStringMultiPolygon() throws Exception {
		MultiPolygon geometry = new MultiPolygon(new Polygon(new LngLatAlt(10, 20), new LngLatAlt(30, 40),
				new LngLatAlt(10, 40), new LngLatAlt(10, 20)));
		geometry.add(new Polygon(new LngLatAlt(5, 20), new LngLatAlt(30, 40), new LngLatAlt(10, 40), new LngLatAlt(5,
				20)));
		assertEquals("MultiPolygon{} Geometry{coordinates=[[[LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}, "
				+ "LngLatAlt{longitude=30.0, latitude=40.0, altitude=NaN}, "
				+ "LngLatAlt{longitude=10.0, latitude=40.0, altitude=NaN}, "
				+ "LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}]], "
				+ "[[LngLatAlt{longitude=5.0, latitude=20.0, altitude=NaN}, "
				+ "LngLatAlt{longitude=30.0, latitude=40.0, altitude=NaN}, "
				+ "LngLatAlt{longitude=10.0, latitude=40.0, altitude=NaN}, "
				+ "LngLatAlt{longitude=5.0, latitude=20.0, altitude=NaN}]]]} GeoJsonObject{properties={}}",
				geometry.toString());
	}

	@Test
	public void itShouldToStringLineString() throws Exception {
		LineString geometry = new LineString(new LngLatAlt(49, 9), new LngLatAlt(41, 1));
		assertEquals("LineString{} MultiPoint{} Geometry{coordinates=["
				+ "LngLatAlt{longitude=49.0, latitude=9.0, altitude=NaN}, "
				+ "LngLatAlt{longitude=41.0, latitude=1.0, altitude=NaN}]} GeoJsonObject{properties={}}",
				geometry.toString());
	}

	@Test
	public void itShouldToStringMultiLineString() throws Exception {
		MultiLineString geometry = new MultiLineString(Arrays.asList(new LngLatAlt(49, 9), new LngLatAlt(41, 1)));
		geometry.add(Arrays.asList(new LngLatAlt(10, 20), new LngLatAlt(30, 40)));
		assertEquals("MultiLineString{} Geometry{coordinates=[[LngLatAlt{longitude=49.0, latitude=9.0, altitude=NaN}, "
				+ "LngLatAlt{longitude=41.0, latitude=1.0, altitude=NaN}], "
				+ "[LngLatAlt{longitude=10.0, latitude=20.0, altitude=NaN}, "
				+ "LngLatAlt{longitude=30.0, latitude=40.0, altitude=NaN}]]} GeoJsonObject{properties={}}",
				geometry.toString());
	}
}
