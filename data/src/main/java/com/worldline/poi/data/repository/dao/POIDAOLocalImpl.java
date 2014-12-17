package com.worldline.poi.data.repository.dao;

import android.content.Context;

import com.worldline.poi.data.bean.vo.POIVO;
import com.worldline.poi.data.bean.vo.utils.POIVOUtils;
import com.worldline.poi.data.exception.POINotFoundException;

import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Implementation of {@link com.worldline.poi.data.repository.dao.POIDAO} for retrieving POI
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
     * Get a list of {@link com.worldline.poi.data.bean.vo.POIVO}.
     *
     * @param poiListCallback A {@link com.worldline.poi.data.repository.dao.POIDAO.POIListCallback}
     *                        to notify clients.
     */
    @Override
    public void getPOIEntityList(POIListCallback poiListCallback) {
        if (poiListCallback == null) {
            throw new IllegalArgumentException("Callback cannot be null!!!");
        }

        RealmResults<POIVO> poiEntities = realm.allObjects(POIVO.class);

        if (poiEntities != null) {
            poiListCallback.onPOIListLoaded(poiEntities);
        } else {
            poiListCallback.onError(new NullPointerException("Collection of POIs null"));
        }
    }

    /**
     * Save a list of {@link com.worldline.poi.data.bean.vo.POIVO}.
     *
     * @param poiEntities The collection of {@link com.worldline.poi.data.bean.vo.POIVO} to save.
     */
    @Override
    public void savePOIEntityList(Collection<POIVO> poiEntities) {
        if (poiEntities == null) {
            throw new IllegalArgumentException("Entities cannot be null!!!");
        }

        realm.beginTransaction();
        for (POIVO entitySource : poiEntities) {
            POIVO entityTarget = realm.createObject(POIVO.class);
            POIVOUtils.copy(entitySource, entityTarget);
        }
        realm.commitTransaction();
    }

    /**
     * Get the details of a {@link com.worldline.poi.data.bean.vo.POIVO} by its id.
     *
     * @param id                The identifier of the desired POI data.
     * @param poiDetailCallback A {@link com.worldline.poi.data.repository.dao.POIDAO.POIDetailCallback}
     */
    @Override
    public void getPOIDetail(int id, POIDetailCallback poiDetailCallback) {
        if (poiDetailCallback == null) {
            throw new IllegalArgumentException("Callback cannot be null!!!");
        }

        RealmQuery<POIVO> query = realm.where(POIVO.class);
        query.equalTo("id", id);
        POIVO POIVO = query.findFirst();

        if (POIVO != null) {
            poiDetailCallback.onPOILoaded(POIVO);
        } else {
            poiDetailCallback.onError(new POINotFoundException());
        }
    }

    /**
     * Save the details of a {@link com.worldline.poi.data.bean.vo.POIVO} entity.
     *
     * @param entity            The entity {@link com.worldline.poi.data.bean.vo.POIVO} to save.
     */
    @Override
    public void savePOIDetail(POIVO entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null!!!");
        }

        realm.beginTransaction();
        POIVO newEntity = realm.createObject(POIVO.class);
        POIVOUtils.copy(entity, newEntity);
        realm.commitTransaction();
    }
}
