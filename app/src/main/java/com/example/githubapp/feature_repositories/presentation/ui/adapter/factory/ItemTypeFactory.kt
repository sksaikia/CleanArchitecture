package com.example.githubapp.feature_repositories.presentation.ui.adapter.factory

import android.view.View
import com.example.githubapp.core.presentation.viewholder.AbstractViewHolder
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.domain.model.Test
import com.example.githubapp.feature_repositories.domain.uimodel.RepoUiModel
import com.example.githubapp.feature_repositories.domain.uimodel.TestUiModel

interface ItemTypeFactory {
    fun type(repo : RepoUiModel): Int
    fun type(test: TestUiModel): Int
    fun createViewHolder(parent: View, type: Int): AbstractViewHolder<*>
}