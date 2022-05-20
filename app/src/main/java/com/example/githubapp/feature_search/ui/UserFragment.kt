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
import com.example.githubapp.databinding.FragmentUserBinding
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
    }

//    private fun initializeViews(view: View) {
//        searchButton = view.findViewById(R.id.search_button)
//        searchText = view.findViewById(R.id.search_text)
//        progressBar = view.findViewById(R.id.progress_bar)
//        userName = view.findViewById(R.id.user_name)
//        userImage = view.findViewById(R.id.user_image)
//        userRepository = view.findViewById(R.id.user_repositories)
//        parent = view.findViewById(R.id.parent)
//        userDetails = view.findViewById(R.id.user_details)
//    }

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
        binding.progressBar.hide()
        Snackbar.make(binding.parent,"Error Occured",Snackbar.LENGTH_SHORT).show()
    }

    private fun onSuccessGetData(data: User) {
        Log.d("FATAL SUCCESS", "success: " + data)
        binding.progressBar.hide()
        binding.userDetails.root.show()
        binding.userDetails.userName.text = data?.name
        binding.userDetails.userRepositories.text = "Public Repositories : ${data?.public_repos}"
        //Set the image here
    }

}