package com.rivaldofez.moviers.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvshowentity WHERE isFavorite = 1")
    fun getAllFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @RawQuery(observedEntities = [TvShowResponse::class])
    fun getAllPopularTvShow(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularTvShows(tvShowResponses: List<TvShowResponse>)

    @Query("SELECT * FROM tvshowentity WHERE id = :id")
    fun getDetailTvShowById(id: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTvShow(tvshow: TvShowEntity)

    @Update
    fun updateDetailTvShow(tvShowEntity: TvShowEntity)

}