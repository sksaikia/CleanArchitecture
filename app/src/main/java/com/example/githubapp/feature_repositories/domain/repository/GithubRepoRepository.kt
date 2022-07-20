package com.example.githubapp.feature_repositories.domain.repository

import com.example.githubapp.core.network.Result
import com.example.githubapp.core.data.BaseItemModel

interface GithubRepoRepository {
    suspend fun getRepoForUser(user: String) : Result<List<BaseItemModel>>
}