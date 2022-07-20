package com.example.githubapp.feature_repositories.presentation.ui.adapter.test

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.githubapp.R
import com.example.githubapp.feature_repositories.domain.model.Repo

class GithubRepoViewHolder(view: View) : AbstractViewHolder<Repo>(view) {

    val repositoryName : TextView = itemView.findViewById(R.id.repo_name)
    val language : TextView = itemView.findViewById(R.id.repo_language)
    override fun bind(element: Repo) {
        repositoryName.text = element.name
        language.text = element.language
    }

    companion object {
        @LayoutRes val LAYOUT = R.layout.listitem_repos
    }
}