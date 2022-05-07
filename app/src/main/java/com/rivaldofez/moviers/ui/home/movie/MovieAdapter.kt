package com.rivaldofez.moviers.ui.home.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.moviers.BuildConfig
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.databinding.ItemGridBinding

class MovieAdapter(private val callback: MovieFragmentCallback): PagedListAdapter<MovieResponse, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResponse>(){
            override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemGridBinding =  ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemGridBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null)
            holder.bind(movie)
    }

    inner class MovieViewHolder(private val binding: ItemGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponse){
            with(binding){
                tvTitle.text = movie.title
                ratingMovie.rating = (0.5F * movie.voteAverage.toFloat())

                containerItem.setOnClickListener{callback.onMovieClick(movie)}

                Glide.with(itemView.context).load(BuildConfig.API_PATH_IMAGE + movie.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}