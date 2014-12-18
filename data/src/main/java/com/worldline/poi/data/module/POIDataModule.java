package com.worldline.poi.data.module;

import com.squareup.okhttp.OkHttpClient;
import com.worldline.poi.data.constants.DataConstants;
import com.worldline.poi.data.net.POIService;
import com.worldline.poi.data.repository.dao.POIDAOFactory;
import com.worldline.poi.data.repository.datasource.POIDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by smassive on 28/11/2014.
 */
@Module (
        injects = {POIDAOFactory.class},
        complete = false
)
public class POIDataModule {

    @Provides @Singleton public POIService providePOIService() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(DataConstants.ENDPOINT)
                .build().create(POIService.class);
    }
}
