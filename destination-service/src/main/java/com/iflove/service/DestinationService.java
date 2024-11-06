package com.iflove.service;

import com.iflove.domain.vo.request.DestinationAddReq;
import com.iflove.domain.vo.response.DestinationInfoResp;
import com.iflove.starter.database.page.request.PageBaseReq;
import com.iflove.starter.database.page.response.PageBaseResp;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public interface DestinationService {
    void add(DestinationAddReq req);

    DestinationInfoResp getById(Long id);

    DestinationInfoResp getByName(String name);

    PageBaseResp<DestinationInfoResp> list(PageBaseReq req);
}
