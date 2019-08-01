package tk.mybatis.springboot.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import tk.mybatis.springboot.service.RedisService;

public class ShiroRedisCacheManager extends AbstractCacheManager {

	private RedisTemplate<byte[], byte[]> redisTemplate;

	@Override
	protected Cache createCache(String name) throws CacheException {
		// TODO Auto-generated method stub
		return new ShiroRedisCache(redisTemplate, name);
	}

}
