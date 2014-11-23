package com.worldline.poi.data.repository.datasource;

import android.content.Context;

/**
 * Factory that creates different implementations of {@link com.worldline.poi.data.repository.datasource.POIDAO}
 *
 * Created by smassive on 11/23/14.
 */
public class POIDAOFactory {

    private Context context;
    private boolean refreshData;

    public POIDAOFactory(Context context, boolean refreshData) {
        if (context == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!!!");
        }
        this.context = context;
        this.refreshData = refreshData;
    }

    public POIDAO createDAO() {
        if (refreshData) {
            return new POIDAONetImpl();
        }
        return new POIDAOLocalImpl(context);
    }
}
