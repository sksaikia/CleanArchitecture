package com.example.githubapp.feature_repositories.domain.model

import com.example.githubapp.core.data.BaseItemModel
import com.example.githubapp.feature_repositories.presentation.ui.adapter.factory.ItemTypeFactory

data class Test(val test: String): BaseItemModel {
    override fun type(typeFactory: ItemTypeFactory): Int {
        return typeFactory.type(this)
    }

}
