package com.vjsarathi.colantask.uiUtils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.vjsarathi.colantask.R
import de.hdodenhof.circleimageview.CircleImageView

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:loadImageWithURL")
    fun CircleImageView.loadImageWithURL(url: String?) {
        if (url.isNullOrEmpty()) {
            Glide.with(context)
                .load(R.drawable.place_holder)
                .into(this)
        } else {
            Glide.with(context).load(url).into(this)
        }
    }
}