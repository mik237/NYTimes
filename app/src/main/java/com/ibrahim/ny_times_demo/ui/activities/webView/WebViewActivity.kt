package com.ibrahim.ny_times_demo.ui.activities.webView

import android.os.Bundle
import android.webkit.WebSettings
import androidx.core.view.isVisible
import com.ibrahim.ny_times_demo.databinding.ActivityWebViewBinding
import com.ibrahim.ny_times_demo.ui.base.BaseActivity
import com.ibrahim.ny_times_demo.util.Constants
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    private var articleUrl : String? = null


    override fun getViewBinding() = ActivityWebViewBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent?.getStringExtra(Constants.ARTICLE_URL)?.let { articleUrl = it }
        initViews()
        initArticlesWebView()
    }

    private fun initViews() {
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun initArticlesWebView() {
        articlesWebView.apply {
            webViewClient = ArticlesWebViewClient(webViewListener = object : WebViewListener{
                override fun onPageStarted() {
                    binding.progressBar.isVisible = true
                }

                override fun onPageFinished() {
                    binding.progressBar.isVisible = false
                }
            })

            isSaveFromParentEnabled = true
            settings.apply {
                javaScriptEnabled = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }
        articleUrl?.let { articlesWebView.loadUrl(it) }
    }



    override fun clearResources() {
        articlesWebView.loadUrl("about:blank")
        articlesWebView.destroy()
    }
}