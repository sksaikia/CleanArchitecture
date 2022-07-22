package com.example.githubapp.feature_repositories.presentation.ui.adapter.factory

import android.view.View
import com.example.githubapp.core.presentation.viewholder.AbstractViewHolder
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.domain.model.Test
import com.example.githubapp.feature_repositories.domain.uimodel.RepoUiModel
import com.example.githubapp.feature_repositories.domain.uimodel.TestUiModel
import com.example.githubapp.feature_repositories.presentation.ui.adapter.factory.ItemTypeFactory
import com.example.githubapp.feature_repositories.presentation.ui.adapter.viewholders.GithubRepoViewHolder
import com.example.githubapp.feature_repositories.presentation.ui.adapter.viewholders.TestViewHolder

class ItemTypeFactoryImp : ItemTypeFactory {
    override fun type(repo: RepoUiModel): Int {
        return GithubRepoViewHolder.LAYOUT
    }

    override fun type(test: TestUiModel): Int {
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