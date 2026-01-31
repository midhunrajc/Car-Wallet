package com.app.vehicle.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    @LayoutRes private val layoutRes: Int,
    private val bind: (View) -> VB
) : Fragment(layoutRes) {

    protected lateinit var binding: VB
        private set

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)
        onViewReady()
    }

    abstract fun onViewReady()
}
