package com.example.githubapp.feature_repositories.domain.model

import com.example.githubapp.core.data.BaseItemModel
import com.example.githubapp.feature_repositories.domain.uimodel.RepoUiModel
import com.example.githubapp.feature_repositories.presentation.ui.adapter.factory.ItemTypeFactory

data class Repo(
    val name: String?,
    val language : String?,
    val owner : Owner?
) {
    fun toRepoUiModel() : RepoUiModel {
        return RepoUiModel(
            name,
            language,
            owner
        )
    }
}