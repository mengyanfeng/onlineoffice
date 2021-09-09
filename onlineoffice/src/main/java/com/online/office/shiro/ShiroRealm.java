package com.online.office.shiro;

import com.online.office.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AuthorizingRealm : from office site documentation (http://shiro.apache.org/documentation.html)
 * search  key-workd 'realm'.
 * ej: https://shiro.apache.org/static/1.8.0/apidocs/org/apache/shiro/realm/AuthorizingRealm.html
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = (String) token.getPrincipal();
        String open_id = String.valueOf(token.getPassword());

        String storeid = userService.getCredential(name);
        if (storeid.isEmpty()) {
            log.info("name is not exist!");
            return null;
        }
        if (!storeid.equals(open_id)) {
            log.info("open_id is not matcher!");
            return null;
        }
        return new SimpleAuthenticationInfo(name, open_id, ShiroRealm.class.getName());
    }
}
