package com.iflove.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.iflove.domain.entity.Route;
import com.iflove.mapper.RouteMapper;
import org.springframework.stereotype.Service;

/**
* @author cangjingyue
* @description 针对表【route(行程信息表)】的数据库操作Service实现
* @createDate 2024-11-05 08:36:33
*/
@Service
public class RouteDao extends ServiceImpl<RouteMapper, Route> {

    public IPage<Route> listRouteInfo(Page page) {
        return lambdaQuery()
                .page(page);
    }
}




