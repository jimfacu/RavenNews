package com.raven.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.raven.home.R
import com.raven.home.Utils.ResultStatus
import com.raven.home.data.Model.News
import com.raven.home.databinding.HomeFragmentBinding
import com.raven.home.presentation.adapters.NewsAdapter
import com.raven.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), NewsAdapter.OnItemClickListener {


    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private lateinit var newsAdapter: NewsAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initObservers()
        initViews()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiStateHome.collect { list ->
                setRecycler(list)
            }
        }
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

    private fun setRecycler(newList: List<News>){
        newsAdapter = NewsAdapter(this)
        binding.recyclerViewNews.adapter = productsAdapter
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewNews.setNewProductList(newList)
    }

    override fun onItemClick(news: News) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(news)
        findNavController().navigate(action)
    }

}


