package com.worldline.poi.data.exception;

/**
 * Exception thrown by the application when a POI search can't return a valid result.
 *
 * Created by smassive on 11/23/14.
 */
public class POINotFoundException extends Exception {

    public POINotFoundException() {
        super();
    }

    public POINotFoundException(final String message) {
        super(message);
    }

    public POINotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public POINotFoundException(final Throwable cause) {
        super(cause);
    }
}
