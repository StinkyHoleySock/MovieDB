package com.example.moviedb.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.moviedb.Constants
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment(R.layout.fragment_details) {

    private val binding: FragmentDetailsBinding by viewBinding()
    private val args: DetailsFragmentArgs by navArgs()
    lateinit var viewModel: DetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle? ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]

        viewModel.getMovieDetails(args.id)

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            with(binding) {

                tvDate.text = movie.release_date
                tvDescription.text = movie.overview
                tvTitle.text = movie.title

                val genresList = mutableListOf<String>()
                for (genre in movie.genres) {
                    genresList.add(genre.name)
                }
                val genres = genresList.joinToString()
                tvGenres.text = genres
                Log.d("develop", "genres: ${genresList.joinToString()}")

                Glide.with(ivPoster.context)
                    .load(Constants.IMAGE_BASE_URL+movie.poster_path)
                    .into(ivPoster)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {isLoading ->
            if (isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
            }
        }

    }

}