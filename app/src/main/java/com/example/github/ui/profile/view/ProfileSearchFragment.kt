package com.example.github.ui.profile.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.ui.profile.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.profile_search_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProfileSearchFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_search_fragment, container, false)

        profileViewModel.userFetchLiveData.observe(viewLifecycleOwner) {
            val username = view.usernameText.text.toString()
            it?.let {
                findNavController().navigate(R.id.action_nav_search_profile_to_nav_profile,
                    Bundle().apply { putString("username", username) })
                profileViewModel.reset()
            }
        }

        profileViewModel.errorLiveData.observe(viewLifecycleOwner) {
            if(it ) Toast.makeText(
                context, "Invalid User or Error, Please try again!",
                Toast.LENGTH_SHORT
            ).show()
        }
        view.button.setOnClickListener {

            val username = view.usernameText.text.toString()

            if(username.isNotEmpty())  {
                profileViewModel.fetch(username.trim())
                val imm = requireActivity()
                    .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0);
            }
            else Toast.makeText(
                context, "Please enter Github username!",
                Toast.LENGTH_SHORT
            ).show()
        }
        return view
    }
}