package com.rivaldofez.moviers.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentity")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    val id: Int,

    @ColumnInfo(name="originalLanguage")
    val originalLanguage: String,

    @ColumnInfo(name="imdbId")
    val imdbId: String,

    @ColumnInfo(name="video")
    val video: Boolean,

    @ColumnInfo(name="title")
    val title: String,

    @ColumnInfo(name="backdropPath")
    val backdropPath: String,

    @ColumnInfo(name="revenue")
    val revenue: Int,

    @ColumnInfo(name="popularity")
    val popularity: Double,

    @ColumnInfo(name="voteCount")
    val voteCount: Int,

    @ColumnInfo(name="budget")
    val budget: Int,

    @ColumnInfo(name="overview")
    val overview: String,

    @ColumnInfo(name="originalTitle")
    val originalTitle: String,

    @ColumnInfo(name="runtime")
    val runtime: Int,

    @ColumnInfo(name="posterPath")
    val posterPath: String,

    @ColumnInfo(name="releaseDate")
    val releaseDate: String,

    @ColumnInfo(name="voteAverage")
    val voteAverage: Float,

    @ColumnInfo(name="tagline")
    val tagline: String,

    @ColumnInfo(name="adult")
    val adult: Boolean,

    @ColumnInfo(name="homepage")
    val homepage: String,

    @ColumnInfo(name="status")
    val status: String,

    @ColumnInfo(name="genres")
    val genre: String,

    @ColumnInfo(name="languages")
    val language: String,

    @ColumnInfo(name="isFavorite")
    var isFavorite: Boolean
)