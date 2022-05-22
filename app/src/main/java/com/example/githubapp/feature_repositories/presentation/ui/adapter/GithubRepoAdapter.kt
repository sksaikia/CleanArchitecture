package com.example.githubapp.feature_repositories.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.feature_repositories.domain.model.Repo

class GithubRepoAdapter : RecyclerView.Adapter<GithubRepoAdapter.GithubRepoViewHolder>() {

    val repos = mutableListOf<Repo>()

    inner class GithubRepoViewHolder(
        itemView : View
    )  : RecyclerView.ViewHolder(itemView){

        val repositoryName : TextView = itemView.findViewById(R.id.repo_name)
        val language : TextView = itemView.findViewById(R.id.repo_language)

        fun bind(repo : Repo) {
            repositoryName.text = repo.name
            language.text = repo.language
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GithubRepoAdapter.GithubRepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_repos,parent,false)
        return GithubRepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubRepoAdapter.GithubRepoViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    fun setList(list: List<Repo>) {
        this.repos.clear()
        this.repos.addAll(list)
        notifyDataSetChanged()
    }
}