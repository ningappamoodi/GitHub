package com.example.github.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.R
import com.example.github.domain.entity.User
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val usersViewModel: UsersViewModel by viewModel()

    private val userList = mutableListOf<User>()
    lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersViewModel.fetch()
        usersAdapter = UsersAdapter(userList)

        usersViewModel.usersLiveData.observe(viewLifecycleOwner, {
            it?.let {
                userList.clear()
                userList.addAll(it)
                usersAdapter.notifyDataSetChanged()
            }
        })

        usersViewModel.errorLiveData.observe(viewLifecycleOwner,
            { if(it) Toast.makeText(context, "Error while loading!",
                Toast.LENGTH_LONG).show()})

        view.userList.layoutManager = LinearLayoutManager(context)
        view.userList.adapter = usersAdapter
    }
    override fun onResume() {
        super.onResume()
        homeViewModel.hideFab(false)
    }

    override fun onPause() {
        super.onPause()
        homeViewModel.hideFab(true)
    }
}