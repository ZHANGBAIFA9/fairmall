package com.fairmall.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2020/10/12 14:27
 * @Description: 复制工具类
 */
public class CopyUtils {
    /**
     * 复制集合
     */
    public static <T,K> List<T> copyList(List<K> sourceList, Class<T> clazz) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return null;
        }
        ArrayList<T> target = new ArrayList<>();
        sourceList.forEach(k -> target.add(convert(k, clazz)));
        return target;
    }

    /**
     * 复制对象
     */
    public static <T,K> T convert(K source, Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(source, t);
        return t;
    }

}
