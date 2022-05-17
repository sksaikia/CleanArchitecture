package com.example.githubapp.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.core.di.viewModel.ViewModelFactory
import com.example.githubapp.core.di.viewModel.ViewModelKey
import com.example.githubapp.feature_search.presentation.GithubUserViewModel
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
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}