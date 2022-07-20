package com.example.githubapp.feature_repositories.presentation.ui.adapter.test

import android.view.View
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.domain.model.Test

class ItemTypeFactoryImp : ItemTypeFactory {
    override fun type(repo: Repo): Int {
        return GithubRepoViewHolder.LAYOUT
    }

    override fun type(test: Test): Int {
        return TestViewHolder.LAYOUT
    }

    override fun createViewHolder(parent: View, type: Int): AbstractViewHolder<*> {
        return when(type) {
            GithubRepoViewHolder.LAYOUT -> GithubRepoViewHolder(parent)
            TestViewHolder.LAYOUT -> TestViewHolder(parent)
            //Todo Check this
            else -> createViewHolder(parent, type)
        }
    }
}