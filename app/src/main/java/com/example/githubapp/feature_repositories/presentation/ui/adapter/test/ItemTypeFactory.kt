package com.example.githubapp.feature_repositories.presentation.ui.adapter.test

import android.view.View
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.domain.model.Test

interface ItemTypeFactory {
    fun type(repo : Repo): Int
    fun type(test: Test): Int
    fun createViewHolder(parent: View, type: Int): AbstractViewHolder<*>
}