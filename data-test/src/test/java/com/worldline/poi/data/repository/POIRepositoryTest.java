package com.worldline.poi.data.repository;

import com.worldline.poi.data.bean.vo.mapper.POIVODataMapper;
import com.worldline.poi.data.ApplicationTestRunner;
import com.worldline.poi.data.repository.dao.POIDAOFactory;
import com.worldline.poi.domain.repository.POIRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by a163607 on 28/11/2014.
 */
@RunWith(ApplicationTestRunner.class)
public class POIRepositoryTest {

    @Mock
    private POIDAOFactory poidaoFactory;
    @Mock
    private POIVODataMapper POIVODataMapper;
    @Mock
    private POIRepository.POIListCallback poiListCallback;

    POIRepositoryImpl poiRepository;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        poiRepository = new POIRepositoryImpl(poidaoFactory, POIVODataMapper);


    }

    @Test public void testGetPOIListSuccess() {

        poiRepository.getPOIList(poiListCallback);
    }
}
