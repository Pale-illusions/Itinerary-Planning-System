package com.iflove.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iflove.dao.DestinationDao;
import com.iflove.domain.entity.Destination;
import com.iflove.domain.vo.request.DestinationAddReq;
import com.iflove.domain.vo.response.DestinationInfoResp;
import com.iflove.service.DestinationService;
import com.iflove.service.adapter.DestinationAdapter;
import com.iflove.starter.convention.exception.ClientException;
import com.iflove.starter.database.page.request.PageBaseReq;
import com.iflove.starter.database.page.response.PageBaseResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private final DestinationDao destinationDao;

    @Override
    @Transactional
    public void add(DestinationAddReq req) {
        Destination destination = destinationDao.getByName(req.getName());
        if (Objects.nonNull(destination)) {
            throw new ClientException("目的地已存在");
        }
        destinationDao.save(Destination.builder().name(req.getName()).build());
    }

    @Override
    public DestinationInfoResp getById(Long id) {
        Destination destination = destinationDao.getById(id);
        if (Objects.isNull(destination)) {
            throw new ClientException("未知目的地");
        }
        return CollectionUtil.getFirst(DestinationAdapter.buildDestinationInfoResp(Collections.singletonList(destination)));
    }

    @Override
    public DestinationInfoResp getByName(String name) {
        Destination destination = destinationDao.getByName(name);
        if (Objects.isNull(destination)) {
            throw new ClientException("未知目的地");
        }
        return CollectionUtil.getFirst(DestinationAdapter.buildDestinationInfoResp(Collections.singletonList(destination)));
    }

    @Override
    public PageBaseResp<DestinationInfoResp> list(PageBaseReq req) {
        IPage<Destination> page = destinationDao.listDestination(req.plusPage());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return PageBaseResp.empty();
        }
        return PageBaseResp.init(page, DestinationAdapter.buildDestinationInfoResp(page.getRecords()));
    }


}
