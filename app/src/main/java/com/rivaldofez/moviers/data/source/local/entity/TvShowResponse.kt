package com.rivaldofez.moviers.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowresponse")
data class TvShowResponse(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "firstAirDate")
    val firstAirDate: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "backdropPath")
    val backdropPath: String,

    @ColumnInfo(name = "originalName")
    val originalName: String,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int
)
