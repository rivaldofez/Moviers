package com.rivaldofez.moviers.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rivaldofez.moviers.data.source.MovieRepository
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity


class MovieFavoriteViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>> = movieRepository.getAllFavoriteMovies()

    fun setFavoriteMovie(movieEntity: MovieEntity){
        val isFavorite = !movieEntity.isFavorite
        movieRepository.setFavoriteMovie(movieEntity, isFavorite)
    }
}