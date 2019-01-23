package com.ruther.api.gateway;

import com.ruther.api.gateway.filter.RateLimiterBefore;
import com.ruther.api.gateway.limiter.LuaRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;


/**
 * @describe: 网关启动类
 * @author: ruther
 * @date: 2018/8/21 10:28
 **/
@SpringCloudApplication
@EnableZuulProxy
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Bean
    public LuaRateLimiter luaRateLimiter() {
        return new LuaRateLimiter( script(), stringRedisTemplate);
    }


    @Bean
    public RedisScript<Long> script() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<Long>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rate_limiter.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }

    @Bean
    public RateLimiterBefore accessFilterBefore() {
        return new RateLimiterBefore();
    }
}
