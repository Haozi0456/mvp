package com.zwh.mvp.library.base.request;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.PostRequest;
import com.zwh.mvp.library.base.response.callback.JsonCallback;

import java.io.File;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;


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
    public static <T> void getRequest(Object tag, String url, Map<String, String> params, JsonCallback<T> callback){
        getRequest(tag,url,null,params,callback);
    }
    /**
     * get 请求
     * @param tag   请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param params 参数键值对
     * @param callback 返回
     * @param <T>
     */
    public static <T> void getRequest(Object tag, String url, @Nullable HttpHeaders headers, Map<String, String> params, JsonCallback<T> callback){
        OkGo.<T>get(url)
                .tag(tag)
                .cacheKey(url)
                .params(params)
                .headers(headers)
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
        postRequest(tag,url, null, params, callback);
    }

    /**
     * post 请求
     * @param tag   请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param params 参数键值对
     * @param callback 返回
     * @param <T>
     */
    public static <T> void postRequest(Object tag,String url, @Nullable HttpHeaders headers, Map<String, String> params, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .headers(headers)
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
        jsonRequest(tag,url,null, jsonStr, callback);
    }

    /** json 请求
     * @param tag 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param jsonStr json字符串
     * @param callback 返回
     * @param <T>
     */
    public static <T> void jsonRequest(Object tag,String url, @Nullable HttpHeaders headers, String jsonStr, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag) // 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
                .headers(headers)
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
    public static void upFilesRequest(Object tag, String url, Map<String, String> params, String filesParamsKey, List<File> files, StringCallback callback) {
        upFilesRequest(tag, url, null, params, filesParamsKey, files, callback);
    }

    /**
     * @param tag 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param params 参数键值对
     * @param filesParamsKey 文件上传参数名称
     * @param files 文件列表
     * @param callback 返回
     */
    public static void upFilesRequest(Object tag,String url, @Nullable HttpHeaders headers, Map<String, String> params,String filesParamsKey, List<File> files, StringCallback callback){
        PostRequest<String> request = OkGo.<String>post(url)
                .tag(tag)
                .cacheMode(CacheMode.NO_CACHE)
                .headers(headers)
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
     * @param files 文件列表
     * @param callback 返回
     */
    public static <T> void upFilesRequest(Object tag,String url, @Nullable HttpHeaders headers, Map<String, String> params,String filesParamsKey, List<File> files, JsonCallback<T> callback){
        PostRequest<T> request = OkGo.<T>post(url)
                .tag(tag)
                .cacheMode(CacheMode.NO_CACHE)
                .headers(headers)
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
     * @param filesMap 文件上传参数名称及参数集合
     * @param callback 返回
     */
    public static void upFilesRequest(Object tag,String url, @Nullable HttpHeaders headers, Map<String, String> params, Map<String,File> filesMap, StringCallback callback){
        PostRequest<String> request = OkGo.<String>post(url)
                .tag(tag)
                .cacheMode(CacheMode.NO_CACHE)
                .headers(headers)
                .isMultipart(true)
                .params(params);
        for (Map.Entry<String, File> entry : filesMap.entrySet()) {
            String mapKey = entry.getKey();
            File mapValue = entry.getValue();
            request.params(mapKey, mapValue);
        }
        request.execute(callback);
    }

    /**
     * @param tag 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param params 参数键值对
     * @param filesMap 文件上传参数名称及参数集合
     * @param callback 返回
     */
    public static <T> void upFilesRequest(Object tag,String url, @Nullable HttpHeaders headers, Map<String, String> params, Map<String,File> filesMap, JsonCallback<T> callback){
        PostRequest<T> request = OkGo.<T>post(url)
                .tag(tag)
                .cacheMode(CacheMode.NO_CACHE)
                .headers(headers)
                .isMultipart(true)
                .params(params);
        for (Map.Entry<String, File> entry : filesMap.entrySet()) {
            String mapKey = entry.getKey();
            File mapValue = entry.getValue();
            request.params(mapKey, mapValue);
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
        upFileRequest(tag, url, null, params, filesParamsKey,  file,  callback);
    }

    /**
     * @param tag 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url 请求地址
     * @param params 参数键值对
     * @param filesParamsKey 文件上传参数名称
     * @param file 文件
     * @param callback 返回
     */
    public static void upFileRequest(Object tag,String url, @Nullable HttpHeaders headers, Map<String, String> params,String filesParamsKey, File file, StringCallback callback){
         OkGo.<String>post(url)
                 .tag(tag)
                .cacheMode(CacheMode.NO_CACHE)
                .headers(headers)
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
    public static void downLoadFileRequest(Object tag, String url, FileCallback callback){
        downLoadFileRequest(tag,url,null,callback);
    }

    /**
     * @param tag 请求的tag，用于标识当前的请求，方便后续取消对应的请求，如果你不需要取消请求，也可以不用设置
     * @param url
     * @param callback
     */
    public static void downLoadFileRequest(Object tag,  String url,@Nullable HttpHeaders headers, FileCallback callback){
        OkGo.<File>get(url)
                .tag(tag)
                .headers(headers)
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
