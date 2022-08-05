package com.example.githubapp.feature_repositories.data.repository

import com.example.githubapp.common.network.GithubAPI
import com.example.githubapp.core.network.RemoteDataSource
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_repositories.data.mapper.RepoMapper
import com.example.githubapp.feature_repositories.domain.model.Test
import com.example.githubapp.feature_repositories.domain.repository.GithubRepoRepository
import com.example.githubapp.core.data.BaseItemModel
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val api : GithubAPI,
    private val mapper : RepoMapper
) : GithubRepoRepository, RemoteDataSource() {

    override suspend fun getRepoForUser(user: String): Result<List<BaseItemModel>> {

        val dataFromApi = mapper.mapRepoListToDomain(api.getRepoForUser(user))
        var result = mutableListOf<BaseItemModel>()
        var state = false
        dataFromApi.forEach {
            result.add(it.toRepoUiModel())
            if (state) {
                result.add(Test("${it.hashCode()}").toTestUiModel())
            }
            state = !state
        }
        return Result.Success(result)
    }
}
