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

    public POIDAO createLocalDAO() {
        return new POIDAOLocalImpl(context);
    }

    public POIDAO createNetDAO() {
        return new POIDAONetImpl(poiService);
    }
}
