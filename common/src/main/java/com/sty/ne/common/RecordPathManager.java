package com.sty.ne.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局路径记录器（根据子模块分组）
 * @Author: tian
 * @UpdateDate: 2020/10/10 10:37 PM
 */
public class RecordPathManager {
    //key: "order"组  value: order子模块下对应所有的Activity路径信息
    private static Map<String, List<PathBean>> groupMap = new HashMap<>();

    /**
     * 将路径信息加入全局Map
     * @param groupName 组名，如：“personal”
     * @param pathName 路径名，如：“Personal_MainActivity”
     * @param clazz 类对象，如：Personal_MainActivity.class
     */
    public static void joinGroup(String groupName, String pathName, Class<?> clazz) {
        List<PathBean> list = groupMap.get(groupName);
        if(list == null) {
            list = new ArrayList<>();
            list.add(new PathBean(pathName, clazz));
            groupMap.put(groupName, list);
        }else {
            for (PathBean pathBean : list) {
                if(!pathName.equals(pathBean.getPath())) {
                    list.add(new PathBean(pathName, clazz));
                    groupMap.put(groupName, list);
                }
            }
        }
    }

    public static Class<?> getTargetClass(String groupName, String pathName) {
        List<PathBean> list = groupMap.get(groupName);
        if(list == null) {
            return null;
        }
        for (PathBean pathBean : list) {
            if(pathName.equalsIgnoreCase(pathBean.getPath())) {
                return pathBean.getClazz();
            }
        }
        return null;
    }
}
