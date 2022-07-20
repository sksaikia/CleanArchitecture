package com.example.githubapp.feature_repositories.domain.usecases

import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_repositories.domain.repository.GithubRepoRepository
import com.example.githubapp.core.data.BaseItemModel

class GithubRepoUsecase(
    private val repository: GithubRepoRepository
) {

    suspend fun invoke(user: String) : Result<List<BaseItemModel>> {
        return repository.getRepoForUser(user)
    }

}