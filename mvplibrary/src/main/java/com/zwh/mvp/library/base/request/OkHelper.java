package com.zwh.mvp.library.base.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.zwh.mvp.library.base.response.callback.JsonCallback;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author Zhaohao
 * @Description: 请求封装工具类
 * @Date 2018/08/20 15:25
 */

public class OkHelper {


    /**
     * get 请求
     * @param tag   请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param params 参数键值对
     * @param callback 返回
     * @param <T>
     */
    public static <T> void getRequest(Object tag,String url, Map<String,String> params, JsonCallback<T> callback){
        OkGo.<T>get(url)
                .tag(tag)
                .cacheKey(url)
                .params(params)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .execute(callback);
    }


    /**
     * post 请求
     * @param tag   请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param params 参数键值对
     * @param callback 返回
     * @param <T>
     */
    public static <T> void postRequest(Object tag,String url, Map<String, String> params, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .params(params)
                .execute(callback);
    }

    /** json 请求
     * @param tag 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param jsonStr json字符串
     * @param callback 返回
     * @param <T>
     */
    public static <T> void jsonRequest(Object tag,String url, String jsonStr, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag) // 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
                .upJson(jsonStr)
                .execute(callback);
    }

    /**
     * @param tag 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param params 参数键值对
     * @param filesParamsKey 文件上传参数名称
     * @param files 文件列表
     * @param callback 返回
     */
    public static void upFilesRequest(Object tag,String url, Map<String, String> params,String filesParamsKey, List<File> files, StringCallback callback){
        PostRequest<String> request = OkGo.<String>post(url)
                .tag(tag)
                .cacheMode(CacheMode.NO_CACHE)
                .isMultipart(true)
                .params(params);
        for (File file : files) {
            request.params(filesParamsKey, file);
        }
        request.execute(callback);
    }

    /**
     * @param tag 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param params 参数键值对
     * @param filesParamsKey 文件上传参数名称
     * @param file 文件
     * @param callback 返回
     */
    public static void upFileRequest(Object tag,String url, Map<String, String> params,String filesParamsKey, File file, StringCallback callback){
         OkGo.<String>post(url)
                 .tag(tag)
                .cacheMode(CacheMode.NO_CACHE)
                .isMultipart(true)
                .params(params)
                .params(filesParamsKey, file)
                .execute(callback);
    }

    /**
     * @param tag 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url
     * @param callback
     */
    public static void downLoadFileRequest(Object tag,String url, FileCallback callback){
        OkGo.<File>get(url)
                .tag(tag)
                .execute(callback);
    }

    /**
     * 取消Tag请求
     * @param tag
     */
    public static void cancelRequest(Object tag){
        OkGo.getInstance().cancelTag(tag);
    }


    /**
     * 取消全部请求
     */
    public static void cancelAllRequest(){
        OkGo.getInstance().cancelAll();
    }
}
