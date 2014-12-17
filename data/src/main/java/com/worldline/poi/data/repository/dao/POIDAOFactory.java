package com.worldline.poi.data.repository.dao;

import android.content.Context;

import com.worldline.poi.data.net.POIService;

import javax.inject.Inject;

/**
 * Factory that creates different implementations of {@link com.worldline.poi.data.repository.dao.POIDAO}
 *
 * Created by smassive on 11/23/14.
 */
public class POIDAOFactory {

    @Inject Context context;
    @Inject POIService poiService;

    /**
     * By default we will use the local implementation of {@link com.worldline.poi.data.repository.dao.POIDAO}.
     *
     * @return A local {@link com.worldline.poi.data.repository.dao.POIDAO} implementation.
     */
    public POIDAO createDAO() {
        return createDAO(true);
    }

    /**
     * Create {@link com.worldline.poi.data.repository.dao.POIDAO} based on local parameter.
     *
     * @param local A boolean indicating which implementation we should return. If true a local
     * implementation of {@link com.worldline.poi.data.repository.dao.POIDAO} will be returned.
     *
     * @return An implementation of {@link com.worldline.poi.data.repository.dao.POIDAO}.
     */
    public POIDAO createDAO(boolean local) {
        if (local) {
            return createLocalDAO();
        }

        return createNetDAO();
    }

    /**
     * Get the local implementation of {@link com.worldline.poi.data.repository.dao.POIDAO}.
     *
     * @return A local implementation of {@link com.worldline.poi.data.repository.dao.POIDAO}.
     */
    public POIDAO createLocalDAO() {
        return new POIDAOLocalImpl(context);
    }

    /**
     * Get the net implementation of {@link com.worldline.poi.data.repository.dao.POIDAO}.
     *
     * @return A net implementation of {@link com.worldline.poi.data.repository.dao.POIDAO}.
     */
    public POIDAO createNetDAO() {
        return new POIDAONetImpl(poiService);
    }
}
