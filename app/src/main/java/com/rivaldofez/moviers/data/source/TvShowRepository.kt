package com.rivaldofez.moviers.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rivaldofez.moviers.data.source.local.LocalDataSource
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse
import com.rivaldofez.moviers.data.source.remote.ApiResponse
import com.rivaldofez.moviers.data.source.remote.response.RemoteDataSource
import com.rivaldofez.moviers.data.source.remote.response.tvshow.TvShowEntityResponse
import com.rivaldofez.moviers.data.source.remote.response.tvshow.TvShowListResponse
import com.rivaldofez.moviers.utils.AppExecutors
import com.rivaldofez.moviers.vo.Resource

class TvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ): TvShowDataSource {

    companion object{
        @Volatile
        private var instance: TvShowRepository? = null
        fun getInstance(remoteDataSource: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): TvShowRepository =
                instance ?: synchronized(this){
                    instance ?: TvShowRepository(remoteDataSource, localData, appExecutors).apply { instance = this }
                }
    }

    override fun getPopularTvShows(sort: String): LiveData<Resource<PagedList<TvShowResponse>>> {
        return object : NetworkBoundResource<PagedList<TvShowResponse>, TvShowListResponse>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowResponse>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(3)
                    .setPageSize(3)
                    .build()

                return LivePagedListBuilder(localDataSource.getPopularTvShow(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowResponse>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<TvShowListResponse>> =
                remoteDataSource.getPopularTvShow()

            override fun saveCallResult(data: TvShowListResponse) {
                val popularTvShowList = ArrayList<TvShowResponse>()
                for(response in data.results){
                    val tvshow = TvShowResponse(
                        id = response.id,
                        firstAirDate = response.firstAirDate,
                        overview = response.overview,
                        originalLanguage = response.originalLanguage,
                        posterPath = response.posterPath,
                        backdropPath = response.backdropPath,
                        originalName = response.originalName,
                        popularity = response.popularity,
                        voteAverage = response.voteAverage,
                        name = response.name,
                        voteCount = response.voteCount
                    )
                    popularTvShowList.add(tvshow)
                }
                localDataSource.insertPopularTvShow(popularTvShowList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object: NetworkBoundResource<TvShowEntity, TvShowEntityResponse>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getDetailTvShow(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShowEntityResponse>> =
                remoteDataSource.getDetailTvShow(tvShowId.toString())

            override fun saveCallResult(data: TvShowEntityResponse) {
                val languages = data.spokenLanguages.joinToString { it.englishName }
                val genres = data.genres.joinToString { it.name }
                val tvShowEntity = TvShowEntity(
                    id = data.id,
                    originalLanguage = data.originalLanguage,
                    numberOfEpisodes = data.numberOfEpisodes,
                    type = data.type,
                    backdropPath = data.backdropPath.toString(),
                    popularity = data.popularity,
                    numberOfSeasons = data.numberOfSeasons,
                    voteCount = data.voteCount,
                    firstAirDate = data.firstAirDate,
                    overview = data.overview,
                    posterPath = data.posterPath,
                    originalName = data.originalName,
                    voteAverage = data.voteAverage,
                    name = data.name,
                    tagline = data.tagline,
                    inProduction = data.inProduction,
                    lastAirDate = data.lastAirDate,
                    homepage = data.homepage,
                    status = data.status,
                    language = languages,
                    genre = genres,
                    latestEpisode = data.lastEpisodeToAir.episodeNumber.toString(),
                    isFavorite = false
                )
                localDataSource.insertTvShowEntity(tvShowEntity)
            }
        }.asLiveData()
    }

    override fun getAllFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(3)
            .setPageSize(3)
            .build()

        return LivePagedListBuilder(localDataSource.getAllFavoriteTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvshow: TvShowEntity, isFavorite: Boolean) {
       appExecutors.diskIO().execute {
           localDataSource.setFavoriteTvShow(tvshow, isFavorite)
       }
    }
}