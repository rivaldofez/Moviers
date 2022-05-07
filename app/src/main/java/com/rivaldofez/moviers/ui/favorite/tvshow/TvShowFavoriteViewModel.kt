package com.rivaldofez.moviers.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rivaldofez.moviers.data.source.TvShowRepository
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity

class TvShowFavoriteViewModel(private val tvShowRepository: TvShowRepository): ViewModel() {
    fun getAllFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = tvShowRepository.getAllFavoriteTvShow()

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity){
        val isFavorite = !tvShowEntity.isFavorite
        tvShowRepository.setFavoriteTvShow(tvShowEntity, isFavorite)
    }
}