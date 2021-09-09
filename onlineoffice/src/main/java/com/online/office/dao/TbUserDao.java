package com.online.office.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserDao {
    String getOpenID(String name);
}