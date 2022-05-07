package com.rivaldofez.moviers.ui.home.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.moviers.BuildConfig
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse
import com.rivaldofez.moviers.databinding.ItemGridBinding

class TvShowAdapter(private val callback: TvShowFragmentCallback): PagedListAdapter<TvShowResponse, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowResponse>(){
            override fun areItemsTheSame(oldItem: TvShowResponse, newItem: TvShowResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowResponse, newItem: TvShowResponse): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemGridBinding = ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemGridBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if(tvShow != null)
            holder.bind(tvShow)
    }

    inner class TvShowViewHolder(private val binding: ItemGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowResponse){
            with(binding){
                tvTitle.text = tvShow.name
                ratingMovie.rating = (0.5F * tvShow.voteAverage.toFloat())

                containerItem.setOnClickListener{callback.onTvShowClick(tvShow)}

                Glide.with(itemView.context).load(BuildConfig.API_PATH_IMAGE + tvShow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}