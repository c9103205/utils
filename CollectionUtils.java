//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cx.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.*;

public class CollectionUtils {
    private static final Logger log = LoggerFactory.getLogger(CollectionUtils.class);

    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    public static List<String> getNoRepeatList(List<String> originList) {
        if (originList == null) {
            throw new IllegalArgumentException("傳入的集合對象不能為空!");
        } else {
            Set<String> set = new HashSet(originList);
            return new ArrayList(set);
        }
    }

    public static <T> List<T> getNoRepeat(List<T> originList) {
        if (originList == null) {
            throw new IllegalArgumentException("傳入的集合對象不能為空!");
        } else {
            Set<T> set = new HashSet(originList);
            return new ArrayList(set);
        }
    }

    public static String getStringFromListBySplit(List<?> list, String splitStr) {
        Assert.notEmpty(list,"集合不能為空");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < list.size(); ++i) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(splitStr);
            }
        }

        return sb.toString();
    }

    public static String getStringFromListByDefalutSplit(List<?> list) {
        return getStringFromListBySplit(list, ",");
    }




    public static <T> List<T> getPropertyList(Collection<?> beanList, String fieldName) {
        Assert.notNull(fieldName, "fieldName can not be null!");
        List<T> propertyList = new ArrayList();
        if (org.springframework.util.CollectionUtils.isEmpty(beanList)) {
            return propertyList;
        } else {
            Iterator var3 = beanList.iterator();

            while(var3.hasNext()) {
                Object object = var3.next();
                if (object == null) {
                    log.warn("Get object from list is null!which fieldName:" + fieldName);
                    propertyList.add(null);
                } else {
                    propertyList.add((T)ReflectUtils.getFieldValue(object, fieldName));
                }
            }

            return propertyList;
        }
    }



    public static <T> boolean addIgnoreNull(Collection<T> collection, T object) {
        if (collection == null) {
            throw new NullPointerException("The collection must not be null");
        } else {
            return object != null && collection.add(object);
        }
    }





    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size();
        int page = (listSize + (pageSize - 1)) / pageSize;
        List<List<T>> listArray = new ArrayList();

        for(int i = 0; i < page; ++i) {
            List<T> subList = new ArrayList();

            for(int j = 0; j < listSize; ++j) {
                int pageIndex = (j + 1 + (pageSize - 1)) / pageSize;
                if (pageIndex == i + 1) {
                    subList.add(list.get(j));
                }

                if (j + 1 == (j + 1) * pageSize) {
                    break;
                }
            }

            listArray.add(subList);
        }

        return listArray;
    }

    /**
     * 將list轉換為map
     * @param <K> map的key
     * @param <V> map的value
     * @param list 要轉換的list
     * @param propertyName list中對象的屬性名,同時該屬性值也是map的key
     * @return 指定key對應的對象集合
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> changeListToMap(List<V> list,String propertyName){
        Assert.hasText(propertyName, "propertyName can not be null!");
        Map<K, V> map = new HashMap<>();
        if(isEmpty(list)) {
            return map;
        }
        for (V v : list) {
            if(v != null) {
                K key = (K) ReflectUtils.getFieldValue(v, propertyName);
                map.put(key, v);
            }
        }
        return map;
    }
}
