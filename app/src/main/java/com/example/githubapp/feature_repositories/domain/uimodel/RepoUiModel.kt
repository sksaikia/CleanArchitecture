package com.example.githubapp.feature_repositories.domain.uimodel

import com.example.githubapp.core.data.BaseItemModel
import com.example.githubapp.feature_repositories.domain.model.Owner
import com.example.githubapp.feature_repositories.presentation.ui.adapter.factory.ItemTypeFactory

data class RepoUiModel(
    val name: String?,
    val language : String?,
    val owner : Owner?
) : BaseItemModel {
    override fun type(typeFactory: ItemTypeFactory): Int {
        return typeFactory.type(this)
    }

}