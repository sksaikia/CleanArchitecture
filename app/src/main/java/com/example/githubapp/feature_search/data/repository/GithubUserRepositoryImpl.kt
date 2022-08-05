package com.example.githubapp.feature_search.data.repository

import android.util.Log
import com.example.githubapp.common.network.GithubAPI
import com.example.githubapp.core.network.RemoteDataSource
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_search.data.local.entity.GithubUserDatabase
import com.example.githubapp.feature_search.data.mapper.UserMapper
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor (
    private val api: GithubAPI,
    private val db: GithubUserDatabase,
    private val mapper: UserMapper
) : GithubUserRepository, RemoteDataSource() {

    val dao = db.dao

    override suspend fun getUserInfo(name: String): Flow<Result<User>> = flow<Result<User>> {

        emit(Result.Loading(true))

        safeApiCall({
            val userFromApi =  api.getUserDetails(name)
            userFromApi.let { userData ->
                dao.deleteUser(name)
                dao.insertUserInfo(mapper.mapUserDTOToEntity(userData))
                emit(Result.Success(data = mapper.mapUserEntityToDomain(dao.getUser(name))))
                emit(Result.Loading(false))
            }
        },{ exception ->
            Log.d("fatal22", "getUserInfo: $exception")
            emit(Result.Error(exception))
        } )
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
