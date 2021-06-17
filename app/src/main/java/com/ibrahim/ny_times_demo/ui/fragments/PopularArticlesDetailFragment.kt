package com.ibrahim.ny_times_demo.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ibrahim.ny_times_demo.R
import com.ibrahim.ny_times_demo.databinding.FragmentPopularArticlesDetailBinding
import com.ibrahim.ny_times_demo.ui.activities.webView.WebViewActivity
import com.ibrahim.ny_times_demo.ui.base.BaseFragment
import com.ibrahim.ny_times_demo.ui.viewmodel.PopularArticlesViewModel
import com.ibrahim.ny_times_demo.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_popular_articles_detail.*


@AndroidEntryPoint
class PopularArticlesDetailFragment : BaseFragment<FragmentPopularArticlesDetailBinding>() {

    private val mainViewModel : PopularArticlesViewModel by activityViewModels()

    private var _binding : FragmentPopularArticlesDetailBinding? = null
    private val binding get() = _binding!!

    private var articleUrl : String? = null

    override fun getViewBinding() = FragmentPopularArticlesDetailBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularArticlesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.btnViewDetail.setOnClickListener {
            Intent(
                requireContext(),
                WebViewActivity::class.java)
                .apply {
                    putExtra(Constants.ARTICLE_URL, articleUrl)
                    startActivity(this)
                }
        }
    }

    private fun initObservers() {
        mainViewModel.selectedArticle.observe(viewLifecycleOwner, Observer{
            it?.let { article ->
                tvTitle.text =  article.title
                tvAuthor.text = article.byline
                tvAbstract.text = article.abstract
                tvPublishedDate.text = article.publish_data
                val imageUrl = article.imageUrlLarge ?: article.imageUrl
                articleUrl = article.url
                Glide.with(ivArticleImage)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(ivArticleImage)
            }
        })
    }

    override fun clearResources() {
        _binding = null
    }

}