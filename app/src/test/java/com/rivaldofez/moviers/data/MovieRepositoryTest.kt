@file:Suppress("UNCHECKED_CAST")
package com.rivaldofez.moviers.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.rivaldofez.moviers.data.source.local.LocalDataSource
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.data.source.remote.response.RemoteDataSource
import com.rivaldofez.moviers.utils.*
import com.rivaldofez.moviers.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote,local, appExecutors)

    private val sortDummy = SortUtils.sortFilter[0]
    private val moviesResponse = DataDummy.generateMovieListResponses()
    private val movieId = moviesResponse[0].id


    @Test
    fun getPopularMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieResponse>
        `when`(local.getPopularMovies(sortDummy)).thenReturn(dataSourceFactory)
        movieRepository.getPopularMovies(sortDummy)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generatePopularMovies().results))
        verify(local).getPopularMovies(sortDummy)

        assertNotNull(movieEntities.data)
        assertEquals(moviesResponse.size.toLong(), movieEntities.data?.size?.toLong())

    }

    @Test
    fun getDetailMovie(){
        val dummyMovieEntity = MutableLiveData<MovieEntity>()
        dummyMovieEntity.value = DataDummy.generateDummyDetailMovies(DataDummy.generateDetailMovie(), true)

        `when`(local.getDetailMovie(movieId)).thenReturn(dummyMovieEntity)

        val movieDetailEntity = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(moviesResponse[0].id, movieDetailEntity.data?.id)
        assertEquals(moviesResponse[0].overview, movieDetailEntity.data?.overview)
        assertEquals(moviesResponse[0].originalLanguage, movieDetailEntity.data?.originalLanguage)
        assertEquals(moviesResponse[0].originalTitle, movieDetailEntity.data?.originalTitle)
        assertEquals(moviesResponse[0].video, movieDetailEntity.data?.video)
        assertEquals(moviesResponse[0].title, movieDetailEntity.data?.title)
        assertEquals(moviesResponse[0].posterPath, movieDetailEntity.data?.posterPath)
        assertEquals(moviesResponse[0].backdropPath, movieDetailEntity.data?.backdropPath)
        assertEquals(moviesResponse[0].releaseDate, movieDetailEntity.data?.releaseDate)
        assertEquals(moviesResponse[0].adult, movieDetailEntity.data?.adult)
    }

    @Test
    fun getAllFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllFavoriteMovies()).thenReturn(dataSourceFactory)

        movieRepository.getAllFavoriteMovies()
        val movieFavoriteEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateFavoriteMovieEntity()))
        verify(local).getAllFavoriteMovies()
        assertNotNull(movieFavoriteEntities)
        assertEquals(1, movieFavoriteEntities.data?.size)
    }

}