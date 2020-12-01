package com.example.github.ui.followers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.R
import com.example.github.domain.entity.Followers
import com.example.github.ui.followers.viewmodel.FollowersViewModel
import kotlinx.android.synthetic.main.followers_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowersFragment : Fragment() {

    private val followersViewModel: FollowersViewModel by viewModel()

    private val followersList = mutableListOf<Followers>()
    lateinit var followersAdapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.followers_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("username")?.let { followersViewModel.fetch(it) }
        followersAdapter = FollowersAdapter(followersList)

        followersViewModel?.followersLiveData?.observe(viewLifecycleOwner, {
            it?.let {
                followersList.clear()
                followersList.addAll(it)
                followersAdapter.notifyDataSetChanged()
            }
        })

        followersViewModel?.errorLiveData.observe(viewLifecycleOwner,
            { if(it) Toast.makeText(context, "Error while loading!",
            Toast.LENGTH_LONG).show()})

        view.folowersList.layoutManager = LinearLayoutManager(context)
        view.folowersList.adapter = followersAdapter
    }
}