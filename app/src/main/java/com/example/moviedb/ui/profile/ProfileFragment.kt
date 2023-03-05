package com.example.moviedb.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentProfileBinding
import com.example.moviedb.util.applyVisibility

class ProfileFragment: Fragment(R.layout.fragment_profile) {

    lateinit var viewModel: ProfileViewModel
    private val binding: FragmentProfileBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()

        binding.btnAuth.setOnClickListener {
            // TODO: fix auth
//            viewModel.validateWithLogin(username, password)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.applyVisibility(isLoading)
        }
    }
}