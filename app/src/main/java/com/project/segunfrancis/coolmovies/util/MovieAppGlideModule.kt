package com.project.segunfrancis.coolmovies.util

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MovieAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val memoryCacheSizeBytes = 10 * 1024 * 1024     // 10 MB
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
    }
}