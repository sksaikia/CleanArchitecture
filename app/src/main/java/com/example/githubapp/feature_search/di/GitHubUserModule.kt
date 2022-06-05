package com.example.githubapp.feature_search.di

import android.app.Application
import androidx.room.Room
import com.example.githubapp.common.network.GithubAPI
import com.example.githubapp.feature_search.data.local.entity.GithubUserDao
import com.example.githubapp.feature_search.data.local.entity.GithubUserDatabase
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
        db : GithubUserDatabase,
        mapper : UserMapper)
    : GithubUserRepository{
        return GithubUserRepositoryImpl(api,db.dao,mapper)
    }

    @Provides
    @Singleton
    fun provideUserMapper() : UserMapper{
        return UserMapper()
    }

    @Provides
    @Singleton
    fun provideDao(app : Application) : GithubUserDatabase {
        return Room.databaseBuilder(
            app, GithubUserDatabase::class.java,"user_db"
        ).build()
    }

}