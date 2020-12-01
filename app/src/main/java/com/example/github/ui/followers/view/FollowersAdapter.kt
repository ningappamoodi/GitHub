package com.example.github.ui.followers.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.github.databinding.FollowersItemBinding
import com.example.github.domain.entity.Followers
import com.example.github.domain.entity.User

class FollowersAdapter(private val followersList: List<Followers>):
    RecyclerView.Adapter<FollowersAdapter.FollowersVH>(){

    class FollowersVH(private val binding: FollowersItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(followers: Followers) {
            binding.followers = followers
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersVH = FollowersVH(
        FollowersItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))

    override fun onBindViewHolder(holder: FollowersVH, position: Int) =
        holder.bind(followersList[position])

    override fun getItemCount(): Int = followersList.size
}
