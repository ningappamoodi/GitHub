package com.example.github.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.databinding.UserItemBinding
import com.example.github.domain.entity.User

class UsersAdapter (private val followersList: List<User>):
    RecyclerView.Adapter<UsersAdapter.UserVH>() {

    class UserVH(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
            Glide.with(binding.imageView2.context).load(user.avatar_url)
                .circleCrop().into(binding.imageView2)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH = UserVH(
        UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
    )

    override fun onBindViewHolder(holder: UserVH, position: Int) =
        holder.bind(followersList[position])

    override fun getItemCount(): Int = followersList.size
}