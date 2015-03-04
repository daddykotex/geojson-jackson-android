package org.geojson.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.geojson.GeoJsonObject;
import org.geojson.GeoJsonObjectVisitor;
import org.geojson.mocks.MockBundle;
import org.junit.Test;

import static org.geojson.GeoJsonObject.ParcelId.test;
import static org.junit.Assert.assertEquals;

public class GeoJsonObjectTest {

    private ObjectMapper mapper = new ObjectMapper();

    private class TestGeoJsonObject extends GeoJsonObject {

        @Override
        public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
            throw new RuntimeException("not implemented");
        }

        @Override
        protected ParcelId getParcelId() {
            return test;
        }
    }

    @Test
    public void itShouldNotSerializeEmptyProperties() throws Exception {
        TestGeoJsonObject testObject = new TestGeoJsonObject();
        assertEquals("{\"type\":\"GeoJsonObjectTest$TestGeoJsonObject\"}", mapper.writeValueAsString(testObject));
    }

    @Test
    public void itShouldSerializePropertiesCorrectly() throws Exception {
        TestGeoJsonObject testObject = new TestGeoJsonObject();
        testObject.setProperties(MockBundle.newBundle());
        testObject.setProperty("String", "String");
        assertEquals("{\"type\":\"GeoJsonObjectTest$TestGeoJsonObject\",\"properties\":{\"String\":\"String\"}}",
                mapper.writeValueAsString(testObject));
    }

    @Test
    public void itShouldDeserializePropertiesCorrectly() throws Exception {
        TestGeoJsonObject testObject = new TestGeoJsonObject();
        testObject.setProperties(MockBundle.newBundle());
        testObject.setProperty("String", "String");
        assertEquals("{\"type\":\"GeoJsonObjectTest$TestGeoJsonObject\",\"properties\":{\"String\":\"String\"}}",
                mapper.writeValueAsString(testObject));
    }


}
