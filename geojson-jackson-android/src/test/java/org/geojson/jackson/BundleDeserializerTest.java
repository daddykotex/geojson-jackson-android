package org.geojson.jackson;

import android.os.Bundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.geojson.mocks.MockBundle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BundleDeserializerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Bundle mockedBundle = MockBundle.newBundle();
    private BundleTestObject testObject;

    private static class BundleTestObject {

        private Bundle bundle = new Bundle();

        @JsonSerialize(using = BundleSerializer.class)
        public Bundle getBundle() {
            return bundle;
        }

        @JsonDeserialize(using = BundleDeserializer.class)
        public void setBundle(Bundle bundle) {
            this.bundle = bundle;
        }

    }

    @Before
    public void setUp() throws Exception {

        testObject = new BundleTestObject();
        testObject.setBundle(mockedBundle);
    }

    @Test
    public void testDeserialize() throws Exception {

        mockedBundle.putString("String", "Value");

        assertEquals("{\"bundle\":{\"String\":\"Value\"}}",
                mapper.writeValueAsString(testObject));

    }

    @Test
    public void testSerialize() throws Exception {

        mockedBundle.putString("String", "Value");

        final String json = "{\"bundle\":{\"String\":\"Value\"}}";

        BundleTestObject deserialized = mapper.readValue(json, BundleTestObject.class);

        assertEquals(deserialized.bundle.size(), 1);

    }

    @Test
    public void testDeserializeMultipleValues() throws Exception {

        mockedBundle.putString("String", "Value");
        mockedBundle.putString("String2", "Value2");
        mockedBundle.putInt("Integer", 999);
        mockedBundle.putFloat("Float", 15f);

        assertEquals("{\"bundle\":{" +
                        "\"Float\":15.0," +
                        "\"String\":\"Value\"," +
                        "\"String2\":\"Value2\"," +
                        "\"Integer\":999" +
                        "}}",
                mapper.writeValueAsString(testObject));

    }
}