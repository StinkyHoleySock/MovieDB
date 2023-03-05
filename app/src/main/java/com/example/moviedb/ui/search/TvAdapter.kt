package com.example.moviedb.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.databinding.ItemMovieBinding
import com.example.moviedb.model.tv.Tv
import com.example.moviedb.util.Constants


class TvAdapter(
    private val tvClickListener: (tvItem: Tv) -> Unit
) : RecyclerView.Adapter<TvAdapter.ViewHolder>() {
    private var list: MutableList<Tv> = mutableListOf()

    fun setData(data: List<Tv>) {
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

        fun bind(tv: Tv) {
            with(binding) {
                tvMovieName.text = tv.name

                Glide.with(ivMoviePoster.context)
                    .load(Constants.IMAGE_BASE_URL + tv.poster_path)
                    .error(R.drawable.ic_no_image)
                    .into(ivMoviePoster)

                ivMoviePoster.setOnClickListener {
                    tvClickListener(tv)
                }
            }
        }
    }

    override fun getItemCount() = list.size

}