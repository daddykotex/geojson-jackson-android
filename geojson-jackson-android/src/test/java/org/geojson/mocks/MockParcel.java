package org.geojson.mocks;

import android.os.Bundle;
import android.os.Parcel;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Stack;

import static org.mockito.Matchers.any;
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
    Stack<Object> objects;

    public Parcel getMockedParcel() {
        return mockedParcel;
    }

    public MockParcel() {
        mockedParcel = mock(Parcel.class);
        objects = new Stack<>();
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
                objects.push(parameter);
                return null;
            }
        };
        doAnswer(writeValueAnswer).when(mockedParcel).writeLong(anyLong());
        doAnswer(writeValueAnswer).when(mockedParcel).writeString(anyString());
        doAnswer(writeValueAnswer).when(mockedParcel).writeBundle(any(Bundle.class));
    }

    private void setupReads() {
        when(mockedParcel.readLong()).thenAnswer(new Answer<Long>() {
            @Override
            public Long answer(InvocationOnMock invocation) throws Throwable {
                return (Long) objects.pop();
            }
        });
        when(mockedParcel.readString()).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return (String) objects.pop();
            }
        });
        when(mockedParcel.readBundle()).thenAnswer(new Answer<Bundle>() {
            @Override
            public Bundle answer(InvocationOnMock invocation) throws Throwable {
                return (Bundle) objects.pop();
            }
        });
    }
} 