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
import com.example.moviedb.model.movie.Movie
import com.example.moviedb.util.applyVisibility

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: HomeViewModel
    private val binding: FragmentHomeBinding by viewBinding()
    private val movieAdapter by lazy {
        MovieAdapter() { movie ->
            navigateToMovieDetails(movie.id, true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(
                context,
                3
            )
            adapter = movieAdapter
        }

        viewModel.movies.observe(viewLifecycleOwner) { moviesList ->
            movieAdapter.setData(moviesList)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressCircular.applyVisibility(isLoading)
            binding.rvMovies.applyVisibility(!isLoading)

        }
    }

    private fun navigateToMovieDetails(id: Long, isMovie: Boolean) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id, isMovie)
        findNavController().navigate(action)
    }
}