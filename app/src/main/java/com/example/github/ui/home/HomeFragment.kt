package com.example.github.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.github.R

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.hideFab(false)
    }

    override fun onPause() {
        super.onPause()
        homeViewModel.hideFab(true)
    }
}