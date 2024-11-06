package com.iflove.service.impl;

import com.iflove.dao.RouteDao;
import com.iflove.domain.vo.request.RouteAddReq;
import com.iflove.service.RouteService;
import com.iflove.service.adapter.RouteAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;

    @Override
    public void addRoute(RouteAddReq req) {
        routeDao.save(RouteAdapter.buildRoute(req));
    }


}
