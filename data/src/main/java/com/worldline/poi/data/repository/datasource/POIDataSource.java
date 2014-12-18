package com.worldline.poi.data.repository.datasource;

import com.worldline.poi.data.bean.vo.POIVO;
import com.worldline.poi.data.bean.vo.mapper.POIVODataMapper;
import com.worldline.poi.data.exception.POIListEmptyException;
import com.worldline.poi.data.exception.POINotFoundException;
import com.worldline.poi.data.repository.dao.POIDAO;
import com.worldline.poi.data.repository.dao.POIDAOFactory;
import com.worldline.poi.domain.bo.POIBO;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by smassive on 11/30/14.
 */
public class POIDataSource implements POIDAO.POIListCallback, POIDAO.POIDetailCallback {

    public interface Callback {
        void onPoiListLoaded(Collection<POIBO> poiBOsCollection);
        void onPoiLoaded(POIBO poiBO);
        void onError(Exception e);
    }

    @Inject
    POIDAOFactory poiDAOFactory;
    private Callback callback;
    private POIDAO poiDAO;
    private boolean local = true;

    public void getPOIList() {
        local = true;
        poiDAO = poiDAOFactory.createLocalDAO();
        poiDAO.getPOIList(this);
    }

    public void getPOI(int id) {
        local = true;
        poiDAO = poiDAOFactory.createLocalDAO();
        poiDAO.getPOIDetail(id, this);
    }

    @Override
    public void onPOILoaded(int id, POIVO poiVO) {
        if (poiVO == null && local) {
            local = false;
            poiDAO = poiDAOFactory.createNetDAO();
            poiDAO.getPOIDetail(id, this);
        } else {
            if (poiVO == null && !local) {
                onError(new POINotFoundException());
                return;
            }

            if (!local) {
                poiDAO = poiDAOFactory.createLocalDAO();
            }
            poiDAO.savePOIDetail(poiVO);

            POIVODataMapper mapper = new POIVODataMapper();
            POIBO poiBO = mapper.transform(poiVO);
            if (callback != null) {
                callback.onPoiLoaded(poiBO);
            }
        }
    }

    @Override
    public void onPOIListLoaded(Collection<POIVO> poiVOsCollection) {
        if ((poiVOsCollection == null || poiVOsCollection.size() < 1) && local) {
            local = false;
            poiDAO = poiDAOFactory.createNetDAO();
            poiDAO.getPOIList(this);
        } else {
            if ((poiVOsCollection == null || poiVOsCollection.size() < 1) && !local) {
                onError(new POIListEmptyException());
                return;
            }

            if (!local) {
                poiDAO = poiDAOFactory.createLocalDAO();
            }
            poiDAO.savePOIList(poiVOsCollection);

            POIVODataMapper mapper = new POIVODataMapper();
            Collection<POIBO> poiBOsCollection = mapper.transform(poiVOsCollection);
            if (callback != null) {
                callback.onPoiListLoaded(poiBOsCollection);
            }
        }
    }

    @Override
    public void onError(Exception exception) {
        if (callback != null) {
            callback.onError(exception);
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
