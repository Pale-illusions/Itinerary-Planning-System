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
 * 行程信息表
 * @TableName route
 */
@TableName(value ="route")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 出发地
     */
    @TableField(value = "start_location")
    private Long start_location;

    /**
     * 目的地
     */
    @TableField(value = "end_location")
    private Long end_location;

    /**
     * 价格 (格式：000000 前4个零代表整数部分，后2个零代表小数部分)
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 耗时
     */
    @TableField(value = "duration")
    private Date duration;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}