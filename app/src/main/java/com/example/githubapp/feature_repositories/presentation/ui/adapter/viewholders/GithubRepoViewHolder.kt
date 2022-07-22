package com.example.githubapp.feature_repositories.presentation.ui.adapter.viewholders

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.githubapp.R
import com.example.githubapp.core.presentation.viewholder.AbstractViewHolder
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.domain.uimodel.RepoUiModel

class GithubRepoViewHolder(view: View) : AbstractViewHolder<RepoUiModel>(view) {

    val repositoryName : TextView = itemView.findViewById(R.id.repo_name)
    val language : TextView = itemView.findViewById(R.id.repo_language)
    override fun bind(element: RepoUiModel) {
        repositoryName.text = element.name
        language.text = element.language
    }

    companion object {
        @LayoutRes val LAYOUT = R.layout.listitem_repos
    }
}