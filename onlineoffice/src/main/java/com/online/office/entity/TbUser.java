package com.online.office.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * tb_action
 * @author 
 */
@Data
public class TbUser implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 行为编号
     */
    private String actionCode;

    /**
     * 行为名称
     */
    private String actionName;

    private static final long serialVersionUID = 1L;
}