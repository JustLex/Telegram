package org.telegram.messenger.volley;

import junit.framework.TestCase;

import org.telegram.messenger.volley.NetworkResponse;
import org.telegram.messenger.volley.VolleyError;

/**
 * Created by user on 27.03.2016.
 */
public class VolleyErrorTest extends TestCase {
    final byte[] dataIn = {1, 2, 3};
    final NetworkResponse responceIn = new NetworkResponse(dataIn);
    final String exeptionMessageIn = "wrong";
    final Throwable throwableIn = new Throwable("throwable");
    final long networktimeIn = 100;

    public void testDefaultConstruct() throws Exception {
        VolleyError error = new VolleyError();
        assertEquals(0, error.getNetworkTimeMs());
        assertEquals(null, error.networkResponse);
    }

    public void testResponceConstructor() throws Exception {
        VolleyError error = new VolleyError(responceIn);
        assertEquals(0, error.getNetworkTimeMs());
        assertEquals(responceIn, error.networkResponse);
    }

    public void testMessageConstructor() throws Exception {
        VolleyError error = new VolleyError(exeptionMessageIn);
        assertEquals(0, error.getNetworkTimeMs());
        assertEquals(exeptionMessageIn, error.getMessage());
        assertEquals(null, error.networkResponse);
    }

    public void testMessageAndReasonConstructor() throws Exception {
        VolleyError error = new VolleyError(exeptionMessageIn, throwableIn);
        assertEquals(0, error.getNetworkTimeMs());
        assertEquals(exeptionMessageIn, error.getMessage());
        assertEquals(null, error.networkResponse);
    }

    public void testThrowableConstructor() throws Exception {
        VolleyError error = new VolleyError(throwableIn);
        assertEquals(0, error.getNetworkTimeMs());
        assertEquals(throwableIn, error.getCause());
        assertEquals(null, error.networkResponse);
    }

    public void testGetNetworkTimeMs() throws Exception {
        VolleyError error = new VolleyError();
        assertEquals(0, error.getNetworkTimeMs());
    }
    
    public void testSetNetworkTimeMs() throws Exception {
        VolleyError error = new VolleyError();
        error.setNetworkTimeMs(networktimeIn);
        assertEquals(networktimeIn, error.getNetworkTimeMs());
    }

}