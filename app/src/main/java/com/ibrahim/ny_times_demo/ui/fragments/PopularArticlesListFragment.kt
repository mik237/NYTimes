package com.ibrahim.ny_times_demo.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_popular_articles_list.*
import javax.inject.Inject


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
            val recyclerViewState : Parcelable? = rvPopularArticles.layoutManager?.onSaveInstanceState()

            rvPopularArticles.layoutManager = LinearLayoutManager(it)
            rvPopularArticles.adapter = articlesAdapter
            rvPopularArticles.addItemDecoration(
                DividerItemDecoration(it, LinearLayoutManager(it).orientation)
            )

            recyclerViewState?.let {
                rvPopularArticles.layoutManager?.onRestoreInstanceState(it)
            }
        }
    }

    private fun fetchPopularArticles() {
        mainViewModel.getNYTimesMostPopularArticles("all-sections", 7, BuildConfig.API_KEY)
    }

    private fun initObservers() {
        mainViewModel.popularArticlesLiveData.observe(viewLifecycleOwner, {
            when(it){
                is Status.Loading -> {
                    progressBar.isVisible = it.showLoading
                }
                is Status.Success -> {
                    val list = it.data as List<PopularArticle>
                    articlesAdapter.setArticlesList(list)
                }
                is Status.Error -> {}
            }
        })
    }

    override fun onItemClicked(article: PopularArticle) {
        mainViewModel.selectedArticle.value = article
        findNavController().navigate(R.id.popularArticlesDetailFragment)
    }

    override fun clearResources() {
        articlesAdapter.setOnItemClickListener(null)
        rvPopularArticles.adapter = null
        _binding = null
    }

}