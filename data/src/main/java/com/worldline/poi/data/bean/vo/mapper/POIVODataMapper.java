package com.worldline.poi.data.bean.vo.mapper;

import com.worldline.poi.data.bean.vo.POIVO;
import com.worldline.poi.domain.bo.POIBO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Mapper class used to transform {@link com.worldline.poi.data.bean.vo.POIVO} in the data layer
 * to {@link com.worldline.poi.domain.bo.POIBO} in the domain layer.
 *
 * Created by smassive on 11/24/14.
 */
public class POIVODataMapper {

    public POIVODataMapper() {
        //empty
    }

    /**
     * Transform a {@link com.worldline.poi.data.bean.vo.POIVO} into an {@link com.worldline.poi.domain.bo.POIBO}.
     *
     * @param poiVO Object to be transformed.
     * @return {@link com.worldline.poi.domain.bo.POIBO} if valid {@link com.worldline.poi.data.bean.vo.POIVO}
     * otherwise null.
     */
    public POIBO transform(POIVO poiVO) {
        POIBO poiBO = null;
        if (poiVO != null) {
            poiBO = new POIBO();
            poiBO.setAddress(poiVO.getAddress());
            poiBO.setDescription(poiVO.getDescription());
            poiBO.setEmail(poiVO.getEmail());
            poiBO.setGeocoordinates(poiVO.getGeocoordinates());
            poiBO.setId(poiVO.getId());
            poiBO.setPhone(poiVO.getPhone());
            poiBO.setTitle(poiVO.getTitle());
            poiBO.setTransport(poiVO.getTransport());
        }

        return poiBO;
    }

    /**
     * Transform a Collection of {@link com.worldline.poi.data.bean.vo.POIVO} into a Collection
     * of {@link com.worldline.poi.domain.bo.POIBO}.
     *
     * @param poiVOCollection Object Collection to be transformed.
     * @return {@link com.worldline.poi.domain.bo.POIBO} if valid {@link com.worldline.poi.data.bean.vo.POIVO}
     * otherwise null.
     */
    public Collection<POIBO> transform(Collection<POIVO> poiVOCollection) {
        List<POIBO> poiBOs = new ArrayList<POIBO>(20);
        POIBO poiBO;
        for (POIVO poiVO : poiVOCollection) {
            poiBO = transform(poiVO);
            if (poiBO != null) {
                poiBOs.add(poiBO);
            }
        }

        return poiBOs;
    }
}
