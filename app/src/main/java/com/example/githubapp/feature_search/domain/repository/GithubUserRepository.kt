package com.example.githubapp.feature_search.domain.repository

import androidx.lifecycle.LiveData
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_search.data.local.entity.GithubUserEntity
import com.example.githubapp.feature_search.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {
    suspend fun getUserInfo(name: String) : Flow<User>
}