package com.worldline.poi.data.net;

import com.worldline.poi.data.entity.POIEntity;

import java.util.Collection;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by smassive on 11/22/14.
 */
public interface POIService {

    public interface POIListCallback {
        public void onPOIListLoaded(Collection<POIEntity> poisCollection);
        public void onError(Exception exception);
    }

    public interface POIDetailCallback {
        public void onPOIDetailLoaded(POIEntity poi);
        public void onError(Exception exception);
    }

    /**
     * Get the list of POIs asynchronously.
     * @param POIListCallback {@link com.worldline.poi.data.net.POIService.POIListCallback} to be
     * nofified when POI list has been retrieved.
     */
    @GET("/points")
    public void getPOIList(POIListCallback POIListCallback);

    /**
     * Get the detail of a POI by id asynchronously.
     * @param poiId The POI id to get POI data.
     * @param POIDetailCallback {@link com.worldline.poi.data.net.POIService.POIDetailCallback} to
     * be notified when POI has been retrieved.
     */
    @GET("/points/{id}")
    public void getPOIById(@Path("id") int poiId, POIDetailCallback POIDetailCallback);
}
