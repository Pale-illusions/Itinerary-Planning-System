package com.iflove.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.domain.entity.Itinerary;
import com.iflove.mapper.ItineraryMapper;
import com.iflove.starter.common.enums.StatusEnum;
import org.springframework.stereotype.Service;

/**
* @author cangjingyue
* @description 针对表【itinerary(行程表)】的数据库操作Service实现
* @createDate 2024-11-05 08:36:33
*/
@Service
public class ItineraryDao extends ServiceImpl<ItineraryMapper, Itinerary> {

    public IPage<Itinerary> listItineraryInfo(Page page, long userId) {
        return lambdaQuery()
                .eq(Itinerary::getUserId, userId)
                .eq(Itinerary::getStatus, StatusEnum.NORMAL.getStatus())
                .page(page);
    }
}




