package com.example.moviedb.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.moviedb.util.Constants
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentDetailsBinding
import com.example.moviedb.util.applyVisibility

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding: FragmentDetailsBinding by viewBinding()
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var viewModel: DetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]

        viewModel.getDetails(args.id, args.isMovie)

        if (args.isMovie) {
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

                    Glide.with(ivPoster.context)
                        .load(Constants.IMAGE_BASE_URL + movie.poster_path)
                        .error(R.drawable.ic_no_image)
                        .into(ivPoster)
                }
            }
        } else {
            viewModel.tvs.observe(viewLifecycleOwner) { tv ->
                with(binding) {

                    tvDate.text = "from: ${tv.first_air_date} to: ${tv.last_air_date}"
                    tvDescription.text = tv.overview
                    tvTitle.text = tv.name

                    val genresList = mutableListOf<String>()
                    for (genre in tv.genres) {
                        genresList.add(genre.name)
                    }
                    val genres = genresList.joinToString()
                    tvGenres.text = genres

                    Glide.with(ivPoster.context)
                        .load(Constants.IMAGE_BASE_URL + tv.poster_path)
                        .error(R.drawable.ic_no_image)
                        .into(ivPoster)
                }
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressCircular.applyVisibility(isLoading)
            binding.llContainer.applyVisibility(!isLoading)

        }
    }
}