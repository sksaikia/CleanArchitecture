package com.example.githubapp.feature_repositories.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_repositories.domain.usecases.GithubRepoUsecase
import com.example.githubapp.core.data.BaseItemModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class GithubRepoViewModel @Inject constructor(
    private val githubRepoUsecase: GithubRepoUsecase
) : ViewModel(){

    private val _repos = MutableLiveData<Result<List<BaseItemModel>>>()
    val repos : LiveData<Result<List<BaseItemModel>>> = _repos

    fun getUserRepos(user : String) {
        if (user.isBlank()){
            return
        }
        _repos.value = Result.Loading(true)
        viewModelScope.launch {
            when(val response = githubRepoUsecase.invoke(user)) {
                is Result.Success -> {
                    _repos.value = response
                }
                is Result.Error -> {
                    _repos.value = Result.Error(response.message.toString())
                }
            }
        }
    }

}