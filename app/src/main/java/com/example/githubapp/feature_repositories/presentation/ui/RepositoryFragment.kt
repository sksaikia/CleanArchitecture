package com.example.githubapp.feature_repositories.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.MainApplication
import com.example.githubapp.common.extensions.hide
import com.example.githubapp.common.extensions.show
import com.example.githubapp.core.network.Result
import com.example.githubapp.core.presentation.BaseDaggerFragment
import com.example.githubapp.core.utils.injectViewModel
import com.example.githubapp.databinding.FragmentRepositoryBinding
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.domain.model.WrapperData
import com.example.githubapp.feature_repositories.presentation.viewmodel.GithubRepoViewModel
import com.example.githubapp.feature_repositories.presentation.ui.adapter.GithubRepoAdapter
import com.example.githubapp.feature_repositories.presentation.ui.adapter.test.GithubRepoAdapter2
import com.example.githubapp.feature_repositories.presentation.ui.adapter.test.ItemTypeFactoryImp
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RepositoryFragment : BaseDaggerFragment<FragmentRepositoryBinding,GithubRepoViewModel>() {

    private var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            username = bundle.getString("user", "")
        }else {

        }
    }

    override fun init() {
        super.init()
        subscribeToLiveData()
        viewModel.getUserRepos(username)
    }

    private fun subscribeToLiveData() {
        viewModel.repos.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> onSuccessGetData(it.data)
                is Result.Loading -> showProgressBar(true)
                is Result.Error -> onErrorGetData(it)
            }
        }
    }

    private fun showProgressBar(state : Boolean) {
        if (state)
            binding.progressBar.show()
        else
            binding.progressBar.hide()
    }

    private fun onErrorGetData(result: Result.Error<List<Repo>>) {
        Log.d("FATAL TEST", "onFailureGetData: " + result)
        showProgressBar(false)
    }

    private fun onSuccessGetData(data: List<Repo>?) {
        Log.d("FATAL", "onSuccessGetData: $data")
        showProgressBar(false)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val adapter = GithubRepoAdapter2(ItemTypeFactoryImp())
        if (data != null) {
            adapter.setList(data)
        }
        binding.recyclerView.adapter = adapter

    }

    override fun initiateViewModel(viewModelProvider: ViewModelProvider) =
        viewModelProvider[GithubRepoViewModel::class.java]

    override fun injectFragment() {
        (activity?.application as MainApplication).component.inject(this)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentRepositoryBinding.inflate(layoutInflater)

}