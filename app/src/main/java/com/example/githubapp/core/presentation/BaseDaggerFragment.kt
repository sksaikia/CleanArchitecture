package com.example.githubapp.core.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.githubapp.core.di.viewModel.ViewModelFactory
import javax.inject.Inject

abstract class BaseDaggerFragment<VB : ViewBinding, VM : ViewModel> : BaseFragment<VB>() {
    @Inject
    lateinit var viewModelFactory : ViewModelFactory

    val viewModel: VM by lazy {
        initiateViewModel(ViewModelProvider(this, viewModelFactory))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectFragment()
    }

    abstract fun initiateViewModel(viewModelProvider: ViewModelProvider) : VM

    abstract fun injectFragment()

}