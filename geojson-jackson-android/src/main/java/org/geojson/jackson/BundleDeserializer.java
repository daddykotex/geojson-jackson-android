package org.geojson.jackson;

import android.os.Bundle;
import android.os.Parcelable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class BundleDeserializer extends JsonDeserializer<Bundle> {

    @Override
    public Bundle deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        if (jp.isExpectedStartObjectToken()) {
            final Bundle node = extractObjects(jp, ctxt);
            if (jp.hasCurrentToken() && jp.getCurrentToken() != JsonToken.END_ARRAY)
                jp.nextToken();
            return node;
        }
        throw ctxt.mappingException(Bundle.class);
    }

    private Bundle extractObjects(JsonParser jp, DeserializationContext ctxt)
            throws JsonParseException, IOException {
        Bundle bundle = new Bundle();
        JsonToken token;

        do {
            token = jp.nextToken();
            switch (token) {
                case VALUE_STRING:
                    bundle.putString(jp.getCurrentName(), jp.getValueAsString());
                    break;
                case VALUE_NUMBER_FLOAT:
                    bundle.putDouble(jp.getCurrentName(), jp.getDoubleValue());
                    break;
                case VALUE_NUMBER_INT:
                    bundle.putDouble(jp.getCurrentName(), jp.getValueAsInt());
                case VALUE_TRUE:
                case VALUE_FALSE:
                    bundle.putBoolean(jp.getCurrentName(), jp.getBooleanValue());
                    break;
                case VALUE_NULL:
                    break;
                default:
                    throw ctxt.mappingException("Unexpected token (" + token.name()
                            + ") when binding data into LngLatAlt ");

            }

        } while (token != null);
        return bundle;

    }
}
