package com.worldline.poi.data.repository;

import com.worldline.poi.data.entity.POIEntity;
import com.worldline.poi.data.entity.mapper.POIEntityDataMapper;
import com.worldline.poi.data.repository.datasource.POIDAO;
import com.worldline.poi.data.repository.datasource.POIDAOFactory;
import com.worldline.poi.domain.repository.POIRepository;

import java.util.Collection;

/**
 * Class that implements {@link com.worldline.poi.domain.repository.POIRepository} for retrieving
 * data
 *
 * Created by smassive on 11/23/14.
 */
public class POIRepositoryImpl implements POIRepository {

    private static POIRepositoryImpl INSTANCE;

    private final POIDAOFactory poiDAOFactory;
    private final POIEntityDataMapper poiEntityDataMapper;

    /**
     * Get unique instance of {@link com.worldline.poi.data.repository.POIRepositoryImpl}.
     *
     * @param poiDAOFactory A {@link com.worldline.poi.data.repository.datasource.POIDAOFactory} for
     * retrieving the desired POIDAO implementation.
     *
     * @return a unique instance of {@link com.worldline.poi.data.repository.POIRepositoryImpl}.
     */
    public static synchronized POIRepositoryImpl getInstance(POIDAOFactory poiDAOFactory, POIEntityDataMapper poiEntityDataMapper) {
        if (INSTANCE == null) {
            INSTANCE = new POIRepositoryImpl(poiDAOFactory, poiEntityDataMapper);
        }

        return INSTANCE;
    }

    /**
     * Constructs a {@link com.worldline.poi.data.repository.POIRepositoryImpl}.
     *
     * @param poiDAOFactory A {@link com.worldline.poi.data.repository.datasource.POIDAOFactory} for
     * retrieving the desired POIDAO implementation.
     */
    protected POIRepositoryImpl(POIDAOFactory poiDAOFactory, POIEntityDataMapper poiEntityDataMapper) {
        if (poiDAOFactory == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!!!");
        }

        this.poiDAOFactory = poiDAOFactory;
        this.poiEntityDataMapper = poiEntityDataMapper;
    }

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
