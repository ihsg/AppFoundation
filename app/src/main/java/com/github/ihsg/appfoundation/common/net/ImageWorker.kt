package com.github.ihsg.appfoundation.common.net

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.ihsg.appfoundation.R

class ImageWorker private constructor() {
    companion object {
        val instance: ImageWorker by lazy { ImageWorker() }

        interface Listener {
            fun onEnd()
        }
    }

    fun loadToImageView(context: Context, url: String, imageView: ImageView, listener: Listener) {
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
                    listener.onEnd()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    listener.onEnd()
                    return false
                }

            })
            .into(imageView)
    }
}