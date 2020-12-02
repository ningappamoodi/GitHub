package com.example.github.ui.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.ui.home.HomeViewModel
import com.example.github.ui.profile.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.main_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileSearchFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        view.button.setOnClickListener {
            profileViewModel.fetch(view.usernameText.text.toString())
            val bundle = Bundle()
                bundle.putString("username", view.usernameText.text.toString())
            findNavController().navigate(R.id.action_nav_home_to_nav_profile, bundle)
        }
        return view
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