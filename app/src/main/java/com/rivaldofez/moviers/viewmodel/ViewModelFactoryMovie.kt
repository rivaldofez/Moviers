package com.rivaldofez.moviers.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rivaldofez.moviers.data.source.MovieRepository
import com.rivaldofez.moviers.di.Injection
import com.rivaldofez.moviers.ui.detail.movie.DetailMovieViewModel
import com.rivaldofez.moviers.ui.favorite.movie.MovieFavoriteViewModel
import com.rivaldofez.moviers.ui.home.movie.MovieViewModel

class ViewModelFactoryMovie private constructor(private val movieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        private var instance: ViewModelFactoryMovie? = null

        fun getInstance(context: Context): ViewModelFactoryMovie =
                instance ?: synchronized(this){
                    instance?: ViewModelFactoryMovie(Injection.provideMovieRepository(context)).apply {
                        instance = this
                    }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown View Model" + modelClass.name)
        }
    }
}