package com.ibrahim.ny_times_demo.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    abstract fun getViewBinding() : VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
    }

    abstract fun clearResources()

    override fun onDestroy() {
        super.onDestroy()
        clearResources()
    }
}