package com.example.githubapp.feature_repositories.ui

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
import com.example.githubapp.core.network.Result
import com.example.githubapp.core.utils.injectViewModel
import com.example.githubapp.databinding.FragmentRepositoryBinding
import com.example.githubapp.feature_repositories.domain.model.Repo
import com.example.githubapp.feature_repositories.presentation.viewmodel.GithubRepoViewModel
import com.example.githubapp.feature_repositories.ui.adapter.GithubRepoAdapter
import javax.inject.Inject

class RepositoryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: GithubRepoViewModel

    private lateinit var binding : FragmentRepositoryBinding
    private var username = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MainApplication).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            username = bundle.getString("user", "")
        }else {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRepositoryBinding.inflate(layoutInflater)
        viewModel = injectViewModel(viewModelFactory)

        viewModel.getUserRepos(username)

        subscribeToLiveData()

        return binding.root

    }

    private fun subscribeToLiveData() {
        viewModel.repos.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> onSuccessGetData(it.data)
                is Result.Error -> onErrorGetData(it)
            }
        }
    }

    private fun onErrorGetData(result: Result.Error) {
        Log.d("FATAL TEST", "onFailureGetData: " + result)
    }

    private fun onSuccessGetData(data: List<Repo>) {
        Log.d("FATAL", "onSuccessGetData: $data")
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val adapter = GithubRepoAdapter()
        adapter.setList(data)
        binding.recyclerView.adapter = adapter

    }

}