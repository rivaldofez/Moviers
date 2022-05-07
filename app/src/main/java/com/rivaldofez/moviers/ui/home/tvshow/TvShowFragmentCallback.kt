package com.rivaldofez.moviers.ui.home.tvshow

import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse

interface TvShowFragmentCallback {
    fun onTvShowClick(tvShow: TvShowResponse)
}