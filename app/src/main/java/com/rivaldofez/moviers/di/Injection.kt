package com.rivaldofez.moviers.di

import android.content.Context
import com.rivaldofez.moviers.data.source.MovieRepository
import com.rivaldofez.moviers.data.source.TvShowRepository
import com.rivaldofez.moviers.data.source.local.LocalDataSource
import com.rivaldofez.moviers.data.source.local.room.MoviersAppsDatabase
import com.rivaldofez.moviers.data.source.remote.response.RemoteDataSource
import com.rivaldofez.moviers.utils.AppExecutors

object Injection {
    fun provideMovieRepository(context: Context): MovieRepository {
        val database = MoviersAppsDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao(), database.tvshowDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTvShowRepository(context: Context): TvShowRepository {
        val database = MoviersAppsDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao(), database.tvshowDao())
        val appExecutors = AppExecutors()

        return TvShowRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}