package com.sjl.platform.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Glide配置
 *
 * @author SJL
 * @date 2018/3/29
 */
public class GlideConfig implements GlideModule {

    // 需要在AndroidManifest.xml中声明
    // <meta-data
    //    android:name="com.sjl.platform.util.GlideConfig"
    //    android:value="GlideModule" />

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //自定义缓存目录
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
                "img",
                100 * 1000 * 1000));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
