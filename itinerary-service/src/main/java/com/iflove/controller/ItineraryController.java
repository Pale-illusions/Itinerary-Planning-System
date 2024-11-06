package com.iflove.controller;

import com.iflove.domain.vo.request.RouteAddReq;
import com.iflove.service.ItineraryService;
import com.iflove.service.RouteService;
import com.iflove.starter.convention.result.Result;
import com.iflove.starter.web.Results;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@RestController
@RequestMapping("api/Itinerary")
@Validated
@Tag(name = "行程模块")
@RequiredArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;
    private final RouteService routeService;

    @PostMapping("route/add")
    public Result<Void> addRoute(@RequestBody @Valid RouteAddReq req) {
        routeService.addRoute(req);
        return Results.success();
    }
}
