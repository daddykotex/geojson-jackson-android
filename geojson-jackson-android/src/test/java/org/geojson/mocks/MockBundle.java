package org.geojson.mocks;

import android.os.Bundle;
import android.os.Parcel;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockBundle {

    public static Bundle newBundle() {
        return new MockBundle().getMockedBundle();
    }

    Bundle mockedBundle;
    Map<String, Object> objects;

    public Bundle getMockedBundle() {
        return mockedBundle;
    }

    public MockBundle() {
        mockedBundle = mock(Bundle.class);
        objects = new HashMap<>();
        setupMock();
    }

    private void setupMock() {
        setupWrites();
        setupReads();
    }

    private void setupWrites() {
        Answer<Void> writeValueAnswer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArguments()[0].toString();
                Object value = invocation.getArguments()[1];
                objects.put(key, value);
                return null;
            }
        };
        doAnswer(writeValueAnswer).when(mockedBundle).putString(anyString(), anyString());
        doAnswer(writeValueAnswer).when(mockedBundle).putInt(anyString(), anyInt());
        doAnswer(writeValueAnswer).when(mockedBundle).putFloat(anyString(), anyFloat());
        doAnswer(writeValueAnswer).when(mockedBundle).putDouble(anyString(), anyDouble());
        doAnswer(writeValueAnswer).when(mockedBundle).putLong(anyString(), anyLong());
        doAnswer(writeValueAnswer).when(mockedBundle).putBoolean(anyString(), anyBoolean());
    }

    private void setupReads() {
        when(mockedBundle.getString(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArguments()[0].toString();
                return (String) objects.get(key);
            }
        });
        when(mockedBundle.getInt(anyString())).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArguments()[0].toString();
                return (Integer) objects.get(key);
            }
        });
        when(mockedBundle.getFloat(anyString())).thenAnswer(new Answer<Float>() {
            @Override
            public Float answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArguments()[0].toString();
                return (Float) objects.get(key);
            }
        });
        when(mockedBundle.getDouble(anyString())).thenAnswer(new Answer<Double>() {
            @Override
            public Double answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArguments()[0].toString();
                return (Double) objects.get(key);
            }
        });
        when(mockedBundle.getLong(anyString())).thenAnswer(new Answer<Long>() {
            @Override
            public Long answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArguments()[0].toString();
                return (Long) objects.get(key);
            }
        });
        when(mockedBundle.get(anyString())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArguments()[0].toString();
                return objects.get(key);
            }
        });
        when(mockedBundle.getLong(anyString())).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArguments()[0].toString();
                return (Boolean) objects.get(key);
            }
        });
        when(mockedBundle.size()).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return objects.size();
            }
        });
        when(mockedBundle.keySet()).thenAnswer(new Answer<Set<String>>() {
            @Override
            public Set<String> answer(InvocationOnMock invocation) throws Throwable {
                return objects.keySet();
            }
        });
    }
} 