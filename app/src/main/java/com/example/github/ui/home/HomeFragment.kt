package com.example.github.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import kotlinx.android.synthetic.main.main_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val userViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        view.button.setOnClickListener {
            userViewModel.fetch(view.usernameText.text.toString())
            val bundle = Bundle()
                bundle.putString("username", view.usernameText.text.toString())
            findNavController().navigate(R.id.action_nav_home_to_nav_profile, bundle)
        }
        return view
    }
}