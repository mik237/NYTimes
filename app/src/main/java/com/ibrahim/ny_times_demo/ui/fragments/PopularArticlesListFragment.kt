package com.ibrahim.ny_times_demo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.ny_times_demo.BuildConfig
import com.ibrahim.ny_times_demo.R
import com.ibrahim.ny_times_demo.adapters.OnItemClickListener
import com.ibrahim.ny_times_demo.adapters.PopularArticlesListAdapter
import com.ibrahim.ny_times_demo.api.Status
import com.ibrahim.ny_times_demo.databinding.FragmentPopularArticlesListBinding
import com.ibrahim.ny_times_demo.ui.base.BaseFragment
import com.ibrahim.ny_times_demo.ui.fragments.models.PopularArticle
import com.ibrahim.ny_times_demo.ui.viewmodel.PopularArticlesViewModel
import com.ibrahim.ny_times_demo.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_popular_articles_list.*
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class PopularArticlesListFragment : BaseFragment<FragmentPopularArticlesListBinding>(), OnItemClickListener {

    @Inject
    lateinit var articlesAdapter : PopularArticlesListAdapter

    private val mainViewModel : PopularArticlesViewModel by activityViewModels()

    private var _binding : FragmentPopularArticlesListBinding? = null
    private val binding get() = _binding!!


    override fun getViewBinding() = FragmentPopularArticlesListBinding.inflate(layoutInflater)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularArticlesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        if(mainViewModel.mappedData.isNullOrEmpty()) {
            fetchPopularArticles()
        }
    }

    private fun initViews() {

        swipeRefreshList.apply {
            setOnRefreshListener {
                swipeRefreshList.isRefreshing = false
                fetchPopularArticles()
            }
        }

        articlesAdapter.setOnItemClickListener(this)

        activity?.let {
            rvPopularArticles.apply {
                layoutManager = LinearLayoutManager(it)
                adapter = articlesAdapter
                addItemDecoration(DividerItemDecoration(it, LinearLayoutManager(it).orientation))
            }
        }
    }

    private fun fetchPopularArticles() {
        mainViewModel.getNYTimesMostPopularArticles(Constants.ALL_SECTIONS, Constants.PERIOD, BuildConfig.API_KEY)
    }

    private fun initObservers() {
        mainViewModel.popularArticlesLiveData.observe(viewLifecycleOwner, Observer{
            it?.let {status ->
                when(status){
                    is Status.Loading -> {
                        progressBar.isVisible = status.showLoading
                    }
                    is Status.Error -> {
                        status.errorMsg?.let {
                            rvPopularArticles.isVisible = false
                            tvError.apply {
                                isVisible = true
                                text = it
                            }
                        }
                    }
                    is Status.Success -> {
                        status.data?.let { data ->
                            rvPopularArticles.isVisible = true
                            tvError.isVisible = false
                            val articlesList : List<PopularArticle> = data as List<PopularArticle>
                            if(articlesList.isNotEmpty())
                                articlesAdapter.setArticlesList(articlesList)
                            if(mainViewModel.selectedArticle.value == null && articlesList.isNotEmpty())
                                mainViewModel.selectedArticle.value = articlesList[0]
                        }
                    }
                }
            }

        })
    }

    override fun onItemClicked(article: PopularArticle) {
        mainViewModel.selectedArticle.value = article
        if(!mainViewModel.isTwoPaneView)
            findNavController().navigate(R.id.popularArticlesDetailFragment)
    }

    override fun clearResources() {
        articlesAdapter.setOnItemClickListener(null)
        rvPopularArticles.adapter = null
        _binding = null
    }

}