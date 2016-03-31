package org.telegram.messenger.volley;

import junit.framework.TestCase;
import org.apache.http.HttpStatus;
import org.telegram.messenger.volley.NetworkResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 27.03.2016.
 */
public class NetworkResponseTest extends TestCase {
    private final byte[] arrayIn = {1, 2, 3};

    Map<String, String> headersIn = new HashMap<>();
    {
        headersIn.put("Accept", "text/plain");
        headersIn.put("Connection", "keep-alive");
    }

    int statusIn = HttpStatus.SC_FORBIDDEN;
    boolean modifiedIn = true;
    long networkTimeIn = 1;

    public void testByteConstructor() throws Exception {
        NetworkResponse response = new NetworkResponse(arrayIn);
        assertEquals(HttpStatus.SC_OK, response.statusCode);
        assertEquals(arrayIn, response.data);
        assertEquals(Collections.<String, String>emptyMap(), response.headers);
        assertEquals(0, response.networkTimeMs);
        assertEquals(false,response.notModified);
    }

    public void testByteHeadersConstructor() throws Exception {
        NetworkResponse response = new NetworkResponse(arrayIn, headersIn);
        assertEquals(HttpStatus.SC_OK, response.statusCode);
        assertEquals(arrayIn, response.data);
        assertEquals(headersIn, response.headers);
        assertEquals(0, response.networkTimeMs);
        assertEquals(false,response.notModified);
    }

    public void testStatusByteHeadersModifiedConstructor() throws Exception {
        NetworkResponse response = new NetworkResponse(statusIn, arrayIn, headersIn, modifiedIn);
        assertEquals(statusIn, response.statusCode);
        assertEquals(arrayIn, response.data);
        assertEquals(headersIn, response.headers);
        assertEquals(0, response.networkTimeMs);
        assertEquals(modifiedIn,response.notModified);
    }

    public void testStatusByteHeadersModifiedTimeMsConstructor() throws Exception {
        NetworkResponse response = new NetworkResponse(statusIn, arrayIn, headersIn, modifiedIn, networkTimeIn);
        assertEquals(statusIn, response.statusCode);
        assertEquals(arrayIn, response.data);
        assertEquals(headersIn, response.headers);
        assertEquals(networkTimeIn, response.networkTimeMs);
        assertEquals(modifiedIn,response.notModified);
    }
}