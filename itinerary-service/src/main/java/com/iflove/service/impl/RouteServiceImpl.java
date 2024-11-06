package com.iflove.service.impl;

import com.iflove.api.client.DestinationClient;
import com.iflove.api.client.UserClient;
import com.iflove.api.dto.DestinationInfoResp;
import com.iflove.dao.RouteDao;
import com.iflove.domain.vo.request.RouteAddReq;
import com.iflove.service.RouteService;
import com.iflove.service.adapter.RouteAdapter;
import com.iflove.starter.convention.exception.ClientException;
import com.iflove.starter.convention.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public void addRoute(RouteAddReq req) {
        Result<DestinationInfoResp> start = destinationClient.getById(req.getStart_location());
        if (!start.isSuccess() || Objects.isNull(start.getData())) {
            throw new ClientException("起始地不存在");
        }
        Result<DestinationInfoResp> end = destinationClient.getById(req.getEnd_location());
        if (!start.isSuccess() || Objects.isNull(end.getData())) {
            throw new ClientException("目的地不存在");
        }
        // 起始地不能与目的地一致
        if (Objects.equals(req.getStart_location(), req.getEnd_location())) {
            throw new ClientException("起始地不能与目的地一致");
        }
        routeDao.save(RouteAdapter.buildRoute(req));
    }


}
