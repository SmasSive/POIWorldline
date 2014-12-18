package com.worldline.poi.data.repository.dao;

import com.worldline.poi.data.bean.dto.POIDTO;
import com.worldline.poi.data.bean.dto.mapper.POIDTODataMapper;
import com.worldline.poi.data.bean.vo.POIVO;
import com.worldline.poi.data.exception.POINotFoundException;
import com.worldline.poi.data.net.POIService;

import java.util.Collection;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Implementation of {@link com.worldline.poi.data.repository.dao.POIDAO} for retrieving POI
 * from the net.
 *
 * Created by smassive on 11/23/14.
 */
public class POIDAONetImpl implements POIDAO {

    private POIService poiService;

    /**
     * Constructs a {@link com.worldline.poi.data.repository.dao.POIDAONetImpl}.
     *
     * @param poiService The {@link com.worldline.poi.data.net.POIService} for
     * retrieving the desired data from the net.
     */
    public POIDAONetImpl(POIService poiService) {
        if (poiService == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!!!");
        }
        this.poiService = poiService;
    }

    /**
     * Get a list of {@link com.worldline.poi.data.bean.vo.POIVO}.
     *
     * @param poiListCallback A {@link com.worldline.poi.data.repository.dao.POIDAO.POIListCallback}
     *                        to notify clients.
     */
    @Override
    public void getPOIList(final POIListCallback poiListCallback) {
        poiService.getPOIList().map(new Func1<Collection<POIDTO>, Collection<POIVO>>() {
            @Override
            public Collection<POIVO> call(Collection<POIDTO> poiDTOs) {
                POIDTODataMapper mapper = new POIDTODataMapper();
                return mapper.transform(poiDTOs);
            }
        })
        //.subscribeOn(Schedulers.newThread()) // FIXME Susbscribe (execute) in a new thread?
        //.observeOn(AndroidSchedulers.mainThread()) // FIXME Observe (notify) in main thread?
        .subscribe(new Observer<Collection<POIVO>>() {
            @Override
            public void onCompleted() {
                // TODO Notify???
            }

            @Override
            public void onError(Throwable e) {
                if (poiListCallback != null) {
                    poiListCallback.onError(new POINotFoundException());
                }
            }

            @Override
            public void onNext(Collection<POIVO> poiVOs) {
                if (poiListCallback != null) {
                    poiListCallback.onPOIListLoaded(poiVOs);
                }
            }
        });

    }

    /**
     * Save a list of {@link com.worldline.poi.data.bean.vo.POIVO}.
     *
     * @param poiEntities     The collection of {@link com.worldline.poi.data.bean.vo.POIVO} to save.
     */
    @Override
    public void savePOIList(Collection<POIVO> poiEntities) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the details of a {@link com.worldline.poi.data.bean.vo.POIVO} by its id.
     *
     * @param id                The identifier of the desired POI data.
     * @param poiDetailCallback A {@link com.worldline.poi.data.repository.dao.POIDAO.POIDetailCallback}
     */
    @Override
    public void getPOIDetail(final int id, final POIDetailCallback poiDetailCallback) {
        poiService.getPOIById(id).map(new Func1<POIDTO, POIVO>() {
            @Override
            public POIVO call(POIDTO poiDTO) {
                POIDTODataMapper mapper = new POIDTODataMapper();
                return mapper.transform(poiDTO);
            }
        })
        //.subscribeOn(Schedulers.newThread()) // FIXME Susbscribe (execute) in a new thread?
        //.observeOn(AndroidSchedulers.mainThread()) // FIXME Observe (notify) in main thread?
        .subscribe(new Observer<POIVO>() {
            @Override
            public void onCompleted() {
                // TODO Notify???
            }

            @Override
            public void onError(Throwable e) {
                if (poiDetailCallback != null) {
                    poiDetailCallback.onError(new POINotFoundException());
                }
            }

            @Override
            public void onNext(POIVO poiVO) {
                if (poiDetailCallback != null) {
                    poiDetailCallback.onPOILoaded(id, poiVO);
                }
            }
        });
    }

    /**
     * Save the details of a {@link com.worldline.poi.data.bean.vo.POIVO} entity.
     *
     * @param entity            The entity {@link com.worldline.poi.data.bean.vo.POIVO} to save.
     */
    @Override
    public void savePOIDetail(POIVO entity) {
        throw new UnsupportedOperationException();
    }
}
