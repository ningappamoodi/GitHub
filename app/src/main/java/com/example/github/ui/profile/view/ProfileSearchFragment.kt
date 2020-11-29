package com.example.github.ui.profile.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.github.R
import com.example.github.ui.profile.viewmodel.ProfileSearchViewModel

class ProfileSearchFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileSearchFragment()
    }

    private lateinit var viewModel: ProfileSearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileSearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}