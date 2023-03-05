package com.example.moviedb.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentSearchBinding
import com.example.moviedb.ui.home.MovieAdapter

class SearchFragment: Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    lateinit var viewModel: SearchViewModel
    private val searchAdapter by lazy {
        SearchAdapter() { movie ->
            navigateToDetails(movie.id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]

        binding.rvResults.apply {
            layoutManager = GridLayoutManager(
                context,
                3
            )
            adapter = searchAdapter
        }

        viewModel.movies.observe(viewLifecycleOwner) { moviesList ->
            searchAdapter.setData(moviesList)

            if (moviesList.isEmpty()) {
                binding.tvNoResults.visibility = View.VISIBLE
            } else {
                binding.tvNoResults.visibility = View.GONE
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {isLoading ->
            if (isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
                binding.rvResults.visibility = View.GONE
            } else {
                binding.progressCircular.visibility = View.GONE
                binding.rvResults.visibility = View.VISIBLE
            }
        }

        binding.rvResults.setOnClickListener {
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getMoviesList(query)
                return false
            }
            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
    }

    private fun navigateToDetails(id: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }
}