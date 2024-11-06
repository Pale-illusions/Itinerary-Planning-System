package com.iflove.controller;

import com.iflove.domain.vo.request.DestinationAddReq;
import com.iflove.domain.vo.response.DestinationInfoResp;
import com.iflove.service.DestinationService;
import com.iflove.starter.convention.result.Result;
import com.iflove.starter.database.page.request.PageBaseReq;
import com.iflove.starter.database.page.response.PageBaseResp;
import com.iflove.starter.log.annotation.ILog;
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
@RequestMapping("api/destination")
@Validated
@Tag(name = "目的地模块")
@RequiredArgsConstructor
@ILog
public class DestinationController {
    private final DestinationService destinationService;


    @PostMapping("add")
    @Operation(summary = "添加目的地", description = "添加目的地")
    public Result<Void> add(@RequestBody @Valid DestinationAddReq req) {
        destinationService.add(req);
        return Results.success();
    }

    @GetMapping("id/{id}")
    @Operation(summary = "目的地信息(id)", description = "目的地信息")
    public Result<DestinationInfoResp> getById(@PathVariable Long id) {
        return Results.success(destinationService.getById(id));
    }

    @GetMapping("name/{name}")
    @Operation(summary = "目的地信息(name)", description = "目的地信息")
    public Result<DestinationInfoResp> getByName(@PathVariable String name) {
        return Results.success(destinationService.getByName(name));
    }

    @GetMapping("list")
    @Operation(summary = "目的地信息列表", description = "目的地信息列表")
    public Result<PageBaseResp<DestinationInfoResp>> list(PageBaseReq req) {
        return Results.success(destinationService.list(req));
    }
}
