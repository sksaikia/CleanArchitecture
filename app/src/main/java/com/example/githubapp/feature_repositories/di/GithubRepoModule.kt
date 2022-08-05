package com.example.githubapp.feature_repositories.di

import com.example.githubapp.common.network.GithubAPI
import com.example.githubapp.feature_repositories.data.mapper.RepoMapper
import com.example.githubapp.feature_repositories.data.repository.GithubRepoRepositoryImpl
import com.example.githubapp.feature_repositories.domain.repository.GithubRepoRepository
import com.example.githubapp.feature_repositories.domain.usecases.GithubRepoUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GithubRepoModule {

    @Provides
    @Singleton
    fun provideRepoUsecase(
        githubRepoRepository: GithubRepoRepository
    ) : GithubRepoUsecase{
        return GithubRepoUsecase(githubRepoRepository)
    }

//    @Provides
//    @Singleton
//    fun provideGithubRepoRepository(
//        api : GithubAPI,
//        mapper : RepoMapper
//    ) : GithubRepoRepository {
//        return GithubRepoRepositoryImpl(api,mapper)
//    }

    @Provides
    @Singleton
    fun provideRepoMapper() : RepoMapper {
        return RepoMapper()
    }

}