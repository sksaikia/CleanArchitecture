package com.example.githubapp.feature_search.data.repository

import android.util.Log
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
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GithubUserRepositoryImpl(
    private val api: GithubAPI,
    private val dao: GithubUserDao,
    private val mapper: UserMapper
) : GithubUserRepository, RemoteDataSource() {

    suspend fun getUserFromDb(name: String): User {
        var user = dao.getUser(name)
        return mapper.mapUserEntityToDomain(user)
    }

    override suspend fun getUserInfo(name: String): Flow<Result<User>> = channelFlow {

        send(Result.Loading(true))

        safeApiCall( {
            val userFromApi =  api.getUserDetails(name)
            userFromApi.let { userData ->
                dao.deleteUser(name)
                dao.insertUserInfo(mapper.mapUserDTOToEntity(userData))
                send(Result.Success(data = mapper.mapUserEntityToDomain(dao.getUser(name))))
                send(Result.Loading(false))
            }
        },{ exception ->
            Log.d("fatal22", "getUserInfo: $exception")
            send(Result.Error(exception))
        })
//        if (userFromApi==null){
//            emit(Result.Error("Tatakae",null))
//        }

//        if(userFromApi is Result.Success){
//            userFromApi?.data?.let { userData ->
//                dao.deleteUser(name)
//                dao.insertUserInfo(mapper.mapUserDTOToEntity(userData))
//                emit(Result.Success(data = mapper.mapUserEntityToDomain(dao.getUser(name))))
//                emit(Result.Loading(true))
//            }
//        }else {
//            emit(mapper.mapUserDTOToDomain(userFromApi))
//        }


    }

}
