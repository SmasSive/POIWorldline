package com.worldline.poi.data.repository;

import com.worldline.poi.data.entity.POIEntity;
import com.worldline.poi.data.entity.mapper.POIEntityDataMapper;
import com.worldline.poi.data.repository.dao.POIDAO;
import com.worldline.poi.data.repository.dao.POIDAOFactory;
import com.worldline.poi.domain.repository.POIRepository;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class that implements {@link com.worldline.poi.domain.repository.POIRepository} for retrieving
 * data
 *
 * Created by smassive on 11/23/14.
 */
@Singleton
public class POIRepositoryImpl implements POIRepository {

    @Inject private POIDAOFactory poiDAOFactory;
    @Inject private POIEntityDataMapper poiEntityDataMapper;

    /**
     * Get a collection of {@link com.worldline.poi.domain.bo.POIBO}.
     *
     * @param poiListCallback A {@link com.worldline.poi.domain.repository.POIRepository.POIListCallback}
     *                        to notify clients.
     */
    @Override
    public void getPOIList(final POIListCallback poiListCallback) {
        // First, try to get objects from local DB
        POIDAO localDAO = poiDAOFactory.createLocalDAO();
        localDAO.getPOIEntityList(new POIDAO.POIListCallback() {
            @Override
            public void onPOIListLoaded(Collection<POIEntity> poisCollection) {
                // If we didn't find results, let's try in the net
                if (poisCollection == null || poisCollection.size() == 0) {
                    POIDAO netDAO = poiDAOFactory.createNetDAO();
                    netDAO.getPOIEntityList(new POIDAO.POIListCallback() {
                        @Override
                        public void onPOIListLoaded(Collection<POIEntity> poisCollection) {
                            poiListCallback.onPOIListLoaded(poiEntityDataMapper.transform(poisCollection));

                            // TODO Save to DB
                        }

                        @Override
                        public void onError(Exception exception) {
                            poiListCallback.onError(exception);
                        }
                    });
                } else {
                    poiListCallback.onPOIListLoaded(poiEntityDataMapper.transform(poisCollection));
                }
            }

            @Override
            public void onError(Exception exception) {
                poiListCallback.onError(exception);
            }
        });
    }

    /**
     * Get a {@link com.worldline.poi.domain.bo.POIBO} by its identifier.
     *
     * @param id                The identifier of the desired POI.
     * @param poiDetailCallback A {@link com.worldline.poi.domain.repository.POIRepository.POIDetailCallback}
     */
    @Override
    public void getPOIDetail(final int id, final POIDetailCallback poiDetailCallback) {
        POIDAO localDAO = poiDAOFactory.createLocalDAO();
        localDAO.getPOIDetail(id, new POIDAO.POIDetailCallback() {
            @Override
            public void onPOILoaded(POIEntity poi) {
                if (poi == null) {
                    POIDAO netDAO = poiDAOFactory.createNetDAO();
                    netDAO.getPOIDetail(id, new POIDAO.POIDetailCallback() {
                        @Override
                        public void onPOILoaded(POIEntity poi) {
                            poiDetailCallback.onPOIDetailLoaded(poiEntityDataMapper.transform(poi));
                        }

                        @Override
                        public void onError(Exception exception) {
                            poiDetailCallback.onError(exception);
                        }
                    });
                } else {
                    poiDetailCallback.onPOIDetailLoaded(poiEntityDataMapper.transform(poi));
                }
            }

            @Override
            public void onError(Exception exception) {
                poiDetailCallback.onError(exception);
            }
        });
    }
}
