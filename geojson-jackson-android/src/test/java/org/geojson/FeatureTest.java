package org.geojson;

import android.os.Parcel;

import org.geojson.mocks.MockParcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FeatureTest {

    @Test
    public void testReadWriteToParcel() throws Exception {
        Feature feature = new Feature();
        Parcel parcel = MockParcel.obtain();
        feature.writeToParcel(parcel, 0);

        Feature featureFromParcel = (Feature) GeoJsonObject.CREATOR.createFromParcel(parcel);

        assertEquals(feature.getProperties().size(), featureFromParcel.getProperties().size());
        assertEquals(feature.getId(), featureFromParcel.getId());
    }

    @Test
    public void testReadWriteToParcelPoint() throws Exception {
        Feature feature = new Feature();
        Point point = new Point();
        Coordinate coord = new Coordinate();

        coord.setLatitude(12.00000d);
        coord.setLongitude(22.00000d);

        point.setCoordinates(coord);

        feature.setGeometry(point);
        feature.setBbox(new double[]{1.2d, 1.2d});

        Parcel parcel = MockParcel.obtain();
        feature.writeToParcel(parcel, 0);

        Feature featureFromParcel = (Feature) GeoJsonObject.CREATOR.createFromParcel(parcel);
        assertEquals(feature.getProperties().size(), featureFromParcel.getProperties().size());
        assertEquals(feature.getId(), featureFromParcel.getId());
        assertEquals(feature.getGeometry(), featureFromParcel.getGeometry());
        assertEquals(feature.getBbox()[0], featureFromParcel.getBbox()[0], 0);
        assertEquals(feature.getBbox()[1], featureFromParcel.getBbox()[1], 0);
    }

    @Test
    public void testReadWriteToParcelPolygon() throws Exception {
        Feature feature = new Feature();
        Polygon polygon = new Polygon();

        List<Coordinate> points = new ArrayList<>();
        points.add(new Coordinate(12.2d, 22.2d));
        points.add(new Coordinate(12.2d, 22.2d));

        polygon.setExteriorRing(points);
        feature.setGeometry(polygon);

        Parcel parcel = MockParcel.obtain();
        feature.writeToParcel(parcel, 0);

        Feature featureFromParcel = (Feature) GeoJsonObject.CREATOR.createFromParcel(parcel);
        assertEquals(feature.getProperties().size(), featureFromParcel.getProperties().size());
        assertEquals(feature.getId(), featureFromParcel.getId());
        assertEquals(feature.getGeometry(), featureFromParcel.getGeometry());
    }

    @Test
    public void testReadWriteToParcelMultiLine() throws Exception {
        Feature feature = new Feature();

        List<Coordinate> points = new ArrayList<>();
        points.add(new Coordinate(12.2d, 22.2d));
        points.add(new Coordinate(12.2d, 22.2d));

        MultiLineString multiLineString = new MultiLineString(points);
        feature.setGeometry(multiLineString);

        Parcel parcel = MockParcel.obtain();
        feature.writeToParcel(parcel, 0);

        Feature featureFromParcel = (Feature) GeoJsonObject.CREATOR.createFromParcel(parcel);
        assertEquals(feature.getProperties().size(), featureFromParcel.getProperties().size());
        assertEquals(feature.getId(), featureFromParcel.getId());
        assertEquals(feature.getGeometry(), featureFromParcel.getGeometry());
    }

    @Test
    public void testReadWriteToParcelLine() throws Exception {
        Feature feature = new Feature();

        LineString lineString = new LineString(new Coordinate(12.2d, 22.2d), new Coordinate(12.2d, 22.3d));
        feature.setGeometry(lineString);

        Parcel parcel = MockParcel.obtain();
        feature.writeToParcel(parcel, 0);

        Feature featureFromParcel = (Feature) GeoJsonObject.CREATOR.createFromParcel(parcel);
        assertEquals(feature.getProperties().size(), featureFromParcel.getProperties().size());
        assertEquals(feature.getId(), featureFromParcel.getId());
        assertEquals(feature.getGeometry(), featureFromParcel.getGeometry());
    }

    @Test
    public void testReadWriteToParcelMultiPoint() throws Exception {
        Feature feature = new Feature();

        MultiPoint multiPoint = new MultiPoint(new Coordinate(12.2d, 22.2d), new Coordinate(12.2d, 22.3d));
        feature.setGeometry(multiPoint);

        Parcel parcel = MockParcel.obtain();
        feature.writeToParcel(parcel, 0);

        Feature featureFromParcel = (Feature) GeoJsonObject.CREATOR.createFromParcel(parcel);
        assertEquals(feature.getProperties().size(), featureFromParcel.getProperties().size());
        assertEquals(feature.getId(), featureFromParcel.getId());
        assertEquals(feature.getGeometry(), featureFromParcel.getGeometry());
    }

    @Test
    public void testReadWriteToParcelMultiPolygon() throws Exception {
        Feature feature = new Feature();

        Polygon polygon = new Polygon(new Coordinate(12.2d, 22.2d), new Coordinate(12.2d, 22.3d));
        Polygon polygon2 = new Polygon(new Coordinate(12.2d, 22.2d), new Coordinate(12.2d, 22.3d));

        MultiPolygon multiPolygon = new MultiPolygon(polygon, polygon2);

        feature.setGeometry(multiPolygon);

        Parcel parcel = MockParcel.obtain();
        feature.writeToParcel(parcel, 0);

        Feature featureFromParcel = (Feature) GeoJsonObject.CREATOR.createFromParcel(parcel);
        assertEquals(feature.getProperties().size(), featureFromParcel.getProperties().size());
        assertEquals(feature.getId(), featureFromParcel.getId());
        assertEquals(feature.getGeometry(), featureFromParcel.getGeometry());
    }

    @Test
    public void testReadWriteToParcelMultiGeo() throws Exception {
        Feature feature = new Feature();

        Polygon polygon = new Polygon(new Coordinate(12.2d, 22.2d), new Coordinate(12.2d, 22.3d));
        Polygon polygon2 = new Polygon(new Coordinate(12.2d, 22.2d), new Coordinate(12.2d, 22.3d));

        MultiPolygon multiPolygon = new MultiPolygon(polygon, polygon2);
        MultiPoint multiPoint = new MultiPoint(new Coordinate(12.2d, 22.2d), new Coordinate(12.2d, 22.3d));

        GeometryCollection geometryCollection = new GeometryCollection(multiPoint, multiPolygon);
        feature.setGeometry(geometryCollection);

        Parcel parcel = MockParcel.obtain();
        feature.writeToParcel(parcel, 0);

        Feature featureFromParcel = (Feature) GeoJsonObject.CREATOR.createFromParcel(parcel);
        assertEquals(feature.getProperties().size(), featureFromParcel.getProperties().size());
        assertEquals(feature.getId(), featureFromParcel.getId());
        assertEquals(feature.getGeometry(), featureFromParcel.getGeometry());
    }
}