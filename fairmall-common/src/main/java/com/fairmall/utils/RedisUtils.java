package com.fairmall.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2020/10/12 9:43
 * @Description:  redis工具类
 */
@Component
public class RedisUtils {
//    @Autowired
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    // 30s 超时时间
    private static final long DEFAULT_EXPIRE = 30;
    private static final Logger logger = LoggerFactory
            .getLogger(RedisUtils.class);

    /**
     * @Description: 将value对象写入缓存
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        if (null != key) {
            redisTemplate.opsForValue().set(getKey(key), value);
        }
    }
    /**
     * @Description: 将value对象写入缓存
     * @param key
     * @param value
     * @param time
     */
    public void set(String key, Object value, long time) {
        if (null != key) {
            set(key, value);
            expire(key, time);
        }
    }

    /**
     * @Description: 获取对象
     * @param key
     * @return
     */
    public Object get(String key) {
        if (null != key) {
            return redisTemplate.opsForValue().get(getKey(key));
        }
        return null;
    }

    /**
     * @Description: 删除缓存 根据key精确匹配删除
     * @param key
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            List<String> delKeys = new ArrayList<String>();
            for (String k : key) {
                if (StringUtils.isEmpty(k)) {
                    delKeys.add(getKey(k));
//					redisTemplate.delete(getKey(k));
                }
            }
            redisTemplate.delete(delKeys);
        }
    }

    /**
     * @Description: 指定缓存的失效时间
     * @param key
     * @param time
     */
    public void expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(getKey(key), time, TimeUnit.SECONDS);
        }
    }

    /**
     * @Description: 生成seq
     * @param key
     * @return
     */
    public long generate(String key) {
        RedisAtomicLong counter = new RedisAtomicLong(getKey(key),
                redisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }
    /**
     * @Description:  生成seq，设置失效时间
     * @param key
     * @param expireTime
     * @return
     */
    public long generate(String key, Date expireTime) {
        RedisAtomicLong counter = new RedisAtomicLong(getKey(key),
                redisTemplate.getConnectionFactory());
        counter.expireAt(expireTime);
        return counter.incrementAndGet();
    }

    /**
     * @Description: 按给定增长额生成seq
     * @param key
     * @param increment
     * @return
     */
    public long generate(String key, int increment) {
        RedisAtomicLong counter = new RedisAtomicLong(getKey(key),
                redisTemplate.getConnectionFactory());
        return counter.addAndGet(increment);
    }

    /**
     * @Description: 按给定增长额生成seq，设置失效时间
     * @param key
     * @param increment
     * @param expireTime
     * @return
     */
    public long generate(String key, int increment, Date expireTime) {
        RedisAtomicLong counter = new RedisAtomicLong(getKey(key),
                redisTemplate.getConnectionFactory());
        counter.expireAt(expireTime);
        return counter.addAndGet(increment);
    }

    /**
     * @Description: 获取加上前缀的key
     * @param key
     * @return
     */
    private String getKey(String key) {
        return key;
    }
    /**
     * @Description:  生成lock key
     * @param key
     * @return
     */
    public String generateLockKey(String key) {
        return String.format("LOCK:%s", getKey(key));
    }

    /**
     * @Description: 将value对象写入缓存
     * @param key
     * @param value
     */
    public void setOrigin(String key, Object value) {
        if (null != key) {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * @Description: 将value对象写入缓存
     * @param key
     * @param value
     * @param time
     */
    public void setOrigin(String key, Object value, long time) {
        if (null != key) {
            setOrigin(key, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * @Description: 获取对象
     * @param key
     * @return
     */
    public Object getOrigin(String key) {
        if (null != key) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * @Description: 删除缓存 根据key精确匹配删除
     * @param key
     */
    public void delOrigin(String... key) {
        if (key != null && key.length > 0) {
            List<String> delKeys = new ArrayList<String>();
            for (String k : key) {
                if (StringUtils.isEmpty(k)) {
                    delKeys.add(k);
                    redisTemplate.delete(k);
                }
            }
            redisTemplate.delete(delKeys);
        }
    }

    /**
     *@Description: 加锁（key带应用简称）
     * @param key
     * @return
     */
    public boolean lock(String key) {
        return lock(key, DEFAULT_EXPIRE);
    }

    /**
     * @Description: 加锁（key带应用简称）
     * @param key
     * @param time 超时时间（秒）
     * @return
     */
    public boolean lock(String key, long time) {
        return lockOrigin(generateLockKey(key), time);
    }

    /**
     * @Description: 解锁
     * @param key
     */
    public void unlock(String key) {
        unlockOrigin(generateLockKey(key));
    }

    /**
     * @Description: 加锁
     * @param key 加锁key
     * @param time 超时时间（秒）
     * @return boolean 成功 true，失败 fasle
     */
    public boolean lockOrigin(String key, long time) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        try {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            if (redisTemplate.getConnectionFactory().getConnection().setNX(serializer.serialize(key), new byte[0])) {
                // 暂设置过期，防止异常中断锁未释放
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
                logger.debug("add RedisLock[" + key + "].");
                return true;
            }
        } catch (Exception e) {
            unlockOrigin(key);
        }
        return false;
    }

    /**
     * @Description: 解锁
     * @param key
     */
    public void unlockOrigin(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        logger.debug("release RedisLock[" + key + "].");
        RedisSerializer<String> serializer = redisTemplate
                .getStringSerializer();
        redisTemplate.getConnectionFactory()
                .getConnection().del(serializer.serialize(key));
    }
    /**
     * @Description: 加锁
     * @param key
     * @param time 有效期（秒）
     * @return boolean
     */
    public boolean lockBusiness(String key, long time) {
        String lockKey = generateLockKey(key);
        if(lockOrigin(lockKey, time)){
            set(lockKey, DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            return true;
        }
        return false;
    }
    /**
     * @Description: 加锁的key是否有效
     * @param key
     * @param time 有效期（秒）
     * @return boolean
     */
    public boolean isvalidLockBusiness(String key, long time) {
        String lockKey = generateLockKey(key);
        Object value = get(lockKey);
        if (null != value && !"".equals(value)) {
            long times = DateUtils.secondsBetween(new Date(), DateUtils.parseDate(
                    value.toString(), "yyyy-MM-dd HH:mm:ss"));
            if (times < time) {
                return true;
            }
        }
        return false;
    }
    /**
     * @Description:  解锁
     * @param key
     */
    public void unlockBusiness(String key) {
        String lockKey = generateLockKey(key);
        unlockOrigin(lockKey);
        del(lockKey);
    }
}
