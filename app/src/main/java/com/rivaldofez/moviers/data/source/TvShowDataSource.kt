package com.rivaldofez.moviers.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse
import com.rivaldofez.moviers.vo.Resource

interface TvShowDataSource {
    fun getPopularTvShows(sort: String): LiveData<Resource<PagedList<TvShowResponse>>>

    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getAllFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvshow: TvShowEntity, isFavorite: Boolean)
}