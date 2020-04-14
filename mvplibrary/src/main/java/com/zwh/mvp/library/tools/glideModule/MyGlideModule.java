package com.zwh.mvp.library.tools.glideModule;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import androidx.annotation.NonNull;

/**
 * @author Zhaohao
 * @Description: Glide 缓存大小和路径配置类
 * @date 2016-09-06 12:50
 */
@com.bumptech.glide.annotation.GlideModule
public class MyGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int cacheSize100MegaBytes = 104857600*2; //磁盘缓存200M
        String downloadDirectoryPath = context.getExternalCacheDir().getPath();
        //设置磁盘缓存大小和路径
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize100MegaBytes));

        builder.setMemoryCache(new LruResourceCache(50 * 1024 * 1024));

    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }
}


//@GlideModule
//public class GiphyGlideModule extends AppGlideModule {
//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
//        builder.setMemoryCache(new LruResourceCache(10 * 1024 * 1024));
//    }
//
//    @Override
//    public void registerComponents(Context context, Registry registry) {
//        registry.append(Api.GifResult.class, InputStream.class, new GiphyModelLoader.Factory());
//    }
//}