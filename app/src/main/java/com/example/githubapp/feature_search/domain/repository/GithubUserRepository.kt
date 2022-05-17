package com.example.githubapp.feature_search.domain.repository

import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_search.domain.model.User

interface GithubUserRepository {
    suspend fun getUserInfo(name: String) : Result<User>
}