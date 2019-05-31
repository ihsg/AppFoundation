package com.github.ihsg.appfoundation.common.net

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.ihsg.appfoundation.R
import com.github.ihsg.appfoundation.common.api.LoadState

class ImageWorker private constructor() {
    companion object {
        val instance: ImageWorker by lazy { ImageWorker() }
    }

    fun loadToImageView(
            context: Context,
            url: String,
            imageView: ImageView,
            loadState: MutableLiveData<LoadState>?
    ) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.bg_banner_placeholder)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    loadState?.postValue(LoadState.error(e?.message))
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    loadState?.postValue(LoadState.LOADED)
                    return false
                }

            })
            .into(imageView)
    }
}