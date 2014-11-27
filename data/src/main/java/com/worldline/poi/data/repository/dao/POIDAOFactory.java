package com.worldline.poi.data.repository.dao;

import android.content.Context;

/**
 * Factory that creates different implementations of {@link com.worldline.poi.data.repository.dao.POIDAO}
 *
 * Created by smassive on 11/23/14.
 */
public class POIDAOFactory {

    private Context context;

    public POIDAOFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!!!");
        }
        this.context = context;
    }

    public POIDAO createLocalDAO() {
        return new POIDAOLocalImpl(context);
    }

    public POIDAO createNetDAO() {
        return new POIDAONetImpl();
    }
}
