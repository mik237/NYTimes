package com.ibrahim.ny_times_demo.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>: Fragment() {

    protected lateinit var mViewBinding: VB

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewBinding    =   getViewBinding()
    }

    /**
     * It returns [VB] which is assigned to [mViewBinding] and used in [onCreate]
     */
    abstract fun getViewBinding(): VB
    abstract fun clearResources()

    override fun onDestroyView() {
        super.onDestroyView()
        clearResources()
    }

}