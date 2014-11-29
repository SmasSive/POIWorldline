package com.worldline.poi.app;

import android.app.Application;

import com.worldline.poi.app.module.POIAppModule;
import com.worldline.poi.data.module.POIDataModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by smassive on 28/11/2014.
 */
public class POIApplication extends Application {
    /**
     * The object graph is the place where all these dependencies live. The object graph contains
     * the created instances and is able to inject them to the objects we add to it.
     */
    private ObjectGraph objectGraph;

    /**
     * We assemble the various module classes that we want to instantiate and use it to create an
     * objectGraph. Because Application subclasses provide an effective application-global singleton,
     * it's convenient to stash a reference to the Dagger objectGraph in this class, as well.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Object[] modules = getModules().toArray();
        objectGraph = ObjectGraph.create(modules);
    }

    /**
     * We create the Module passing in a reference to this which can then be used in a provide
     * method in our module so that we can easily inject an Android application instance (which in
     * turn provides a Context) wherever we use Dagger for dependency injection.
     *
     * @return List of modules
     */
    protected List<Object> getModules() {
        return Arrays.<Object>asList(
                new POIAppModule(this),
                new POIDataModule()
        );
    }
}
