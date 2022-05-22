package com.example.githubapp.feature_search.ui

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
import com.example.githubapp.core.utils.injectViewModel
import com.example.githubapp.databinding.FragmentUserBinding
import com.example.githubapp.feature_repositories.ui.RepositoryFragment
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.presentation.GithubUserViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class UserFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: GithubUserViewModel

    private lateinit var binding: FragmentUserBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MainApplication).component.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   val view =  inflater.inflate(R.layout.fragment_user, container, false)
        binding = FragmentUserBinding.inflate(layoutInflater)
        viewModel = injectViewModel(viewModelFactory)

        subscribeToLiveData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()

    }


    private fun setOnClickListeners() {
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

}