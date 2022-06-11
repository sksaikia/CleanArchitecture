package com.example.githubapp.feature_search.domain.usecases

import androidx.lifecycle.LiveData
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_search.data.remote.dto.UserDTO
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow

class GithubUserUsecase(
    private val repository: GithubUserRepository
) {

    suspend operator fun invoke(word : String) : Flow<User> {
       return repository.getUserInfo(word)
    }

}