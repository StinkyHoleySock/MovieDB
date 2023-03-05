package com.example.moviedb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.databinding.ItemMovieBinding
import com.example.moviedb.model.movie.Movie

class MovieAdapter(
    private val movieClickListener: (movieItem: Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var list: MutableList<Movie> = mutableListOf()

    fun setData(data: List<Movie>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                //todo fix link
                val imageLink = movie.poster_path
                tvMovieName.text = movie.title

                Glide.with(ivMoviePoster.context)
                    .load(imageLink)
                    .into(ivMoviePoster)

                ivMoviePoster.setOnClickListener {
                    movieClickListener(movie)
                }
            }
        }
    }

    override fun getItemCount() = list.size
}