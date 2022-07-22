package com.example.githubapp.feature_repositories.presentation.ui.adapter.viewholders

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.githubapp.R
import com.example.githubapp.core.presentation.viewholder.AbstractViewHolder
import com.example.githubapp.feature_repositories.domain.model.Test
import com.example.githubapp.feature_repositories.domain.uimodel.TestUiModel

class TestViewHolder(view : View) : AbstractViewHolder<TestUiModel>(view) {
    val textView : TextView = itemView.findViewById(R.id.title)
    override fun bind(element: TestUiModel) {
        textView.text = element.test
    }

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.listitem_repos2
    }
}