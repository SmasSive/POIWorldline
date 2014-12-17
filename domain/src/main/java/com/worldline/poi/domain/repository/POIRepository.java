package com.worldline.poi.domain.repository;

import com.worldline.poi.domain.bo.POIBO;

import java.util.Collection;

/**
 * Interface that represents a Repository for getting {@link com.worldline.poi.domain.bo.POIBO}.
 *
 * Created by smassive on 11/23/14.
 */
public interface POIRepository {

    /**
     * Get a Collection of {@link com.worldline.poi.domain.bo.POIBO}.
     *
     */
    public void getPOIList();

    /**
     * Get a {@link com.worldline.poi.domain.bo.POIBO} by its identifier.
     *
     * @param id The identifier of the desired POI.
     */
    public void getPOIDetail(int id);
}
