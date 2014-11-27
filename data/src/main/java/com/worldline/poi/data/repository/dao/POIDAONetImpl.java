package com.worldline.poi.data.repository.dao;

import com.worldline.poi.data.entity.POIEntity;
import com.worldline.poi.data.net.POIService;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Implementation of {@link com.worldline.poi.data.repository.dao.POIDAO} for retrieving POI
 * from the net.
 *
 * Created by smassive on 11/23/14.
 */
public class POIDAONetImpl implements POIDAO {

    @Inject private POIService poiService;

    /**
     * Get a list of {@link com.worldline.poi.data.entity.POIEntity}.
     *
     * @param poiListCallback A {@link com.worldline.poi.data.repository.dao.POIDAO.POIListCallback}
     *                        to notify clients.
     */
    @Override
    public void getPOIEntityList(final POIListCallback poiListCallback) {
        poiService.getPOIList(new POIService.POIListCallback() {
            @Override
            public void onPOIListLoaded(Collection<POIEntity> poisCollection) {
                poiListCallback.onPOIListLoaded(poisCollection);
            }

            @Override
            public void onError(Exception exception) {
                poiListCallback.onError(exception);
            }
        });
    }

    /**
     * Get the details of a {@link com.worldline.poi.data.entity.POIEntity} by its id.
     *
     * @param id                The identifier of the desired POI data.
     * @param poiDetailCallback A {@link com.worldline.poi.data.repository.dao.POIDAO.POIDetailCallback}
     */
    @Override
    public void getPOIDetail(int id, final POIDetailCallback poiDetailCallback) {
        poiService.getPOIById(id, new POIService.POIDetailCallback() {
            @Override
            public void onPOIDetailLoaded(POIEntity poi) {
                poiDetailCallback.onPOILoaded(poi);
            }

            @Override
            public void onError(Exception exception) {
                poiDetailCallback.onError(exception);
            }
        });
    }
}
