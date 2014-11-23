package com.worldline.poi.data.repository;

import com.worldline.poi.data.entity.POIEntity;
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

    /**
     * Get unique instance of {@link com.worldline.poi.data.repository.POIRepositoryImpl}.
     *
     * @param poiDAOFactory A {@link com.worldline.poi.data.repository.datasource.POIDAOFactory} for
     * retrieving the desired POIDAO implementation.
     *
     * @return a unique instance of {@link com.worldline.poi.data.repository.POIRepositoryImpl}.
     */
    public static synchronized POIRepositoryImpl getInstance(POIDAOFactory poiDAOFactory) {
        if (INSTANCE == null) {
            INSTANCE = new POIRepositoryImpl(poiDAOFactory);
        }

        return INSTANCE;
    }

    /**
     * Constructs a {@link com.worldline.poi.data.repository.POIRepositoryImpl}.
     *
     * @param poiDAOFactory A {@link com.worldline.poi.data.repository.datasource.POIDAOFactory} for
     * retrieving the desired POIDAO implementation.
     */
    protected POIRepositoryImpl(POIDAOFactory poiDAOFactory) {
        if (poiDAOFactory == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!!!");
        }

        this.poiDAOFactory = poiDAOFactory;
    }

    /**
     * Get a collection of {@link com.worldline.poi.domain.bo.POIBO}.
     *
     * @param poiListCallback A {@link com.worldline.poi.domain.repository.POIRepository.POIListCallback}
     *                        to notify clients.
     */
    @Override
    public void getPOIList(final POIListCallback poiListCallback) {
        POIDAO dao = poiDAOFactory.createDAO();
        dao.getPOIEntityList(new POIDAO.POIListCallback() {
            @Override
            public void onPOIListLoaded(Collection<POIEntity> poisCollection) {
                // TODO transform from POIEntity to POIBO
                //poiListCallback.onPOIListLoaded();
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
    public void getPOIDetail(int id, final POIDetailCallback poiDetailCallback) {
        POIDAO dao = poiDAOFactory.createDAO();
        dao.getPOIDetail(id, new POIDAO.POIDetailCallback() {
            @Override
            public void onPOILoaded(POIEntity poi) {
                // TODO transform from POIEntity to POIBO
                //poiDetailCallback.onPOIDetailLoaded();
            }

            @Override
            public void onError(Exception exception) {
                poiDetailCallback.onError(exception);
            }
        });
    }
}
