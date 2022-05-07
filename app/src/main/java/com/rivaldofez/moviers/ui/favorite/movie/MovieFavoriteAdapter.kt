package com.rivaldofez.moviers.ui.favorite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.moviers.BuildConfig
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.databinding.ItemFavoriteBinding
import com.rivaldofez.moviers.utils.formatDate

class MovieFavoriteAdapter(private val callback: MovieFavoriteCallback): PagedListAdapter<MovieEntity, MovieFavoriteAdapter.MovieFavoriteViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        val itemFavoriteBinding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavoriteViewHolder(itemFavoriteBinding)
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        if(movie != null)
            holder.bind(movie)
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    inner class MovieFavoriteViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity){
            with(binding){
                tvItemTitle.text = movie.title
                ratingItem.rating = (0.5F * movie.voteAverage)
                tvItemOverview.text = movie.overview
                tvItemDate.text = formatDate(movie.releaseDate)

                containerItem.setOnClickListener{callback.onMovieClick(movie)}

                Glide.with(itemView.context).load(BuildConfig.API_PATH_IMAGE + movie.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }

    }

}