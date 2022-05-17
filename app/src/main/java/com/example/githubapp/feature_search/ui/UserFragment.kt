package com.example.githubapp.feature_search.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.MainApplication
import com.example.githubapp.R
import com.example.githubapp.core.network.Result
import com.example.githubapp.core.utils.injectViewModel
import com.example.githubapp.feature_search.presentation.GithubUserViewModel
import javax.inject.Inject


class UserFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: GithubUserViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MainApplication).component.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_user_activity, container, false)

        viewModel = injectViewModel(viewModelFactory)

        viewModel.getUserDetail("sksaikia")

        subscribeToLiveData()

        return view
    }

    private fun subscribeToLiveData() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> Log.d("FATAL", "subscribeToLiveData: " + it.data.toString())
                is Result.Error -> Log.d("FATAL", it.errorMessage.toString())
            }
        }
    }

}