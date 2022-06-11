package com.example.githubapp.feature_search.presentation.viewmodels

import androidx.lifecycle.*
import com.example.githubapp.core.network.dispatchers.CoroutineDispatcherProvider
import com.example.githubapp.core.network.Result
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.domain.usecases.GithubUserUsecase
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
         // _user.value = Result.Loading

         viewModelScope.launch {
             githubUserUsecase.invoke(name).onStart {
                 _user.postValue( Result.Loading)
             }.flowOn(dispatcherProvider.default)
                 .collect {
                     _user.postValue( Result.Success(it))
                 }
         }
     }
}


//         viewModelScope.launch {
//             when(val response = githubUserUsecase.invoke(name)){
//                 is Result.Success -> {
//                     _user.value = response
//                 }
//                 is Result.Error -> {
//                     _user.value = Result.Error(response.cause,response.code,response.errorMessage)
//                 }
//             }
//         }
//         viewModelScope.launch {
//                val response = githubUserUsecase.invoke(name)
//
//


//             val userName = Transformations.switchMap(_user, {
//                 is Result.Success -> {
//                    _user
//             }
//                 is Result.Error -> {
//
//             }
//             })
//         }
