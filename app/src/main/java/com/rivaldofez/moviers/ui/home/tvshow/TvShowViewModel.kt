package com.rivaldofez.moviers.ui.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rivaldofez.moviers.data.source.TvShowRepository
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse
import com.rivaldofez.moviers.vo.Resource

class TvShowViewModel(private val tvShowRepository: TvShowRepository): ViewModel() {
    fun getPopularTvShows(sort: String): LiveData<Resource<PagedList<TvShowResponse>>> = tvShowRepository.getPopularTvShows(sort)
}