package com.rivaldofez.moviers.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse

@Database(entities = [MovieEntity::class, TvShowEntity::class, MovieResponse::class, TvShowResponse::class], version = 1, exportSchema = false)
abstract class MoviersAppsDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun tvshowDao(): TvShowDao

    companion object {

        @Volatile
        private var INSTANCE: MoviersAppsDatabase? = null

        fun getInstance(context: Context): MoviersAppsDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MoviersAppsDatabase::class.java,
                    "Moviers.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}