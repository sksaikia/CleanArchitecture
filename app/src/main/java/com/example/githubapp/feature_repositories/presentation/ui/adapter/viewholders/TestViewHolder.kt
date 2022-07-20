package com.example.githubapp.feature_repositories.presentation.ui.adapter.viewholders

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.githubapp.R
import com.example.githubapp.core.presentation.viewholder.AbstractViewHolder
import com.example.githubapp.feature_repositories.domain.model.Test

class TestViewHolder(view : View) : AbstractViewHolder<Test>(view) {
    val textView : TextView = itemView.findViewById(R.id.title)
    override fun bind(element: Test) {
        textView.text = element.test
    }

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.listitem_repos2
    }
}