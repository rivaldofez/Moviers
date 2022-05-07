package com.rivaldofez.moviers.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse
import com.rivaldofez.moviers.data.source.local.room.MovieDao
import com.rivaldofez.moviers.data.source.local.room.TvShowDao
import com.rivaldofez.moviers.utils.SortUtils

class LocalDataSource private constructor(private val mMovieDao: MovieDao, private val mTvShowDao: TvShowDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao, tvShowDao: TvShowDao): LocalDataSource =
            INSTANCE?: LocalDataSource(movieDao, tvShowDao)

    }

    fun insertPopularMovies(movies: List<MovieResponse>) = mMovieDao.insertPopularMovies(movies)

    fun insertMovieEntity(movie: MovieEntity) = mMovieDao.inserDetailMovie(movie)

    fun setFavoriteMovie(movieEntity: MovieEntity, isFavorite: Boolean){
        movieEntity.isFavorite = isFavorite
        mMovieDao.updateDetailMovie(movieEntity)
    }

    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getAllFavoriteMovie()

    fun getDetailMovie(id: Int): LiveData<MovieEntity> = mMovieDao.getDetailMovieById(id)

    fun getPopularMovies(sort: String): DataSource.Factory<Int, MovieResponse> {
        val query = SortUtils.getSortedPopularMovieQuery(sort)
        return mMovieDao.getAllPopularMovie(query)
    }

    fun insertPopularTvShow(tvshows: List<TvShowResponse>) = mTvShowDao.insertPopularTvShows(tvshows)

    fun insertTvShowEntity(tvShow: TvShowEntity) = mTvShowDao.insertDetailTvShow(tvShow)

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity, isFavorite: Boolean){
        tvShowEntity.isFavorite = isFavorite
        mTvShowDao.updateDetailTvShow(tvShowEntity)
    }

    fun getAllFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = mTvShowDao.getAllFavoriteTvShow()

    fun getDetailTvShow(id: Int): LiveData<TvShowEntity> = mTvShowDao.getDetailTvShowById(id)

    fun getPopularTvShow(sort: String): DataSource.Factory<Int, TvShowResponse>{
        val query = SortUtils.getSortedPopularTvShow(sort)
        return mTvShowDao.getAllPopularTvShow(query)
    }



}