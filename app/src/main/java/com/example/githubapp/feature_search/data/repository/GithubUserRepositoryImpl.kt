package com.example.githubapp.feature_search.data.repository

import com.example.githubapp.common.network.GithubAPI
import com.example.githubapp.core.network.RemoteDataSource
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_search.data.local.entity.GithubUserDao
import com.example.githubapp.feature_search.data.local.entity.GithubUserEntity
import com.example.githubapp.feature_search.data.mapper.UserMapper
import com.example.githubapp.feature_search.data.remote.dto.UserDTO
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.domain.repository.GithubUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubUserRepositoryImpl(
    private val api : GithubAPI,
    private val dao : GithubUserDao,
    private val mapper : UserMapper
) : GithubUserRepository, RemoteDataSource(){


    suspend fun getUserFromDb(name : String) : User {
        var user =  dao.getUser(name)
        if (user==null)
            user = GithubUserEntity(
                "",
                0,
                null
            )
        return mapper.mapUserEntityToDomain(user)
    }

    override suspend fun getUserInfo(name: String): Flow<User> = flow{

        emit(getUserFromDb(name))
        val apiResult = getDataFromApi(name)
        if (apiResult is Result.Success){
            val userData = mapper.mapUserDTOToEntity(apiResult.data)
            insertUserInfoIntoDB(userData)
        }

        emit(getUserFromDb(name))

    }

    private suspend fun insertUserInfoIntoDB(userData: GithubUserEntity) {
        dao.insertUserInfo(userData)
    }


    private suspend fun getDataFromApi(name: String): Result<UserDTO> {
        return safeApiCall { api.getUserDetails(name) }
    }

}
