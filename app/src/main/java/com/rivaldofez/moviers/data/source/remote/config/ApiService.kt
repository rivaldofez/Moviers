package com.rivaldofez.moviers.data.source.remote.config

import com.rivaldofez.moviers.data.source.remote.response.movie.MovieEntityResponse
import com.rivaldofez.moviers.data.source.remote.response.movie.MovieListResponse
import com.rivaldofez.moviers.data.source.remote.response.tvshow.TvShowEntityResponse
import com.rivaldofez.moviers.data.source.remote.response.tvshow.TvShowListResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): Call<MovieListResponse>

    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): Call<TvShowListResponse>

    @GET("movie/{id}")
    fun getMovieById(
        @Path("id") id: String,
        @Query("api_key") key: String

    ): Call<MovieEntityResponse>

    @GET("tv/{id}")
    fun getTvShowById(
        @Path("id") id: String,
        @Query("api_key") key: String
    ): Call<TvShowEntityResponse>

}
