package com.example.github.ui.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.ProfileFragmentBinding
import com.example.github.ui.profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : Fragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ProfileFragmentBinding.inflate(inflater, container, false)

        binding.include.followersView.textView2.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("username", arguments?.getString("username"))
            findNavController().navigate(R.id.action_nav_profile_to_nav_followers, bundle) }

        binding.include.followersView.textView3.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("username", arguments?.getString("username"))
            findNavController().navigate(R.id.action_nav_profile_to_nav_followers, bundle) }

        profileViewModel.userLiveData.observe(viewLifecycleOwner, { binding.user = it })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}