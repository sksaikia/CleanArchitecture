package com.example.githubapp.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.core.di.viewModel.ViewModelFactory
import com.example.githubapp.core.di.viewModel.ViewModelKey
import com.example.githubapp.feature_repositories.presentation.viewmodel.GithubRepoViewModel
import com.example.githubapp.feature_search.presentation.viewmodels.GithubUserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubUserViewModel::class)
    abstract fun bindUserViewModel(viewModel: GithubUserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GithubRepoViewModel::class)
    abstract fun bindRepoViewModel(viewModel: GithubRepoViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
