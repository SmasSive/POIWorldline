package com.worldline.poi.data.repository;

import android.test.ApplicationTestCase;

import com.worldline.poi.data.entity.mapper.POIEntityDataMapper;
import com.worldline.poi.data.repository.dao.POIDAOFactory;
import com.worldline.poi.domain.repository.POIRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by a163607 on 28/11/2014.
 */
public class POIRepositoryTest extends ApplicationTestCase {

    @Mock
    private POIDAOFactory poidaoFactory;
    @Mock
    private POIEntityDataMapper poiEntityDataMapper;
    @Mock
    private POIRepository.POIListCallback poiListCallback;

    POIRepositoryImpl poiRepository;

    public POIRepositoryTest(Class applicationClass) {
        super(applicationClass);
    }

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        poiRepository = new POIRepositoryImpl(poidaoFactory, poiEntityDataMapper);
    }

    @Test public void testGetPOIListSuccess() {
        poiRepository.getPOIList(poiListCallback);
    }
}
