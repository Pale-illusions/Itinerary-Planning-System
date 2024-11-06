package com.iflove.service;

import com.iflove.domain.vo.request.RouteAddReq;
import jakarta.validation.Valid;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public interface RouteService {
    void addRoute(@Valid RouteAddReq req);
}
