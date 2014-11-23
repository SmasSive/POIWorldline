package com.worldline.poi.data.repository.datasource;

import android.content.Context;

import com.worldline.poi.data.entity.POIEntity;
import com.worldline.poi.data.exception.POINotFoundException;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Implementation of {@link com.worldline.poi.data.repository.datasource.POIDAO} for retrieving POI
 * locally.
 *
 * Created by smassive on 11/23/14.
 */
public class POIDAOLocalImpl implements POIDAO {

    private final Context context;
    private final Realm realm;

    public POIDAOLocalImpl(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!!!");
        }
        this.context = context;
        this.realm = Realm.getInstance(context);
    }

    /**
     * Get a list of {@link com.worldline.poi.data.entity.POIEntity}.
     *
     * @param poiListCallback A {@link com.worldline.poi.data.repository.datasource.POIDAO.POIListCallback}
     *                        to notify clients.
     */
    @Override
    public void getPOIEntityList(POIListCallback poiListCallback) {
        if (poiListCallback == null) {
            throw new IllegalArgumentException("Callback cannot be null!!!");
        }

        RealmResults<POIEntity> poiEntities = realm.allObjects(POIEntity.class);

        if (poiEntities != null) {
            poiListCallback.onPOIListLoaded(poiEntities);
        } else {
            poiListCallback.onError(new NullPointerException("Collection of POIs null"));
        }
    }

    /**
     * Get the details of a {@link com.worldline.poi.data.entity.POIEntity} by its id.
     *
     * @param id                The identifier of the desired POI data.
     * @param poiDetailCallback A {@link com.worldline.poi.data.repository.datasource.POIDAO.POIDetailCallback}
     */
    @Override
    public void getPOIDetail(int id, POIDetailCallback poiDetailCallback) {
        if (poiDetailCallback == null) {
            throw new IllegalArgumentException("Callback cannot be null!!!");
        }

        RealmQuery<POIEntity> query = realm.where(POIEntity.class);
        query.equalTo("id", id);
        POIEntity poiEntity = query.findFirst();

        if (poiEntity != null) {
            poiDetailCallback.onPOILoaded(poiEntity);
        } else {
            poiDetailCallback.onError(new POINotFoundException());
        }
    }
}
