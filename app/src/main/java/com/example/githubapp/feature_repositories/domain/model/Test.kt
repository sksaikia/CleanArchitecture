package com.example.githubapp.feature_repositories.domain.model

import com.example.githubapp.core.data.BaseItemModel
import com.example.githubapp.feature_repositories.domain.uimodel.TestUiModel
import com.example.githubapp.feature_repositories.presentation.ui.adapter.factory.ItemTypeFactory

data class Test(val test: String){
    fun toTestUiModel() : TestUiModel {
        return TestUiModel(test)
    }
}
