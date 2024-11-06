package com.iflove.service;

import com.iflove.domain.vo.request.ItineraryAddReq;
import com.iflove.domain.vo.response.ItineraryInfoResp;
import com.iflove.starter.database.page.request.PageBaseReq;
import com.iflove.starter.database.page.response.PageBaseResp;
import jakarta.validation.Valid;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public interface ItineraryService {

    void addItinerary(@Valid ItineraryAddReq req, String userId);

    PageBaseResp<ItineraryInfoResp> listItineraryInfo(@Valid PageBaseReq req, long userId);

    void finish(Long id, long userId);
}
