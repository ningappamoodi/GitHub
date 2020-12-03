package com.example.github.ui.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.databinding.UserItemBinding
import com.example.github.domain.entity.User

class UsersAdapter(private val followersList: List<User>, private val navigateToProfile: (String) -> Unit):
    RecyclerView.Adapter<UsersAdapter.UserVH>() {

    class UserVH(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User,  navigateToProfile: (String) -> Unit) {
            binding.user = user

            binding.userItem.setOnClickListener {
                navigateToProfile(user.login)
            }

            binding.executePendingBindings()
            Glide.with(binding.imageView2.context).load(user.avatar_url)
                .circleCrop().into(binding.imageView2)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {

        val binding =  UserItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
       return UserVH(binding)
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) =
        holder.bind(followersList[position], navigateToProfile)

    override fun getItemCount(): Int = followersList.size
}