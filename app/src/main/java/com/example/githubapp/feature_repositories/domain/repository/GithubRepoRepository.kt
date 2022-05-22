package com.example.githubapp.feature_repositories.domain.repository

import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_repositories.domain.model.Repo

interface GithubRepoRepository {
    suspend fun getRepoForUser(user: String) : Result<List<Repo>>
}