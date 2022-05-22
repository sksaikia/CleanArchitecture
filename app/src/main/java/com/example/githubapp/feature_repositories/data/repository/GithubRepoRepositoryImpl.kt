package com.example.githubapp.feature_repositories.data.repository

import com.example.githubapp.common.network.GithubAPI
import com.example.githubapp.core.network.RemoteDataSource
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_repositories.data.mapper.RepoMapper
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.domain.repository.GithubRepoRepository

class GithubRepoRepositoryImpl(
    private val api : GithubAPI,
    private val mapper : RepoMapper
) : GithubRepoRepository, RemoteDataSource() {

    override suspend fun getRepoForUser(user: String) : Result<List<Repo>> {
        return safeApiCall{
            val repoDTOs = api.getRepoForUser(user)
            mapper.mapRepoListToDomain(repoDTOs)
        }
    }
}