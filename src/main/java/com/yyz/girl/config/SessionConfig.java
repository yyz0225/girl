package com.yyz.girl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: yyz
 * @Date: 2020/1/9 16:59
 * maxInactiveIntervalInSeconds设置session失效时间
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class SessionConfig {
}
