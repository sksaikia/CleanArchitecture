package com.example.githubapp.feature_repositories.domain.model

import com.example.githubapp.feature_repositories.presentation.ui.adapter.test.BaseItemModel
import com.example.githubapp.feature_repositories.presentation.ui.adapter.test.ItemTypeFactory

data class Test(val test: String): BaseItemModel {
    override fun type(typeFactory: ItemTypeFactory): Int {
        return typeFactory.type(this)
    }

}
