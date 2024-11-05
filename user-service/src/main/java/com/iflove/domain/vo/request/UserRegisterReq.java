package com.iflove.domain.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 用户注册请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户注册请求体")
public class UserRegisterReq {
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$", message = "用户名只能包含字母、数字或汉字")
    @Length(min = 1, max = 10, message = "用户名长度应在1到10之间")
    @Schema(description = "用户名")
    String username;

    @Schema(description = "密码")
    @Length(min = 6, max = 20, message = "密码长度应在6到20之间")
    String password;
}
