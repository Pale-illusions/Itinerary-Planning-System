package com.iflove.controller;

import com.iflove.domain.vo.request.DestinationAddReq;
import com.iflove.domain.vo.response.DestinationInfoResp;
import com.iflove.service.DestinationService;
import com.iflove.starter.convention.result.Result;
import com.iflove.starter.database.page.request.PageBaseReq;
import com.iflove.starter.database.page.response.PageBaseResp;
import com.iflove.starter.log.annotation.ILog;
import com.iflove.starter.web.Results;
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
    public Result<Void> add(@RequestBody @Valid DestinationAddReq req) {
        destinationService.add(req);
        return Results.success();
    }

    @GetMapping("id/{id}")
    public Result<DestinationInfoResp> getById(@PathVariable Long id) {
        return Results.success(destinationService.getById(id));
    }

    @GetMapping("name/{name}")
    public Result<DestinationInfoResp> getByName(@PathVariable String name) {
        return Results.success(destinationService.getByName(name));
    }

    @GetMapping("list")
    public Result<PageBaseResp<DestinationInfoResp>> list(PageBaseReq req) {
        return Results.success(destinationService.list(req));
    }
}
