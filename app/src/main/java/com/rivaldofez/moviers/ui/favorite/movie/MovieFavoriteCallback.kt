package com.rivaldofez.moviers.ui.favorite.movie

import com.rivaldofez.moviers.data.source.local.entity.MovieEntity

interface MovieFavoriteCallback {
    fun onMovieClick(movie: MovieEntity)
}