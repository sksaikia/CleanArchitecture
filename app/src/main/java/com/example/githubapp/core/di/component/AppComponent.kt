package com.example.githubapp.core.di.component

import android.app.Application
import com.example.githubapp.core.di.modules.NetworkModule
import com.example.githubapp.core.di.modules.RepositoryModule
import com.example.githubapp.core.di.modules.ViewModelModule
import com.example.githubapp.feature_repositories.di.GithubRepoModule
import com.example.githubapp.feature_repositories.di.RepoRepositoryModule
import com.example.githubapp.feature_repositories.presentation.ui.RepositoryFragment
import com.example.githubapp.feature_search.di.GitHubUserModule
import com.example.githubapp.feature_search.presentation.ui.fragment.UserFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    GitHubUserModule::class,
    ViewModelModule::class,
    GithubRepoModule::class,
    RepositoryModule::class,
    RepoRepositoryModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(userFragment : UserFragment)

    fun inject(repoFragment : RepositoryFragment)

}