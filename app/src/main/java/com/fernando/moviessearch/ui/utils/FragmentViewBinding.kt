package com.fernando.moviessearch.ui.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class FragmentViewBinding<V : ViewBinding> : Fragment() {
    protected lateinit var binding: V

    abstract fun inflate(inflater: LayoutInflater) : V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = inflate(inflater)
        return binding.root
    }
}