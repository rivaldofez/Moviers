package com.rivaldofez.moviers.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rivaldofez.moviers.data.source.MovieRepository
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getPopularMovies(sort: String): LiveData<Resource<PagedList<MovieResponse>>> = movieRepository.getPopularMovies(sort)
}