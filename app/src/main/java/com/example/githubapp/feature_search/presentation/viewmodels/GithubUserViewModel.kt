package com.example.githubapp.feature_search.presentation.viewmodels

import androidx.lifecycle.*
import com.example.githubapp.core.network.dispatchers.CoroutineDispatcherProvider
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.domain.usecases.GithubUserUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

//Inject it
class GithubUserViewModel @Inject constructor(
    private val githubUserUsecase: GithubUserUsecase,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel(){

    private val _user = MutableLiveData<Result<User>>()
    val user : LiveData<Result<User>> = _user

     fun getUserDetail(name: String) {
         viewModelScope.launch {
             githubUserUsecase.invoke(name).onStart {
                 _user.postValue(Result.Loading(true))
             }.flowOn(Dispatchers.IO)
                 .collect { result ->
                     when(result) {
                         is Result.Success -> {
                            _user.postValue(result)
                         }
                         is Result.Error -> {
                            _user.postValue(result)
                         }
                     }
                 }
         }
     }
}
