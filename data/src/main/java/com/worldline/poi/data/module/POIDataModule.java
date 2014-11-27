package com.worldline.poi.data.module;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;
import com.worldline.poi.data.constants.DataConstants;
import com.worldline.poi.data.net.POIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Dagger module to provide POIService interface
 *
 * Created by smassive on 27/11/2014.
 */
@Module(
        library = true,
        complete = false
)
public class POIDataModule {

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    public POIService providePOIService() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(DataConstants.ENDPOINT)
                .build().create(POIService.class);
    }
}
