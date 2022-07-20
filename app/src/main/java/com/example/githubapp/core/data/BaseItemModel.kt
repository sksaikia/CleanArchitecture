package com.example.githubapp.core.data

import com.example.githubapp.feature_repositories.presentation.ui.adapter.factory.ItemTypeFactory

interface BaseItemModel {
    fun type(typeFactory : ItemTypeFactory) : Int
}