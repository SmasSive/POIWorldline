package com.worldline.poi.data.repository;

import com.worldline.poi.data.repository.datasource.POIDataSource;
import com.worldline.poi.domain.bo.POIBO;
import com.worldline.poi.domain.repository.POIRepository;

import java.util.Collection;

import javax.inject.Singleton;

/**
 * Class that implements {@link com.worldline.poi.domain.repository.POIRepository} for retrieving
 * data
 *
 * Created by smassive on 11/23/14.
 */
@Singleton
public class POIRepositoryImpl implements POIRepository, POIDataSource.Callback {

    private POIDataSource poiDataSource;
    private POIRepository.Callback callback;

    public POIRepositoryImpl() {
        poiDataSource = new POIDataSource();
        poiDataSource.setCallback(this);
    }

    /**
     * Get a collection of {@link com.worldline.poi.domain.bo.POIBO}.
     */
    @Override
    public void getPOIList(Callback callback) {
        this.callback = callback;
        poiDataSource.getPOIList();
    }

    /**
     * Get a {@link com.worldline.poi.domain.bo.POIBO} by its identifier.
     *
     * @param id                The identifier of the desired POI.
     */
    @Override
    public void getPOIDetail(int id, Callback callback) {
        this.callback = callback;
        poiDataSource.getPOI(id);
    }

    @Override
    public void onPoiListLoaded(Collection<POIBO> poiBOsCollection) {
        if (callback != null) {
            callback.onPOIListLoaded(poiBOsCollection);
        }
    }

    @Override
    public void onPoiLoaded(POIBO poiBO) {
        if (callback != null) {
            callback.onPOILoaded(poiBO);
        }
    }

    @Override
    public void onError(Exception e) {
        if (callback != null) {
            callback.onError(e);
        }
    }
}
