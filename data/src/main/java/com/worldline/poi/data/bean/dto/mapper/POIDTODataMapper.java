package com.worldline.poi.data.bean.dto.mapper;

import com.worldline.poi.data.bean.dto.POIDTO;
import com.worldline.poi.data.bean.vo.POIVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Mapper class used to transform {@link com.worldline.poi.data.bean.dto.POIDTO} as the response
 * from server to {@link com.worldline.poi.data.bean.vo.POIVO} in the data layer.
 *
 * Created by smassive on 11/24/14.
 */
public class POIDTODataMapper {

    public POIDTODataMapper() {
        //empty
    }

    /**
     * Transform a {@link com.worldline.poi.data.bean.vo.POIVO} into an {@link com.worldline.poi.domain.bo.POIBO}.
     *
     * @param poiDTO Object to be transformed.
     * @return {@link com.worldline.poi.data.bean.vo.POIVO} if valid {@link com.worldline.poi.data.bean.dto.POIDTO}
     * otherwise null.
     */
    public POIVO transform(POIDTO poiDTO) {
        POIVO poiVO = null;
        if (poiDTO != null) {
            poiVO = new POIVO();
            poiVO.setAddress(poiDTO.getAddress());
            poiVO.setDescription(poiDTO.getDescription());
            poiVO.setEmail(poiDTO.getEmail());
            poiVO.setGeocoordinates(poiDTO.getGeocoordinates());
            poiVO.setId(poiDTO.getId());
            poiVO.setPhone(poiDTO.getPhone());
            poiVO.setTitle(poiDTO.getTitle());
            poiVO.setTransport(poiDTO.getTransport());
        }

        return poiVO;
    }

    /**
     * Transform a Collection of {@link com.worldline.poi.data.bean.vo.POIVO} into a Collection
     * of {@link com.worldline.poi.domain.bo.POIBO}.
     *
     * @param poiDTOCollection Object Collection to be transformed.
     * @return {@link com.worldline.poi.data.bean.vo.POIVO} if valid {@link com.worldline.poi.data.bean.dto.POIDTO}
     * otherwise null.
     */
    public Collection<POIVO> transform(Collection<POIDTO> poiDTOCollection) {
        List<POIVO> poiVOs = new ArrayList<POIVO>(20);
        POIVO poiVO;
        for (POIDTO poiDTO : poiDTOCollection) {
            poiVO = transform(poiDTO);
            if (poiVO != null) {
                poiVOs.add(poiVO);
            }
        }

        return poiVOs;
    }
}
