package com.zys.taimeiknowledge.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yisong.zhang
 * @since 2021/4/16 10:15
 * ThreadLocal的工具类(上下文内容)
 */
public class SystemContext {

    private static final ThreadLocal<Map<String,String>> contextMap = new ThreadLocal();
    private static Logger logger = LoggerFactory.getLogger(SystemContext.class);
    private static Integer MAX_CAPACITY = 100;
    private static Integer MAX_SIZE = 1024;


    public SystemContext() {
    }

    public static void setContextMap(Map<String, String> map) {
        contextMap.set(map);
    }

    public static Map<String, String> getContextMap() {
        return contextMap.get();
    }

    public static String get(String key) {
        Map<String, String> contextMap = getContextMap();
        return contextMap == null ? null : contextMap.get(convertKey(key));
    }

    private static String convertKey(String key) {
        return key.toLowerCase();
    }


    public static String put(String key, String value) {
        if (key == null) {
            logger.error("key: is null, can't put it into the context map");
            return value;
        } else if (key.length() > MAX_SIZE) {
            throw new RuntimeException("key is more than " + MAX_SIZE + ", can't put it into the context map");
        } else if (value != null && value.length() > MAX_SIZE) {
            throw new RuntimeException("value is more than " + MAX_SIZE + ", can't put it into the context map");
        } else {
            Map<String, String> contextMap = getContextMap();
            if (contextMap == null) {
                contextMap = new HashMap(16);
                SystemContext.contextMap.set(contextMap);
            }

            if (((Map)contextMap).size() > MAX_CAPACITY) {
                throw new RuntimeException("the context map is full, can't put anything");
            } else if (value == null) {
                ((Map)contextMap).remove(convertKey(key));
                return null;
            } else {
                return (String)((Map)contextMap).put(convertKey(key), value);
            }
        }
    }

    public static void remove(String key) {
        if (key == null) {
            logger.error("key: is null, can't remove");
        } else {
            Map<String, String> contextMap = getContextMap();
            if (contextMap != null) {
                contextMap.remove(convertKey(key));
            }

        }
    }

    public static void clean(String key) {
        if (key == null) {
            logger.error("key is null,can't remove");
        } else {
            Map<String, String> map = (Map)contextMap.get();
            if (map != null) {
                map.remove(convertKey(key));
            }

        }
    }


}
