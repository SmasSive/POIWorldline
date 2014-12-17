package com.worldline.poi.data.bean.vo.utils;

import com.worldline.poi.data.bean.vo.POIVO;

/**
 * Created by smassive on 11/30/14.
 */
public class POIVOUtils {

    /**
     * Copy a {@link com.worldline.poi.data.bean.vo.POIVO} object.
     *
     * @param poiVOSource The object to be copied.
     * @return A new {@link com.worldline.poi.data.bean.vo.POIVO} object.
     */
    public static void copy(POIVO poiVOSource, POIVO poiVOTarget) {
        if (poiVOSource != null) {
            poiVOTarget = new POIVO();
            poiVOTarget.setTransport(poiVOSource.getTransport());
            poiVOTarget.setTitle(poiVOSource.getTitle());
            poiVOTarget.setPhone(poiVOSource.getPhone());
            poiVOTarget.setId(poiVOSource.getId());
            poiVOTarget.setAddress(poiVOSource.getAddress());
            poiVOTarget.setDescription(poiVOSource.getDescription());
            poiVOTarget.setEmail(poiVOSource.getEmail());
            poiVOTarget.setGeocoordinates(poiVOSource.getGeocoordinates());
        }
    }
}
