package com.rivaldofez.moviers.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.vo.Resource

interface MovieDataSource {
    fun getPopularMovies(sort: String): LiveData<Resource<PagedList<MovieResponse>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean)
}