package com.example.github.ui.following.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.databinding.FollowingItemBinding
import com.example.github.domain.entity.Following

class FollowingAdapter(private val followersList: List<Following>):
    RecyclerView.Adapter<FollowingAdapter.FollowingVH>(){

    class FollowingVH(private val binding: FollowingItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(following: Following) {
            binding.following = following
            binding.executePendingBindings()
            Glide.with(binding.imageView2.context).load(following.avatar_url).into(binding.imageView2)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingVH = FollowingVH(
        FollowingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))

    override fun onBindViewHolder(holder: FollowingVH, position: Int) =
        holder.bind(followersList[position])

    override fun getItemCount(): Int = followersList.size
}
