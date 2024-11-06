package com.iflove.service;

import com.iflove.domain.vo.request.RouteAddReq;
import com.iflove.domain.vo.response.RouteInfoResp;
import com.iflove.starter.database.page.request.PageBaseReq;
import com.iflove.starter.database.page.response.PageBaseResp;
import jakarta.validation.Valid;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public interface RouteService {
    void addRoute(@Valid RouteAddReq req);

    RouteInfoResp getRouteInfo(Long id);

    PageBaseResp<RouteInfoResp> listRouteInfo(@Valid PageBaseReq req);

}
