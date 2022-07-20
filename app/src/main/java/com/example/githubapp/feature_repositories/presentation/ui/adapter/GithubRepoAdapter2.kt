package com.example.githubapp.feature_repositories.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.core.data.BaseItemModel
import com.example.githubapp.core.presentation.viewholder.AbstractViewHolder
import com.example.githubapp.feature_repositories.presentation.ui.adapter.factory.ItemTypeFactory

class GithubRepoAdapter2(
    private val adapterTypeFactory: ItemTypeFactory
) : RecyclerView.Adapter<AbstractViewHolder<BaseItemModel>>() {

    val repos = mutableListOf<BaseItemModel>()

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

    fun setList(list: List<BaseItemModel>) {
        this.repos.clear()
        this.repos.addAll(list)
        notifyDataSetChanged()
    }

}