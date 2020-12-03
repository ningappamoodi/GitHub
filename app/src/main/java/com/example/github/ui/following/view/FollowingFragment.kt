package com.example.github.ui.following.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.R
import com.example.github.domain.entity.Following
import com.example.github.ui.following.viewmodel.FollowingViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.following_fragment.*
import kotlinx.android.synthetic.main.following_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowingFragment: Fragment() {
    private val followingViewModel: FollowingViewModel by viewModel()

    private val followingList = mutableListOf<Following>()
    lateinit var followingAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.following_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetch()
        followingAdapter = FollowingAdapter(followingList)

        followingViewModel?.followingLiveData?.observe(viewLifecycleOwner, {
            it?.let {
                followingList.clear()
                followingList.addAll(it)
                followingAdapter.notifyDataSetChanged()
                view.progressBar.visibility = View.GONE
            }
        })

        followingViewModel?.errorLiveData.observe(viewLifecycleOwner,
            { if(it) retryLoadData(view)})

        view.followingList.layoutManager = LinearLayoutManager(context)
        view.followingList.adapter = followingAdapter
    }

    private fun fetch() = arguments?.getString("username")
        ?.let { progressBar.visibility = View.VISIBLE
            followingViewModel.fetch(it) }

    private fun retryLoadData(view: View) {
        progressBar.visibility = View.GONE
        Snackbar.make(view, getString(R.string.no_data), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                arguments?.getString("username")?.let { fetch() }
            }
            .show()
    }
}