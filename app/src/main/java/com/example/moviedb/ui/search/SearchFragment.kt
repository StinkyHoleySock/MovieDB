package com.example.moviedb.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentSearchBinding
import com.example.moviedb.util.applyVisibility

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    lateinit var viewModel: SearchViewModel
    private val movieAdapter by lazy {
        MovieAdapter() { movie ->
            navigateToDetails(movie.id, true)
        }
    }
    private val tvAdapter by lazy {
        TvAdapter() { tv ->
            navigateToDetails(tv.id, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(
                context,
                3
            )
            adapter = movieAdapter
        }

        binding.rvTv.apply {
            layoutManager = GridLayoutManager(
                context,
                3
            )
            adapter = tvAdapter
        }

        viewModel.movies.observe(viewLifecycleOwner) { moviesList ->
            binding.tvMoviesTitle.applyVisibility(moviesList.isNotEmpty())
            movieAdapter.setData(moviesList)
        }

        viewModel.tv.observe(viewLifecycleOwner) { tvList ->
            binding.tvTvsTitle.applyVisibility(tvList.isNotEmpty())
            tvAdapter.setData(tvList)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressCircular.applyVisibility(isLoading)
            binding.llContainer.applyVisibility(!isLoading)
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getMoviesListByQuery(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
    }

    private fun navigateToDetails(id: Long, isMovie: Boolean) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id, isMovie)
        findNavController().navigate(action)
    }
}