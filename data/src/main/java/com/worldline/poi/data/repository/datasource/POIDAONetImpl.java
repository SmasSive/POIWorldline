package com.worldline.poi.data.repository.datasource;

/**
 * Created by smassive on 11/23/14.
 */
public class POIDAONetImpl implements POIDAO {
    /**
     * Get a list of {@link com.worldline.poi.data.entity.POIEntity}.
     *
     * @param poiListCallback A {@link com.worldline.poi.data.repository.datasource.POIDAO.POIListCallback}
     *                        to notify clients.
     */
    @Override
    public void getPOIEntityList(POIListCallback poiListCallback) {

    }

    /**
     * Get the details of a {@link com.worldline.poi.data.entity.POIEntity} by its id.
     *
     * @param id                The identifier of the desired POI data.
     * @param poiDetailCallback A {@link com.worldline.poi.data.repository.datasource.POIDAO.POIDetailCallback}
     */
    @Override
    public void getPOIDetail(int id, POIDetailCallback poiDetailCallback) {

    }
}
