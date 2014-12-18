package com.worldline.poi.data.exception;

/**
 * Exception thrown by the application when a POI search can't return a valid result.
 *
 * Created by smassive on 11/23/14.
 */
public class POIListEmptyException extends Exception {

    public POIListEmptyException() {
        super();
    }

    public POIListEmptyException(final String message) {
        super(message);
    }

    public POIListEmptyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public POIListEmptyException(final Throwable cause) {
        super(cause);
    }
}
