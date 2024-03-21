package com.vjsarathi.colantask.uiUtils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.vjsarathi.colantask.R

open class ColanImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attributeSet, defStyle) {

    fun loadImageWithURL(url: String?) {
        if (url.isNullOrEmpty()) {
            Glide.with(context).load(R.drawable.place_holder).into(this)
        } else {
            Glide.with(context).load(url).into(this)
        }
    }

}