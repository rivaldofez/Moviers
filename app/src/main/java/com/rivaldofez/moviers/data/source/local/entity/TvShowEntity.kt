package com.rivaldofez.moviers.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentity")
data class TvShowEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String,

    @ColumnInfo(name = "numberOfEpisodes")
    val numberOfEpisodes: Int,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "backdropPath")
    val backdropPath: String,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "numberOfSeasons")
    val numberOfSeasons: Int,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int,

    @ColumnInfo(name = "firstAirDate")
    val firstAirDate: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "originalName")
    val originalName: String,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Float,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "tagline")
    val tagline: String,

    @ColumnInfo(name = "inProduction")
    val inProduction: Boolean,

    @ColumnInfo(name = "lastAirDate")
    val lastAirDate: String,

    @ColumnInfo(name = "homepage")
    val homepage: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name="genres")
    val genre: String,

    @ColumnInfo(name="languages")
    val language: String,

    @ColumnInfo(name="latestEpisode")
    val latestEpisode: String,

    @ColumnInfo(name="isFavorite")
    var isFavorite: Boolean
)