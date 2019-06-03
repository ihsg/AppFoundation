package com.github.ihsg.appfoundation.common.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageUrl")
fun bindImageUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }
}