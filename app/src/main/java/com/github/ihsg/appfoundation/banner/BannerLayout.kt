package com.github.ihsg.appfoundation.banner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.ihsg.appfoundation.R
import kotlinx.android.synthetic.main.sub_banner_layout.view.*

class BannerLayout : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.sub_banner_layout, this)
    }

    fun update(fragment: Fragment) {
        val viewModel: BannerVM = ViewModelProviders.of(fragment).get(BannerVM::class.java)
        viewModel.load()?.observe(fragment, Observer {
            this.bannerView.startAction(
                this.progressBar,
                it
            )
        })
    }
}