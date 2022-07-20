package com.example.githubapp.feature_repositories.presentation.ui.adapter.test

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder<in T>(private val view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(element : T)
}