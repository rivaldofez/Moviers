package com.rivaldofez.moviers.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rivaldofez.moviers.data.source.TvShowRepository
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.vo.Resource

class DetailTvShowViewModel(private val tvShowRepository: TvShowRepository): ViewModel() {
    private lateinit var tvShowId: String

    fun setCurrentTvShow(tvShowId: String){
        this.tvShowId = tvShowId
    }

    fun getDetailTvShow() : LiveData<Resource<TvShowEntity>> = tvShowRepository.getDetailTvShow(tvShowId.toInt())
}