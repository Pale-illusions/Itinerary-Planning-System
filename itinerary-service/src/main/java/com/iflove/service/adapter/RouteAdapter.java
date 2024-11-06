package com.iflove.service.adapter;

import com.iflove.domain.entity.Route;
import com.iflove.domain.vo.request.RouteAddReq;
import com.iflove.domain.vo.response.RouteInfoResp;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public class RouteAdapter {
    public static Route buildRoute(RouteAddReq req) {
        return Route.builder()
                .startLocation(req.getStartLocation())
                .endLocation(req.getEndLocation())
                .price(req.getPrice())
                .duration(req.getDurationAsLocalTime())
                .build();
    }

    public static RouteInfoResp buildRouteInfoResp(Route route, String start, String end) {
        return RouteInfoResp.builder()
                .id(route.getId())
                .startLocation(start)
                .endLocation(end)
                .price(route.getPrice())
                .duratoin(route.getDuration())
                .build();
    }
}
