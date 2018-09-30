package com.mplus.core.cache.redis;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonRedisSerializer implements RedisSerializer {
	
	private static Logger logger = LoggerFactory.getLogger(FastJsonRedisSerializer.class);
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
 
    public FastJsonRedisSerializer() {
        
    }
 
    @Override
    public byte[] serialize(@Nullable Object source) throws SerializationException {
        if (null == source) {
            return new byte[0];
        }
        String json = JSON.toJSONString(source, SerializerFeature.WriteClassName);
        logger.info("序列化:{}", json);
        return json.getBytes(DEFAULT_CHARSET);
    }
 
    @Override
	public Object deserialize(@Nullable byte[] source) throws SerializationException {
		return deserialize(source, Object.class);
	}
    
    @Nullable
    public <T> T deserialize(@Nullable byte[] source, Class<T> type) throws SerializationException {
        if (null == source || source.length <= 0) {
            return null;
        }
        String str = new String(source, DEFAULT_CHARSET);
        try {
        	return (T) JSON.parseObject(str, type);
		} catch (Exception ex) {
			throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
		}
    }
 
}
