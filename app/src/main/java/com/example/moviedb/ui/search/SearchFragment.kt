package com.example.moviedb.ui.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentSearchBinding

class SearchFragment: Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    lateinit var viewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]


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

}