package org.telegram.messenger.volley;

import junit.framework.TestCase;

import static org.junit.Assert.*;

/**
 * Created by user on 27.03.2016.
 */
public class NetworkErrorTest extends TestCase {
    final Throwable throwableIn = new Throwable("throwable");
    final byte[] dataIn = {1, 2, 3};
    final NetworkResponse responceIn = new NetworkResponse(dataIn);

    public void testDefaultConstructor() throws Exception {
        NetworkError error = new NetworkError();
        assertEquals(0, error.getNetworkTimeMs());
        assertEquals(null, error.networkResponse);
    }

    public void testCauseConstructor() throws Exception {
        NetworkError netError = new NetworkError(throwableIn);

        assertEquals(0, netError.getNetworkTimeMs());
        assertEquals(throwableIn, netError.getCause());
        assertEquals(null, netError.networkResponse);
    }

    public void testResponceConstructor() throws Exception {
        NetworkError netError = new NetworkError(responceIn);

        assertEquals(0, netError.getNetworkTimeMs());
        assertEquals(responceIn, netError.networkResponse);
    }
}