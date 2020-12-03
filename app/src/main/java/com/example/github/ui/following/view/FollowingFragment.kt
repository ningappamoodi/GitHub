package com.example.github.ui.following.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.R
import com.example.github.domain.entity.Following
import com.example.github.ui.following.viewmodel.FollowingViewModel
import kotlinx.android.synthetic.main.following_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowingFragment: Fragment() {
    private val followingViewModel: FollowingViewModel by viewModel()

    private val followingList = mutableListOf<Following>()
    lateinit var followersAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.following_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("username")?.let { followingViewModel.fetch(it) }
        followersAdapter = FollowingAdapter(followingList)

        followingViewModel?.followingLiveData?.observe(viewLifecycleOwner, {
            it?.let {
                followingList.clear()
                followingList.addAll(it)
                followersAdapter.notifyDataSetChanged()
            }
        })

        followingViewModel?.errorLiveData.observe(viewLifecycleOwner,
            { if(it) Toast.makeText(context, "Error while loading!",
                Toast.LENGTH_LONG).show()})

        view.followingList.layoutManager = LinearLayoutManager(context)
        view.followingList.adapter = followersAdapter
    }
}