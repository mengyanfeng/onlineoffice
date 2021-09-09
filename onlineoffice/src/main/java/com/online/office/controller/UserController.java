package com.online.office.controller;

import com.online.office.po.User;
import com.online.office.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public void login(@Validated @RequestBody User user) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(
                    user.getName(), user.getPassword());
            try {
                currentUser.login( token );
                //if no exception, that's it, we're done!
                log.info("User ["+currentUser.getPrincipal() +"] logged in successfully!");
            } catch ( UnknownAccountException uae ) {
                //username wasn't in the system, show them an error message?
            } catch ( IncorrectCredentialsException ice ) {
                //password didn't match, try again?
            } catch ( LockedAccountException lae ) {
                //account for that username is locked - can't login.  Show them a message?
            } catch ( AuthenticationException ae) {
                //unexpected condition - error?
            }
        }
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
