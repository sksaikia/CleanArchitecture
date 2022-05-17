package com.example.githubapp.feature_search.di

import com.example.githubapp.common.network.GithubAPI
import com.example.githubapp.feature_search.data.mapper.UserMapper
import com.example.githubapp.feature_search.data.repository.GithubUserRepositoryImpl
import com.example.githubapp.feature_search.domain.repository.GithubUserRepository
import com.example.githubapp.feature_search.domain.usecases.GithubUserUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GitHubUserModule {

    @Provides
    @Singleton
    fun provideGetUserUseCase(repository: GithubUserRepository): GithubUserUsecase {
        return GithubUserUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideGithubUserRepository(
        api : GithubAPI,
        mapper : UserMapper)
    : GithubUserRepository{
        return GithubUserRepositoryImpl(api,mapper)
    }

    @Provides
    @Singleton
    fun provideUserMapper() : UserMapper{
        return UserMapper()
    }
}