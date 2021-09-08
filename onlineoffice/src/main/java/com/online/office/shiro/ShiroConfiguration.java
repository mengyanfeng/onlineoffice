package com.online.office.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {
    @Bean
    public Realm realm() {
        return new ShiroRealm();
    }

    /**
     *  从错误信息知：shiro的自动配置（shiroWebAutoConfiguration）中，自动创建SecurityMananger对象，因此，无需再定义
     *  这也和官方文档一致： http://shiro.apache.org/spring-boot.html
     *  error message :
     *  The bean 'securityManager', defined in class path resource [org/apache/shiro/spring/config/web/autoconfigure/ShiroWebAutoConfiguration.class],
     *  could not be registered. A bean with that name has already been defined in class path resource [com/online/office/shiro/ShiroConfiguration.class]
     *  and overriding is disabled.
     *
     */
    /*
    @Bean
    public SecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }*/

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();


        chainDefinition.addPathDefinition("/user/**", "anon");

        // logged in users with the 'admin' role
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // logged in users with the 'document:read' permission
        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }
}
