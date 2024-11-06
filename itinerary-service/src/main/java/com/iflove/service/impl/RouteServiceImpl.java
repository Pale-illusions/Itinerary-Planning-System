package com.iflove.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iflove.api.client.DestinationClient;
import com.iflove.api.dto.DestinationInfoResp;
import com.iflove.dao.RouteDao;
import com.iflove.domain.entity.Route;
import com.iflove.domain.vo.request.RouteAddReq;
import com.iflove.domain.vo.response.RouteInfoResp;
import com.iflove.service.RouteService;
import com.iflove.service.adapter.RouteAdapter;
import com.iflove.starter.convention.exception.ClientException;
import com.iflove.starter.convention.exception.ServiceException;
import com.iflove.starter.convention.result.Result;
import com.iflove.starter.database.page.request.PageBaseReq;
import com.iflove.starter.database.page.response.PageBaseResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;
    private final DestinationClient destinationClient;

    @Transactional
    @Override
    public void addRoute(RouteAddReq req) {
        Result<DestinationInfoResp> start = destinationClient.getById(req.getStartLocation());
        if (!start.isSuccess() || Objects.isNull(start.getData())) {
            throw new ClientException("起始地不存在");
        }
        Result<DestinationInfoResp> end = destinationClient.getById(req.getEndLocation());
        if (!start.isSuccess() || Objects.isNull(end.getData())) {
            throw new ClientException("目的地不存在");
        }
        // 起始地不能与目的地一致
        if (Objects.equals(req.getStartLocation(), req.getEndLocation())) {
            throw new ClientException("起始地不能与目的地一致");
        }
        routeDao.save(RouteAdapter.buildRoute(req));
    }

    @Override
    public RouteInfoResp getRouteInfo(Long id) {
        Route route = routeDao.getById(id);
        if (Objects.isNull(route)) {
            throw new ClientException("行程信息不存在");
        }
        return getRouteInfoResp(route);
    }

    @Override
    public PageBaseResp<RouteInfoResp> listRouteInfo(PageBaseReq req) {
        IPage<Route> page = routeDao.listRouteInfo(req.plusPage());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return PageBaseResp.empty();
        }
        List<RouteInfoResp> list = page.getRecords().stream()
                .map(this::getRouteInfoResp)
                .collect(Collectors.toList());
        return PageBaseResp.init(page, list);
    }

    private RouteInfoResp getRouteInfoResp(Route route) {
        Result<DestinationInfoResp> start = destinationClient.getById(route.getStartLocation());
        Result<DestinationInfoResp> end = destinationClient.getById(route.getEndLocation());
        if (!start.isSuccess() || !end.isSuccess() || Objects.isNull(start.getData()) || Objects.isNull(end.getData())) {
            throw new ServiceException("远程调用失败");
        }
        return RouteAdapter.buildRouteInfoResp(route, start.getData().getName(), end.getData().getName());
    }


}
