package com.rivaldofez.moviers.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rivaldofez.moviers.data.source.MovieDataSource
import com.rivaldofez.moviers.data.source.NetworkBoundResource
import com.rivaldofez.moviers.data.source.local.LocalDataSource
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.data.source.remote.ApiResponse
import com.rivaldofez.moviers.data.source.remote.response.RemoteDataSource
import com.rivaldofez.moviers.data.source.remote.response.movie.MovieEntityResponse
import com.rivaldofez.moviers.data.source.remote.response.movie.MovieListResponse
import com.rivaldofez.moviers.utils.AppExecutors
import com.rivaldofez.moviers.vo.Resource

class FakeMovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ): MovieDataSource {

    override fun getPopularMovies(sort: String): LiveData<Resource<PagedList<MovieResponse>>> {
        return object : NetworkBoundResource<PagedList<MovieResponse>, MovieListResponse>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieResponse>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(3)
                    .setPageSize(3)
                    .build()

                return LivePagedListBuilder(localDataSource.getPopularMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieResponse>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MovieListResponse>> =
                remoteDataSource.getPopularMovies()

            override fun saveCallResult(data: MovieListResponse) {
                val popularMovieList = ArrayList<MovieResponse>()
                for(response in data.results){
                    val movie = MovieResponse(
                        id = response.id,
                        overview = response.overview,
                        originalLanguage = response.originalLanguage,
                        originalTitle = response.originalTitle,
                        video = response.video,
                        title = response.title,
                        posterPath = response.posterPath,
                        backdropPath = response.backdropPath,
                        releaseDate = response.releaseDate,
                        popularity = response.popularity,
                        voteAverage = response.voteAverage,
                        adult = response.adult,
                        voteCount = response.voteCount

                    )
                    popularMovieList.add(movie)
                }

                localDataSource.insertPopularMovies(popularMovieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object: NetworkBoundResource<MovieEntity, MovieEntityResponse>(appExecutors){
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getDetailMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<MovieEntityResponse>> =
                remoteDataSource.getDetailMovie(movieId.toString())

            override fun saveCallResult(data: MovieEntityResponse) {
                val languages = data.spokenLanguages.joinToString { it.englishName }
                val genres = data.genres.joinToString { it.name }
                val movieEntity = MovieEntity(
                    id = data.id,
                    originalLanguage = data.originalLanguage,
                    imdbId = data.imdbId,
                    video = data.video,
                    title = data.title,
                    backdropPath = data.backdropPath,
                    revenue = data.revenue,
                    popularity = data.popularity,
                    voteCount = data.voteCount,
                    budget = data.budget,
                    overview = data.overview,
                    originalTitle = data.originalTitle,
                    runtime = data.runtime,
                    posterPath = data.posterPath,
                    releaseDate = data.releaseDate,
                    voteAverage = data.voteAverage,
                    tagline = data.tagline,
                    adult = data.adult,
                    homepage = data.homepage,
                    status = data.status,
                    language = languages,
                    genre = genres,
                    isFavorite = false
                )
                localDataSource.insertMovieEntity(movieEntity)
            }
        }.asLiveData()
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(3)
            .setPageSize(3)
            .build()

        return LivePagedListBuilder(localDataSource.getAllFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) {
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteMovie(movie, isFavorite)
        }
    }
}