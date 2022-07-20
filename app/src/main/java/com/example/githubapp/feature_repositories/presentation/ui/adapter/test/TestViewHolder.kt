package com.example.githubapp.feature_repositories.presentation.ui.adapter.test

import android.view.View
import androidx.annotation.LayoutRes
import com.example.githubapp.R
import com.example.githubapp.feature_repositories.domain.model.Repo

class TestViewHolder(view : View) : AbstractViewHolder<Repo>(view) {
    override fun bind(element: Repo) {

    }

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.listitem_repos2
    }
}