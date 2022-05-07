package com.rivaldofez.moviers.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.moviers.BuildConfig
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.databinding.ItemFavoriteBinding
import com.rivaldofez.moviers.utils.formatDate

class TvShowFavoriteAdapter(private val callback: TvShowFavoriteCallback): PagedListAdapter<TvShowEntity, TvShowFavoriteAdapter.TvShowFavoriteViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>(){
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFavoriteViewHolder {
        val itemFavoriteBinding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowFavoriteViewHolder((itemFavoriteBinding))
    }

    override fun onBindViewHolder(holder: TvShowFavoriteViewHolder, position: Int) {
        val tvshow = getItem(position)
        if (tvshow != null)
            holder.bind(tvshow)
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    inner class TvShowFavoriteViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowEntity){
            with(binding){
                tvItemTitle.text = tvshow.name
                ratingItem.rating = (0.5F * tvshow.voteAverage)
                tvItemOverview.text = tvshow.overview
                tvItemDate.text = formatDate(tvshow.firstAirDate)

                containerItem.setOnClickListener{callback.onTvShowClick(tvshow)}

                Glide.with(itemView.context).load(BuildConfig.API_PATH_IMAGE + tvshow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }

    }
}