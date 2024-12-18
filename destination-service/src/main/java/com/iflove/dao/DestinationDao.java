package com.iflove.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.domain.entity.Destination;
import com.iflove.mapper.DestinationMapper;
import org.springframework.stereotype.Service;

/**
* @author cangjingyue
* @description 针对表【destination(目的地表)】的数据库操作Service实现
* @createDate 2024-11-05 08:36:33
*/
@Service
public class DestinationDao extends ServiceImpl<DestinationMapper, Destination> {

    public Destination getByName(String name) {
        return lambdaQuery()
                .eq(Destination::getName, name)
                .one();
    }

    public IPage<Destination> listDestination(Page page) {
        return lambdaQuery()
                .page(page);
    }
}




