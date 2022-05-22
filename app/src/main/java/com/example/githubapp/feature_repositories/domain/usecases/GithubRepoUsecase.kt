package com.example.githubapp.feature_repositories.domain.usecases

import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.domain.repository.GithubRepoRepository

class GithubRepoUsecase(
    private val repository: GithubRepoRepository
) {

    suspend fun invoke(user: String) : Result<List<Repo>> {
        return repository.getRepoForUser(user)
    }

}