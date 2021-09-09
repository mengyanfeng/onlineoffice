package com.online.office.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class User {
    @NotBlank(message = "用户名不能为空")
    private String name;
    @Size(min=2, max=32, message = "密码长度为【2-32】个字符")
    private String password;
}
