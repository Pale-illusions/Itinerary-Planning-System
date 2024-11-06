package com.iflove.service.adapter;

import com.iflove.domain.entity.Destination;
import com.iflove.domain.vo.response.DestinationInfoResp;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public class DestinationAdapter {

    public static List<DestinationInfoResp> buildDestinationInfoResp(List<Destination> destination) {
        return destination.stream()
                .map(d -> DestinationInfoResp.builder()
                        .id(d.getId())
                        .name(d.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
