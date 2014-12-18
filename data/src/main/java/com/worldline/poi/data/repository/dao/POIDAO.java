package com.worldline.poi.data.repository.dao;

import com.worldline.poi.data.bean.vo.POIVO;

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
        void onPOIListLoaded(Collection<POIVO> poiVOsCollection);
        void onError(Exception exception);
    }

    /**
     * Callback used for clients to be notified when either poi data has been retrieved successfully
     * or any error occurred.
     */
    public interface POIDetailCallback {
        void onPOILoaded(int id, POIVO poi);
        void onError(Exception exception);
    }

    /**
     * Get a list of {@link com.worldline.poi.data.bean.vo.POIVO}.
     *
     * @param poiListCallback A {@link POIDAO.POIListCallback} to notify clients.
     */
    public void getPOIList(POIListCallback poiListCallback);

    /**
     * Save a list of {@link com.worldline.poi.data.bean.vo.POIVO}.
     *
     * @param poiVOsCollection The collection of {@link com.worldline.poi.data.bean.vo.POIVO} to save.
     */
    public void savePOIList(Collection<POIVO> poiVOsCollection);

    /**
     * Get the details of a {@link com.worldline.poi.data.bean.vo.POIVO} by its id.
     *
     * @param id The identifier of the desired POI data.
     * @param poiDetailCallback A {@link POIDAO.POIDetailCallback} to notify clients.
     */
    public void getPOIDetail(int id, POIDetailCallback poiDetailCallback);

    /**
     * Save the details of a {@link com.worldline.poi.data.bean.vo.POIVO} entity.
     *
     * @param poiVO            The entity {@link com.worldline.poi.data.bean.vo.POIVO} to save.
     */
    public void savePOIDetail(POIVO poiVO);
}
