package com.worldline.poi.data.repository.datasource;

import com.worldline.poi.data.entity.POIEntity;
import com.worldline.poi.data.net.POIService;

import java.util.Collection;

/**
 * Implementation of {@link com.worldline.poi.data.repository.datasource.POIDAO} for retrieving POI
 * from the net.
 *
 * Created by smassive on 11/23/14.
 */
public class POIDAONetImpl implements POIDAO {

    private POIService poiService;

    public POIDAONetImpl(POIService poiService) {
        if (poiService == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!!!");
        }
        this.poiService = poiService;
    }

    /**
     * Get a list of {@link com.worldline.poi.data.entity.POIEntity}.
     *
     * @param poiListCallback A {@link com.worldline.poi.data.repository.datasource.POIDAO.POIListCallback}
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
     * @param poiDetailCallback A {@link com.worldline.poi.data.repository.datasource.POIDAO.POIDetailCallback}
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
