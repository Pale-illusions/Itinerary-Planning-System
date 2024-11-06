package com.iflove.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iflove.api.client.UserClient;
import com.iflove.dao.ItineraryDao;
import com.iflove.dao.RouteDao;
import com.iflove.domain.entity.Itinerary;
import com.iflove.domain.entity.Route;
import com.iflove.domain.vo.request.ItineraryAddReq;
import com.iflove.domain.vo.response.ItineraryInfoResp;
import com.iflove.service.ItineraryService;
import com.iflove.service.RouteService;
import com.iflove.service.adapter.ItineraryAdapter;
import com.iflove.starter.common.enums.StatusEnum;
import com.iflove.starter.convention.exception.ClientException;
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
public class ItineraryServiceImpl implements ItineraryService {
    private final ItineraryDao itineraryDao;
    private final RouteDao routeDao;
    private final UserClient userClient;
    private final RouteService routeService;

    @Transactional
    @Override
    public void addItinerary(ItineraryAddReq req, String userId) {
        Route route = routeDao.getById(req.getRouteId());
        if (Objects.isNull(route)) {
            throw new ClientException("行程信息不存在");
        }
        itineraryDao.save(ItineraryAdapter.buildItinerary(req, Long.parseLong(userId)));
    }

    @Override
    public PageBaseResp<ItineraryInfoResp> listItineraryInfo(PageBaseReq req, long userId) {
        IPage<Itinerary> page = itineraryDao.listItineraryInfo(req.plusPage(), userId);
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return PageBaseResp.empty();
        }
        List<ItineraryInfoResp> list = page.getRecords().stream()
                .map(itinerary -> ItineraryInfoResp.builder()
                        .userName(userClient.getUserInfo(userId).getData().getName())
                        .routeInfoResp(routeService.getRouteInfo(itinerary.getRouteId()))
                        .build()
                )
                .collect(Collectors.toList());
        return PageBaseResp.init(page, list);
    }

    @Transactional
    @Override
    public void finish(Long id, long userId) {
        Itinerary itinerary = itineraryDao.getById(id);
        // 不存在或已完成
        if (Objects.isNull(itinerary) || Objects.equals(StatusEnum.FORBIDDEN, StatusEnum.of(itinerary.getStatus()))) {
            throw new ClientException("行程不存在");
        }
        itinerary.setStatus(StatusEnum.FORBIDDEN.getStatus());
        itineraryDao.updateById(itinerary);
    }


}
