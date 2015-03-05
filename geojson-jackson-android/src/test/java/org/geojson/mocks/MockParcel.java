package org.geojson.mocks;

import android.os.Parcel;
import android.os.Parcelable;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockParcel {

    public static Parcel obtain() {
        return new MockParcel().getMockedParcel();
    }

    Parcel mockedParcel;
    List<Object> objects;
    int position;

    public Parcel getMockedParcel() {
        return mockedParcel;
    }

    public MockParcel() {
        mockedParcel = mock(Parcel.class);
        objects = new ArrayList<>();
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
                Object parameter = invocation.getArguments()[0];
                objects.add(parameter);
                return null;
            }
        };
        doAnswer(writeValueAnswer).when(mockedParcel).writeLong(anyLong());
        doAnswer(writeValueAnswer).when(mockedParcel).writeParcelable(any(Parcelable.class), anyInt());
        doAnswer(writeValueAnswer).when(mockedParcel).writeString(anyString());
        doAnswer(writeValueAnswer).when(mockedParcel).writeMap(any(Map.class));
        doAnswer(writeValueAnswer).when(mockedParcel).writeDoubleArray(any(double[].class));
    }

    private void setupReads() {
        when(mockedParcel.readLong()).thenAnswer(new Answer<Long>() {
            @Override
            public Long answer(InvocationOnMock invocation) throws Throwable {
                final Long value = (Long) objects.get(position);
                position++;
                return value;
            }
        });
        when(mockedParcel.readString()).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                final String value = (String) objects.get(position);
                position++;
                return value;
            }
        });
        when(mockedParcel.readHashMap(any(ClassLoader.class))).thenAnswer(new Answer<Map<String, Object>>() {
            @Override
            @SuppressWarnings("unchecked")
            public Map<String, Object> answer(InvocationOnMock invocation) throws Throwable {
                final Map value = (Map) objects.get(position);
                position++;
                return value;
            }
        });
        when(mockedParcel.readParcelable(any(ClassLoader.class))).thenAnswer(new Answer<Parcelable>() {
            @Override
            public Parcelable answer(InvocationOnMock invocation) throws Throwable {
                final Parcelable value = (Parcelable) objects.get(position);
                position++;
                return value;
            }
        });
        when(mockedParcel.createDoubleArray()).thenAnswer(new Answer<double[]>() {
            @Override
            public double[] answer(InvocationOnMock invocation) throws Throwable {
                final double[] value = (double[]) objects.get(position);
                position++;
                return value;
            }
        });

    }
} 