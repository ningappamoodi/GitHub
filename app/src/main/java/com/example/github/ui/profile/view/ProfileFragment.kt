package com.example.github.ui.profile.view

import androidx.fragment.app.Fragment
import com.example.github.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : Fragment() {
    private val profileViewModel: HomeViewModel by sharedViewModel()
}