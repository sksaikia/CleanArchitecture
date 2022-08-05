package com.example.githubapp.feature_search.presentation.viewmodels

import androidx.lifecycle.*
import com.example.githubapp.core.network.dispatchers.CoroutineDispatcherProvider
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.domain.usecases.GithubUserUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class GithubUserViewModel @Inject constructor(
    private val githubUserUsecase: GithubUserUsecase
) : ViewModel(){

    private val _user = MutableLiveData<Result<User>>()
    val user : LiveData<Result<User>> = _user

    private val _userFlow = MutableStateFlow<Result<User>>(Result.Loading(true))
    val userFlow = _userFlow.asSharedFlow()

     fun getUserDetail(name: String) {

         _userFlow.value = Result.Loading(true)

         viewModelScope.launch {
             githubUserUsecase.invoke(name)
                 .collect { result ->
                     when(result) {
                         is Result.Success -> {
                            _userFlow.value = result
                         }
                         is Result.Error -> {
                             _userFlow.value = result
                         }
                     }
                 }
         }

//         viewModelScope.launch {
//             githubUserUsecase.invoke(name).onStart {
//                 _user.postValue(Result.Loading(true))
//             }.collect { result ->
//                     when(result) {
//                         is Result.Success -> {
//                            _user.postValue(result)
//                         }
//                         is Result.Error -> {
//                            _user.postValue(result)
//                         }
//                     }
//                 }
//         }
     }
}

data class GithubUserState (
    val data : User? = null,
    val idLoading : Boolean = false
)
