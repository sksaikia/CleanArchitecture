package com.example.githubapp.feature_repositories.domain.model

import com.example.githubapp.feature_repositories.data.remote.dto.OwnerDTO

data class Repo(
    val name: String?,
    val language : String?,
    val owner : Owner?
)