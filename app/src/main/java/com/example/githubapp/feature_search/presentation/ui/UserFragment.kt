package com.example.githubapp.feature_search.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.MainApplication
import com.example.githubapp.R
import com.example.githubapp.common.extensions.hide
import com.example.githubapp.common.extensions.show
import com.example.githubapp.core.network.Result
import com.example.githubapp.core.presentation.BaseDaggerFragment
import com.example.githubapp.core.utils.injectViewModel
import com.example.githubapp.databinding.FragmentUserBinding
import com.example.githubapp.feature_repositories.presentation.ui.RepositoryFragment
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.presentation.viewmodels.GithubUserViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class UserFragment : BaseDaggerFragment<FragmentUserBinding, GithubUserViewModel>() {

    private fun setUpClickListeners() {
        binding.searchButton.setOnClickListener {
            var userName = binding.searchText.text.toString().trim()
            if(userName.isNotBlank()){
                viewModel.getUserDetail(userName)
                binding.progressBar.show()
            }
        }

        binding.userDetails.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("user",binding.searchText.text.toString())
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
        activity?.supportFragmentManager?.
        beginTransaction()?.
        replace(R.id.container,repoFragment)
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

    private fun showProgressBar(state : Boolean) {
        if (state)
            binding.progressBar.show()
        else
            binding.progressBar.hide()
    }

    private fun onFailureGetData(it: Result.Error) {
        Log.d("FATAL TEST", "onFailureGetData: " + it)
        showProgressBar(false)
        Snackbar.make(binding.parent,"Error Occured",Snackbar.LENGTH_SHORT).show()
    }

    private fun onSuccessGetData(data: User) {
        Log.d("FATAL SUCCESS", "success: " + data)
        showProgressBar(false)
        binding.userDetails.root.show()
        binding.userDetails.userName.text = data?.name
        binding.userDetails.userRepositories.text = "Public Repositories : ${data?.public_repos}"
        //Set the image here
    }

    override fun initiateViewModel(viewModelProvider: ViewModelProvider) =
        viewModelProvider[GithubUserViewModel::class.java]

    override fun injectFragment() {
        (activity?.application as MainApplication).component.inject(this)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentUserBinding.inflate(layoutInflater)

}