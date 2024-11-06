package com.iflove.service.adapter;

import com.iflove.domain.entity.Route;
import com.iflove.domain.vo.request.RouteAddReq;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public class RouteAdapter {
    public static Route buildRoute(RouteAddReq req) {
        return Route.builder()
                .start_location(req.getStart_location())
                .end_location(req.getEnd_location())
                .price(req.getPrice())
                .duration(req.getDurationAsLocalTime())
                .build();
    }
}
