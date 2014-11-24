package com.worldline.poi.data.entity.mapper;

import com.worldline.poi.data.entity.POIEntity;
import com.worldline.poi.domain.bo.POIBO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Mapper class used to transform {@link com.worldline.poi.data.entity.POIEntity} (in the data layer)
 * to {@link com.worldline.poi.domain.bo.POIBO} in the domain layer.
 *
 * Created by smassive on 11/24/14.
 */
public class POIEntityDataMapper {

    public POIEntityDataMapper() {
        //empty
    }

    /**
     * Transform a {@link com.worldline.poi.data.entity.POIEntity} into an {@link com.worldline.poi.domain.bo.POIBO}.
     *
     * @param poiEntity Object to be transformed.
     * @return {@link com.worldline.poi.domain.bo.POIBO} if valid {@link com.worldline.poi.data.entity.POIEntity}
     * otherwise null.
     */
    public POIBO transform(POIEntity poiEntity) {
        POIBO poiBO = null;
        if (poiEntity != null) {
            poiBO = new POIBO();
            poiBO.setAddress(poiEntity.getAddress());
            poiBO.setDescription(poiEntity.getDescription());
            poiBO.setEmail(poiEntity.getEmail());
            poiBO.setGeocoordinates(poiEntity.getGeocoordinates());
            poiBO.setId(poiEntity.getId());
            poiBO.setPhone(poiEntity.getPhone());
            poiBO.setTitle(poiEntity.getTitle());
            poiBO.setTransport(poiEntity.getTransport());
        }

        return poiBO;
    }

    /**
     * Transform a Collection of {@link com.worldline.poi.data.entity.POIEntity} into a Collection
     * of {@link com.worldline.poi.domain.bo.POIBO}.
     *
     * @param poiEntityCollection Object Collection to be transformed.
     * @return {@link com.worldline.poi.domain.bo.POIBO} if valid {@link com.worldline.poi.data.entity.POIEntity}
     * otherwise null.
     */
    public Collection<POIBO> transform(Collection<POIEntity> poiEntityCollection) {
        List<POIBO> userList = new ArrayList<POIBO>(20);
        POIBO user;
        for (POIEntity userEntity : poiEntityCollection) {
            user = transform(userEntity);
            if (user != null) {
                userList.add(user);
            }
        }

        return userList;
    }
}
