package com.example.githubapp.core.di.modules

import com.example.githubapp.feature_search.data.repository.GithubUserRepositoryImpl
import com.example.githubapp.feature_search.domain.repository.GithubUserRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindRepository(repository : GithubUserRepositoryImpl) : GithubUserRepository
}