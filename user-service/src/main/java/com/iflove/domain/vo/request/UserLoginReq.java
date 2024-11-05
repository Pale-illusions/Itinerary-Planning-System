package com.iflove.domain.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 用户登录请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户登录请求体")
public class UserLoginReq {
    @Schema(description = "用户名")
    @Length(min = 1, max = 20)
    @NotNull(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @Length(min = 6, max = 20)
    @NotNull(message = "密码不能为空")
    private String password;
}
