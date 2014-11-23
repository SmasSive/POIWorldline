package com.worldline.poi.domain.repository;

import com.worldline.poi.domain.bo.POIBO;

import java.util.Collection;

/**
 * Interface that represents a Repository for getting {@link com.worldline.poi.domain.bo.POIBO}.
 *
 * Created by smassive on 11/23/14.
 */
public interface POIRepository {

    /**
     * Callback used to be notified when either a POI list has been loaded or an error happened.
     */
    public interface POIListCallback {
        public void onPOIListLoaded(Collection<POIBO> poiBOsCollection);
        public void onError(Exception exception);
    }

    /**
     * Callback used to be notified when either a POI has been loaded or an error happened.
     */
    public interface POIDetailCallback {
        public void onPOIDetailLoaded(POIBO poiBO);
        public void onError(Exception exception);
    }

    /**
     * Get a collection of {@link com.worldline.poi.domain.bo.POIBO}.
     *
     * @param poiListCallback A {@link com.worldline.poi.domain.repository.POIRepository.POIListCallback}
     * to notify clients.
     */
    public void getPOIList(POIListCallback poiListCallback);

    /**
     * Get a {@link com.worldline.poi.domain.bo.POIBO} by its identifier.
     *
     * @param id The identifier of the desired POI.
     * @param poiDetailCallback A {@link com.worldline.poi.domain.repository.POIRepository.POIDetailCallback}
     * to notify clients.
     */
    public void getPOIDetail(int id, POIDetailCallback poiDetailCallback);
}
