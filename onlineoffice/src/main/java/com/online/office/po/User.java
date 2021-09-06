package com.online.office.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class User {
    @NotBlank(message = "用户名不能为空")
    private String name;
    @Size(min=6, max=32, message = "密码长度为【6-32】个字符")
    private String password;
}
