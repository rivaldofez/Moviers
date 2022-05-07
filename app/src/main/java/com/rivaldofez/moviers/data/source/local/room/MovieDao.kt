package com.rivaldofez.moviers.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity WHERE isFavorite = 1")
    fun getAllFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieResponse::class])
    fun getAllPopularMovie(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movieResponses: List<MovieResponse>)

    @Query("SELECT * FROM movieentity WHERE id = :id")
    fun getDetailMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserDetailMovie(movie: MovieEntity)

    @Update
    fun updateDetailMovie(movieEntity: MovieEntity)

}