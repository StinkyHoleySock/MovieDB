package com.example.moviedb.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.databinding.ItemMovieBinding
import com.example.moviedb.model.Movie

class SearchAdapter(
    private val movieClickListener: (movieItem: Movie) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var list: MutableList<Movie> = mutableListOf()

    fun setData(data: List<Movie>) {
        list.clear()
        list.addAll(data)
        Log.d("develop", "setData: $data")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("develop", "onCreateViewHolder")

        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("develop", "onBindViewHolder")

        holder.bind(list[position])
    }


    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(response: Movie) {
            with(binding) {
                //todo fix link
                val imageLink = "https://image.tmdb.org/t/p/w500" + response.poster_path
                tvMovieName.text = response.title
                Log.d("develop", "iml: $imageLink")
                Log.d("develop", "tvMovieNamev: ${response.title}")

                Glide.with(ivMoviePoster.context)
                    .load(imageLink)
                    .into(ivMoviePoster)

                ivMoviePoster.setOnClickListener {
                    movieClickListener(response)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("develop", "getItemCount: ${list.size}")
        return list.size
    }
}