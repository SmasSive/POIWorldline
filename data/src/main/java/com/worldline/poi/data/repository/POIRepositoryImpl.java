package com.worldline.poi.data.repository;

import com.worldline.poi.data.bean.vo.POIVO;
import com.worldline.poi.data.bean.vo.mapper.POIVODataMapper;
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

    private POIDAOFactory poiDAOFactory;

    /**
     * Constructs a {@link com.worldline.poi.data.repository.POIRepositoryImpl}.
     *
     * @param poiDAOFactory A {@link com.worldline.poi.data.repository.dao.POIDAOFactory} for
     * retrieving the desired POIDAO implementation.
     * responsible for the conversion of the objects between layers.
     */
    @Inject public POIRepositoryImpl(POIDAOFactory poiDAOFactory) {
        if (poiDAOFactory == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!!!");
        }

        this.poiDAOFactory = poiDAOFactory;
    }

    /**
     * Get a collection of {@link com.worldline.poi.domain.bo.POIBO}.
     */
    @Override
    public void getPOIList() {
        // First, try to get objects from local DB
        final POIDAO localDAO = poiDAOFactory.createLocalDAO();
        localDAO.getPOIEntityList(new POIDAO.POIListCallback() {
            @Override
            public void onPOIListLoaded(Collection<POIVO> poisCollection) {
                // If we didn't find results, let's try in the net
                if (poisCollection == null || poisCollection.size() == 0) {
                    final POIDAO netDAO = poiDAOFactory.createNetDAO();
                    netDAO.getPOIEntityList(new POIDAO.POIListCallback() {
                        @Override
                        public void onPOIListLoaded(Collection<POIVO> poisCollection) {
                            // Save results to DB
                            localDAO.savePOIEntityList(poisCollection);
                            // TODO return results
                        }

                        @Override
                        public void onError(Exception exception) {
                            // TODO
                        }
                    });
                } else {
                    // TODO return results
                }
            }

            @Override
            public void onError(Exception exception) {
                // TODO
            }
        });
    }

    /**
     * Get a {@link com.worldline.poi.domain.bo.POIBO} by its identifier.
     *
     * @param id                The identifier of the desired POI.
     */
    @Override
    public void getPOIDetail(final int id) {
        final POIDAO localDAO = poiDAOFactory.createLocalDAO();
        localDAO.getPOIDetail(id, new POIDAO.POIDetailCallback() {
            @Override
            public void onPOILoaded(POIVO poi) {
                if (poi == null) {
                    POIDAO netDAO = poiDAOFactory.createNetDAO();
                    netDAO.getPOIDetail(id, new POIDAO.POIDetailCallback() {
                        @Override
                        public void onPOILoaded(POIVO poi) {
                            // Save results to DB
                            localDAO.savePOIDetail(poi);
                            // TODO return results
                        }

                        @Override
                        public void onError(Exception exception) {
                            // TODO
                        }
                    });
                } else {
                    // TODO return results
                }
            }

            @Override
            public void onError(Exception exception) {
                // TODO
            }
        });
    }
}
