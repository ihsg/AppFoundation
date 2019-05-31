package com.github.ihsg.appfoundation.banner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.github.ihsg.appfoundation.common.net.ImageWorker
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader

class BannerView : Banner {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    fun startAction(progressBar: ProgressBar?, bannerList: List<BannerEntity>) {
        //1. show loading
        progressBar?.let { it.visibility = View.VISIBLE }

        //2. set image loader
        this.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                if (context != null && imageView != null && path != null && (path is String) && path.isNotEmpty()) {
                    ImageWorker.instance.loadToImageView(
                        context,
                        path,
                        imageView, null
                    )
                }
            }
        })

        //3. set images
        this.setImages(
            bannerList.filter { it.type?.code.equals("HOME") }
                .mapNotNull { it.imageUrl }
        )

        //4. start
        this.start()
    }
}