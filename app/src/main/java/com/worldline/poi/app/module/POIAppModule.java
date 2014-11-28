package com.worldline.poi.app.module;

import android.content.Context;

import com.worldline.poi.app.POIApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by smassive on 28/11/2014.
 */
@Module (
        injects = {POIApplication.class}
)
public class POIAppModule {
    private final POIApplication application;

    public POIAppModule(POIApplication application) {
        this.application = application;
    }

    @Provides @Singleton @ForApplication public Context provideApplicationContext() {
        return application;
    }
}
