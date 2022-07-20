package com.example.githubapp.feature_repositories.presentation.ui.adapter.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.domain.model.WrapperData

class GithubRepoAdapter2(
    private val adapterTypeFactory: ItemTypeFactory
) : RecyclerView.Adapter<AbstractViewHolder<BaseItemModel>>() {

    val repos = mutableListOf<Repo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder<BaseItemModel> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return adapterTypeFactory.createViewHolder(view, viewType) as AbstractViewHolder<BaseItemModel>
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<BaseItemModel>, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemViewType(position: Int): Int {
        return repos[position].type(adapterTypeFactory)
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