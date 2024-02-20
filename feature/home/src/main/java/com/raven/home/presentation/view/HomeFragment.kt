package com.raven.home.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.raven.home.R
import com.raven.home.databinding.HomeFragmentBinding
import com.raven.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment)  {


    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        getNews("7")
        //binding = HomeFragmentBinding.bind(view)
        //initViews()
    }

    private fun initViews() {
        binding.searchIcon.setOnClickListener {
            val query = binding.query
            if(query != null){
                getNews(query)
            }

        }
    }

    private  fun getNews(query: String) {
        viewModel.getMoreProducts(query)
    }

}


