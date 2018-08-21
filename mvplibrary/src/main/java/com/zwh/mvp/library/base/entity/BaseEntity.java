package com.zwh.mvp.library.base.entity;

import com.zwh.mvp.library.utils.JsonConvertUtils;

/**
 * @author Zhaohao
 * @Description: 基类工具类
 * @Date 2018/08/21 15:27
 */

public class BaseEntity {


    public String toString(){
        return JsonConvertUtils.toJson(this);
    }
}
