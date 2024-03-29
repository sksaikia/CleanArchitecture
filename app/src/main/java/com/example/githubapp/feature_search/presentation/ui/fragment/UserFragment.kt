package com.example.githubapp.feature_search.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.githubapp.MainApplication
import com.example.githubapp.R
import com.example.githubapp.common.extensions.hide
import com.example.githubapp.common.extensions.show
import com.example.githubapp.core.network.Result
import com.example.githubapp.core.presentation.BaseDaggerFragment
import com.example.githubapp.core.utils.ImageHandler
import com.example.githubapp.databinding.FragmentUserBinding
import com.example.githubapp.feature_repositories.presentation.ui.RepositoryFragment
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.presentation.viewmodels.GithubUserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class UserFragment : BaseDaggerFragment<FragmentUserBinding, GithubUserViewModel>() {

    private fun setUpClickListeners() {
        binding.searchButton.setOnClickListener {
            var userName = "sksaikia"
            if (userName.isNotBlank()) {
                viewModel.getUserDetail(userName)

                lifecycleScope.launch {
                    viewModel.userFlow.collect {
                        when(it) {
                            is Result.Success -> {
                                onSuccessGetData(it.data)
                                Log.d("FATAL", "setUpClickListeners: SUCCESS")
                            }
                            is Result.Loading -> {
                                showProgressBar(true)
                                Log.d("FATAL", "setUpClickListeners: Loading")
                            }
                            is Result.Error -> {
                                onFailureGetData(it)
                                Log.d("FATAL", "setUpClickListeners: Error")
                            }
                        }
                    }
                }

//                viewModel.user.observe(viewLifecycleOwner, Observer {
//                    when (it) {
//                        is Result.Success -> onSuccessGetData(it.data)
//                        is Result.Loading -> showProgressBar(true)
//                        is Result.Error -> onFailureGetData(it)
//                    }
//                })
                binding.progressBar.show()
            }
        }

        binding.userDetails.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("user", "sksaikia")
            var repoFragment = RepositoryFragment()
            repoFragment.arguments = bundle
            goToRepoFrag(repoFragment)

        }
    }

    override fun init() {
        super.init()
        subscribeToLiveData()
        setUpClickListeners()
    }

    private fun goToRepoFrag(repoFragment: RepositoryFragment) {
        val fragmentName = repoFragment.toString()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, repoFragment)
            ?.addToBackStack(fragmentName)
            ?.commit()
    }


    private fun subscribeToLiveData() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> onSuccessGetData(it.data)
                is Result.Loading -> showProgressBar(true)
                is Result.Error -> onFailureGetData(it)
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        if (state)
            binding.progressBar.show()
        else
            binding.progressBar.hide()
    }

    private fun onFailureGetData(it: Result.Error<User>) {
        showProgressBar(false)
        Snackbar.make(binding.parent, "Error Occured ${it.message}", Snackbar.LENGTH_SHORT).show()
    }

    private fun onSuccessGetData(data: User?) {
        Log.d("FATAL SUCCESS", "success: " + data)
        showProgressBar(false)
        binding.userDetails.root.show()
        binding.userDetails.apply {
            userName.text = data?.name
            userRepositories.text = "Public Repositories : ${data?.public_repos}"
            if (data?.avatar_url != null) {
                ImageHandler.loadImage(data.avatar_url, userImage)
            }
        }

    }

    override fun initiateViewModel(viewModelProvider: ViewModelProvider) =
        viewModelProvider[GithubUserViewModel::class.java]

    override fun injectFragment() {
        (activity?.application as MainApplication).component.inject(this)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentUserBinding.inflate(layoutInflater)

}