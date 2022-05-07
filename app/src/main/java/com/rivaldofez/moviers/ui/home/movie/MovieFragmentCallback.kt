package com.rivaldofez.moviers.ui.home.movie

import com.rivaldofez.moviers.data.source.local.entity.MovieResponse

interface MovieFragmentCallback {
    fun onMovieClick(movie: MovieResponse)
}