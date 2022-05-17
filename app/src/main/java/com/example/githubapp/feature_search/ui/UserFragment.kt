package com.example.githubapp.feature_search.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.MainApplication
import com.example.githubapp.R
import com.example.githubapp.common.extensions.hide
import com.example.githubapp.common.extensions.show
import com.example.githubapp.core.network.Result
import com.example.githubapp.core.utils.injectViewModel
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.presentation.GithubUserViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class UserFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: GithubUserViewModel

    private lateinit var searchButton: MaterialButton
    private lateinit var searchText : EditText
    private lateinit var progressBar : ProgressBar
    private lateinit var userImage : ImageView
    private lateinit var userName : TextView
    private lateinit var userRepository : TextView
    private lateinit var parent : ConstraintLayout
    private lateinit var userDetails : CardView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MainApplication).component.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_user, container, false)

        initializeViews(view)
        setOnClickListeners()

        viewModel = injectViewModel(viewModelFactory)

        subscribeToLiveData()

        return view
    }

    private fun setOnClickListeners() {
        searchButton.setOnClickListener {
            var userName = searchText.text.toString().trim()
            if(userName.isNotBlank()){
                viewModel.getUserDetail(userName)
                progressBar.show()
            }
        }
    }

    private fun initializeViews(view: View) {
        searchButton = view.findViewById(R.id.search_button)
        searchText = view.findViewById(R.id.search_text)
        progressBar = view.findViewById(R.id.progress_bar)
        userName = view.findViewById(R.id.user_name)
        userImage = view.findViewById(R.id.user_image)
        userRepository = view.findViewById(R.id.user_repositories)
        parent = view.findViewById(R.id.parent)
        userDetails = view.findViewById(R.id.user_details)
    }

    private fun subscribeToLiveData() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> onSuccessGetData(it.data)
                is Result.Error -> onFailureGetData(it)
            }
        }
    }

    private fun onFailureGetData(it: Result.Error) {
        Log.d("FATAL TEST", "onFailureGetData: " + it)
        progressBar.hide()
        Snackbar.make(parent,"Error Occured",Snackbar.LENGTH_SHORT).show()
    }

    private fun onSuccessGetData(data: User) {
        userDetails.show()
        progressBar.hide()
        userName.text = data?.name
        userRepository.text = "Public Repositories : ${data?.public_repos}"
        //Set the image here
    }

}