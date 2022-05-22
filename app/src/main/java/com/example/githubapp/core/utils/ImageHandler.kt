package com.example.githubapp.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.githubapp.R

object ImageHandler {

    fun loadImage(url : String, view: ImageView) {
        Glide.with(view.getContext())
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .dontAnimate()
            .error(R.drawable.ic_launcher_foreground)
            .into(view)
    }
}