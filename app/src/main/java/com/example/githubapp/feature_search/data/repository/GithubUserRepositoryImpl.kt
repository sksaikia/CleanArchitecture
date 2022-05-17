package com.example.githubapp.feature_search.data.repository

import com.example.githubapp.common.network.GithubAPI
import com.example.githubapp.core.network.RemoteDataSource
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_search.data.mapper.UserMapper
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.domain.repository.GithubUserRepository

class GithubUserRepositoryImpl(
    private val api : GithubAPI,
    private val mapper : UserMapper
) : GithubUserRepository, RemoteDataSource(){

    override suspend fun getUserInfo(name: String): Result<User> {

        return safeApiCall {
            val userDTO = api.getUserDetails(name)
            mapper.mapUserToDomain(userDTO)
        }
    }
}