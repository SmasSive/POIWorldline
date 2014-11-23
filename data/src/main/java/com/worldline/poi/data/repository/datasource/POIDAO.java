package com.worldline.poi.data.repository.datasource;

import com.worldline.poi.data.entity.POIEntity;

import java.util.Collection;

/**
 * POI Data Access Object: Interface that represents a data store from where data is retrieved.
 * It should be implemented by the different types of data origin, in our case:
 * - From Internet
 * - From Database
 *
 * Created by smassive on 11/23/14.
 */
public interface POIDAO {

    /**
     * Callback used for clients to be notified when either a poi list has been loaded or any error
     * occurred.
     */
    public interface POIListCallback {
        void onPOIListLoaded(Collection<POIEntity> poisCollection);
        void onError(Exception exception);
    }

    /**
     * Callback used for clients to be notified when either poi data has been retrieved successfully
     * or any error occurred.
     */
    public interface  POIDetailCallback {
        void onPOILoaded(POIEntity poi);
        void onError(Exception exception);
    }

    /**
     * Get a list of {@link com.worldline.poi.data.entity.POIEntity}.
     *
     * @param poiListCallback A {@link POIDAO.POIListCallback}
     * to notify clients.
     */
    public void getPOIEntityList(POIListCallback poiListCallback);

    /**
     * Get the details of a {@link com.worldline.poi.data.entity.POIEntity} by its id.
     *
     * @param id The identifier of the desired POI data.
     * @param poiDetailCallback A {@link POIDAO.POIDetailCallback}
     * to notify clients.
     */
    public void getPOIDetail(int id, POIDetailCallback poiDetailCallback);
}
