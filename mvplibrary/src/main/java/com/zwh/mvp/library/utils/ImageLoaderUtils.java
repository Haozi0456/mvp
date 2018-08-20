package com.zwh.mvp.library.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zwh.mvp.library.R;

import java.io.File;


/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils {
    public static void displayWithVisible(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        if (TextUtils.isEmpty(url)) {
            imageView.setVisibility(View.GONE);
            return;
        }
        imageView.setVisibility(View.VISIBLE);
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder)
                .error(error);
        Glide.with(context).load(url).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder)
                .error(error);
        Glide.with(context).load(url).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
//        Glide.with(context).load(url)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
//                .error(R.drawable.ic_empty_picture)
//                .crossFade().into(imageView);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.ic_image_loading)
                .centerCrop()
                .error(R.drawable.ic_empty_picture);
        Glide.with(context).load(url).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }

    public static void display(Context context, ImageView imageView, File url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
//        Glide.with(context).load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
//                .error(R.drawable.ic_empty_picture)
//                .crossFade().into(imageView);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.ic_image_loading)
                .centerCrop()
                .error(R.drawable.ic_empty_picture);
        Glide.with(context).load(url).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }

    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
//        Glide.with(context).load(url).asBitmap()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.ic_image_loading)
//                .error(R.drawable.ic_empty_picture)
//                .thumbnail(0.5f)
//                .into(imageView);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture);
        Glide.with(context).asBitmap().load(url).thumbnail(0.5f).apply(options).into(imageView);

    }

    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
//        Glide.with(context).load(url).asBitmap()
//                .format(DecodeFormat.PREFER_ARGB_8888)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.ic_image_loading)
//                .error(R.drawable.ic_empty_picture)
//                .into(imageView);

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture);
        Glide.with(context).asBitmap().load(url).thumbnail(0.5f).apply(options).into(imageView);
    }

    public static void display(Context context, ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
//        Glide.with(context).load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
//                .error(R.drawable.ic_empty_picture)
//                .crossFade().into(imageView);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_image_loading)
                .centerCrop()
                .error(R.drawable.ic_empty_picture);
        Glide.with(context).load(url).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
//        Glide.with(context).load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.drawable.toux2)
//                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .transform(new GlideRoundTransformUtil(context))
                .error(R.drawable.toux2);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
//        Glide.with(context).load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(placeholder)
//                .error(error)
//                .centerCrop()
//                .transform(new GlideRoundTransformUtil(context)).into(imageView);

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(placeholder)
                .transform(new GlideRoundTransformUtil(context))
                .error(error);
        Glide.with(context).load(url).apply(options).into(imageView);

    }

}
