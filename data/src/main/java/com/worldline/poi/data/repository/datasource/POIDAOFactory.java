package com.worldline.poi.data.repository.datasource;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.worldline.poi.data.net.POIService;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Factory that creates different implementations of {@link com.worldline.poi.data.repository.datasource.POIDAO}
 *
 * Created by smassive on 11/23/14.
 */
public class POIDAOFactory {

    private static String ENDPOINT = "http://t21services.herokuapp.com";

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
        OkHttpClient okHttpClient = new OkHttpClient();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(ENDPOINT)
                .build();

        POIService poiService = restAdapter.create(POIService.class);

        return new POIDAONetImpl(poiService);
    }
}
