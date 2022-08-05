package com.example.githubapp.feature_repositories.di

import com.example.githubapp.feature_repositories.data.repository.GithubRepoRepositoryImpl
import com.example.githubapp.feature_repositories.domain.repository.GithubRepoRepository
import com.example.githubapp.feature_search.data.repository.GithubUserRepositoryImpl
import com.example.githubapp.feature_search.domain.repository.GithubUserRepository
import dagger.Binds
import dagger.Module

@Module
interface RepoRepositoryModule {

    @Binds
    fun bindRepository(repository : GithubRepoRepositoryImpl) : GithubRepoRepository
}