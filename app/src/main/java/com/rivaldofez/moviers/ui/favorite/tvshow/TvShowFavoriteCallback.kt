package com.rivaldofez.moviers.ui.favorite.tvshow

import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity

interface TvShowFavoriteCallback {
    fun onTvShowClick(tvShow: TvShowEntity)
}