package com.iflove.controller;

import com.iflove.api.client.DestinationClient;
import com.iflove.api.client.UserClient;
import com.iflove.domain.vo.request.ItineraryAddReq;
import com.iflove.domain.vo.request.RouteAddReq;
import com.iflove.domain.vo.response.ItineraryInfoResp;
import com.iflove.domain.vo.response.RouteInfoResp;
import com.iflove.service.ItineraryService;
import com.iflove.service.RouteService;
import com.iflove.starter.convention.result.Result;
import com.iflove.starter.database.page.request.PageBaseReq;
import com.iflove.starter.database.page.response.PageBaseResp;
import com.iflove.starter.frequencycontrol.annotation.FrequencyControl;
import com.iflove.starter.frequencycontrol.domain.enums.FrequencyControlStrategyEnum;
import com.iflove.starter.frequencycontrol.domain.enums.FrequencyControlTargetEnum;
import com.iflove.starter.log.annotation.ILog;
import com.iflove.starter.user.core.UserContext;
import com.iflove.starter.web.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@RestController
@RequestMapping("api/itinerary")
@Validated
@Tag(name = "行程模块")
@RequiredArgsConstructor
@ILog
public class ItineraryController {
    private final ItineraryService itineraryService;
    private final RouteService routeService;

    @PostMapping("route/add")
    @Operation(summary = "添加行程信息", description = "添加行程信息")
    public Result<Void> addRoute(@RequestBody @Valid RouteAddReq req) {
        routeService.addRoute(req);
        return Results.success();
    }

    @GetMapping("route/{id}")
    @Operation(summary = "行程信息", description = "行程信息")
    public Result<RouteInfoResp> getRouteInfo(@PathVariable Long id) {
        return Results.success(routeService.getRouteInfo(id));
    }

    @GetMapping("route/list")
    @Operation(summary = "行程信息列表", description = "行程信息列表")
    public Result<PageBaseResp<RouteInfoResp>> listRouteInfo(@Valid PageBaseReq req) {
        return Results.success(routeService.listRouteInfo(req));
    }

    @PostMapping("add")
    @Operation(summary = "添加行程", description = "添加行程")
    public Result<Void> addItinerary(@RequestBody @Valid ItineraryAddReq req) {
        itineraryService.addItinerary(req, UserContext.getUserId());
        return Results.success();
    }

    @GetMapping("list")
    @Operation(summary = "行程列表", description = "行程列表")
    public Result<PageBaseResp<ItineraryInfoResp>> listItineraryInfo(@Valid PageBaseReq req) {
        return Results.success(itineraryService.listItineraryInfo(req, Long.parseLong(UserContext.getUserId())));
    }

    @PutMapping("{id}")
    @Operation(summary = "结束行程", description = "结束行程")
    public Result<Void> finish(@PathVariable Long id) {
        itineraryService.finish(id, Long.parseLong(UserContext.getUserId()));
        return Results.success();
    }
}
