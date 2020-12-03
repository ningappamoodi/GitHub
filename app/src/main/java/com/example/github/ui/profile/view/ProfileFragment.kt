package com.example.github.ui.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.databinding.ProfileFragmentBinding
import com.example.github.ui.profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = ProfileFragmentBinding.inflate(inflater, container, false)

        binding.progressBar.visibility = View.VISIBLE
        binding.profileLayout.visibility = View.GONE
        profileViewModel.userLiveData.observe(viewLifecycleOwner, {
            it?.let {
                binding.user = it
                bindData()
                binding.progressBar.visibility = View.GONE
                binding.profileLayout.visibility = View.VISIBLE
        }})

        return binding.root
    }

    private fun bindData() {
        binding.user?.let {
            Glide.with(this).load(it.avatar_url).circleCrop().into(binding.imgProfile)

            if (it.followers > 0) {
                context?.resources?.getColor(R.color.maroon)?.run {
                    binding.include.followersView.count.setTextColor(this)
                }
                binding.include.followersView.customText.setOnClickListener {

                    val bundle = Bundle()
                    bundle.putString("username", binding.user?.login)
                    findNavController().navigate(R.id.action_nav_profile_to_nav_followers, bundle)
                }

            }

            if (it.following > 0) {
                context?.resources?.getColor(R.color.maroon)?.run {
                    binding.include.followingView.count.setTextColor(this)
                }

                binding.include.followingView.customText.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("username", binding.user?.login)
                    findNavController().navigate(R.id.action_nav_profile_to_nav_following, bundle)
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("username")?.let { profileViewModel.fetch(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}