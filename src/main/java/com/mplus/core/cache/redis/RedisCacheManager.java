package com.mplus.core.cache.redis;

import javax.annotation.Resource;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCacheManager extends AbstractCacheManager {

	@Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected Cache<String, Object> createCache(String name) throws CacheException {
        return new ShiroCache<>(redisTemplate, name);
    }

}
