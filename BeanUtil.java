package com.cx.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class BeanUtil {

    /**
     * 拷貝屬性
     *
     * @param s   源對象
     * @param c   目標類
     * @param <S> 源類型
     * @param <T> 目標類型
     * @return
     */
    public static <S, T> T transform(S s, Class<? extends T> c) {
        if (s == null) {
            return null;
        }
        T t = null;
        try {
            t = c.newInstance();
            BeanUtils.copyProperties(s, t);
        } catch (InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return t;
    }

    /**
     * 拷貝屬性
     *
     * @param soures 源對象列表
     * @param c      目標類
     * @param <S>    源類型
     * @param <T>    目標類型
     * @return
     */
    public static <S, T> List<T> transform(List<S> soures, Class<? extends T> c) {
        if (CollectionUtils.isEmpty(soures)) return new ArrayList<>();
        return soures.stream().map(s -> {
            T t = null;
            try {
                t = c.newInstance();
            }  catch (InstantiationException | IllegalAccessException e) {
                log.error(e.getMessage(), e);
            }
            BeanUtils.copyProperties(s, t);
            return t;
        }).collect(Collectors.toList());
    }

    /**
     * 拷貝屬性
     *
     * @param soures 源對象列表
     * @param <S>    源類型
     * @return
     */
    public static <S> List<S> copy(List<S> soures) {
        if (CollectionUtils.isEmpty(soures)) return new ArrayList<>();
        return soures.stream().map(s -> {
            S s1 = null;
            try {
                s1 = (S)s.getClass().newInstance();
                BeanUtils.copyProperties(s, s1);
            }  catch (InstantiationException | IllegalAccessException e) {
                log.error(e.getMessage(), e);
            }
            return s1;
        }).collect(Collectors.toList());
    }

    /**
     * 拷貝對象
     *
     * @param soure  源對象
     * @param <S>    源類型
     * @return
     */
    public static <S> S copy(S soure) {
        if (soure == null) return null;
        S s1 = null;
        try {
            s1 = (S) soure.getClass().newInstance();
            BeanUtils.copyProperties(soure, s1);
        }  catch (InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return s1;
    }

    /**
     * @Description <p> 拷貝非空對象屬性值 </P>
     * @param source 源對象
     * @param target 目標對象
     */
    public static void copyIgnoreNull(Object source, Object target) {
        if (source == null || target == null) return;
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * @Description <p>獲取到對象中屬性為null的屬性名  </P>
     * @param source 要拷貝的對象
     * @return
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


}
