package com.iflove.service.adapter;

import com.iflove.domain.entity.Itinerary;
import com.iflove.domain.vo.request.ItineraryAddReq;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public class ItineraryAdapter {
    public static Itinerary buildItinerary(ItineraryAddReq req, long userId) {
        return Itinerary.builder()
                .routeId(req.getRouteId())
                .userId(userId)
                .build();
    }
}
