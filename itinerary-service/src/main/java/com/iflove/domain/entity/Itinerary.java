package com.iflove.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 行程表
 * @TableName itinerary
 */
@TableName(value ="itinerary")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Itinerary implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建人id
     */
    @TableField(value = "user_id")
    private Long user_id;

    /**
     * 行程信息id
     */
    @TableField(value = "route_id")
    private Long route_id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 行程状态 ( 0 未完成 / 1 已完成 / 2 已失效 )
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}