package com.ibrahim.ny_times_demo.ui.activities.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.ibrahim.ny_times_demo.R
import com.ibrahim.ny_times_demo.ui.base.BaseActivity
import com.ibrahim.ny_times_demo.databinding.ActivityMainBinding
import com.ibrahim.ny_times_demo.ui.viewmodel.PopularArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mainViewModel : PopularArticlesViewModel by viewModels()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val showDetailFragment = resources.getBoolean(R.bool.show_detail_fragment)

        mainViewModel.isTwoPaneView = showDetailFragment

        findNavController(R.id.fragment)
                .popBackStack(R.id.popularArticlesListFragment, false)
    }

    override fun clearResources() {

    }

}