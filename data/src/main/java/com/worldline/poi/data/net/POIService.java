package com.worldline.poi.data.net;

import com.worldline.poi.data.bean.dto.POIDTO;

import java.util.Collection;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by smassive on 11/22/14.
 */
public interface POIService {

    /**
     * Get the list of POIs.
     */
    @GET("/points")
    public Observable<Collection<POIDTO>> getPOIList();

    /**
     * Get the detail of a POI by id.
     * @param poiId The POI id to get POI data.
     */
    @GET("/points/{id}")
    public Observable<POIDTO> getPOIById(@Path("id") int poiId);
}
