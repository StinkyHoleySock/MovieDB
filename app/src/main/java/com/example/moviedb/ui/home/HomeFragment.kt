package com.example.moviedb.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.model.Movie

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: HomeViewModel
    private val binding: FragmentHomeBinding by viewBinding()
    private val movieAdapter by lazy {
        MovieAdapter() {
            navigateToMovieDetails(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        binding.rvResults.apply {
            layoutManager = GridLayoutManager(
                context,
                3
            )
            adapter = movieAdapter
        }

        viewModel.movies.observe(viewLifecycleOwner) { moviesList ->
            movieAdapter.setData(moviesList)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {isLoading ->
            if (isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
                binding.rvResults.visibility = View.INVISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
                binding.rvResults.visibility = View.VISIBLE
            }
        }
    }

    private fun navigateToMovieDetails(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
        findNavController().navigate(action)
    }
}