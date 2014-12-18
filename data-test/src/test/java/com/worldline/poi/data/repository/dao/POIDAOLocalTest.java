package com.worldline.poi.data.repository.dao;

import com.worldline.poi.data.ApplicationTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;

/**
 * Created by a163607 on 28/11/2014.
 */
@RunWith(ApplicationTestRunner.class)
public class POIDAOLocalTest {

    @Mock
    private POIDAO.POIListCallback poiListCallback;

    private POIDAO poiDAOLocal;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        poiDAOLocal = new POIDAOLocalImpl(Robolectric.application);
    }

    @Test public void testGetPOIEntityListSuccess() {
        poiDAOLocal.getPOIList(poiListCallback);
    }
}
