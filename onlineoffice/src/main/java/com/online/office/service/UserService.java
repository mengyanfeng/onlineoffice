package com.online.office.service;

import com.online.office.dao.TbUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    TbUserDao tbUserDao;

    public String getCredential(String name) {
        return tbUserDao.getOpenID(name);
    }

}
