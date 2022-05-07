package com.rivaldofez.moviers.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rivaldofez.moviers.data.source.MovieRepository
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.data.source.remote.response.movie.MovieEntityResponse
import com.rivaldofez.moviers.utils.DataDummy
import com.rivaldofez.moviers.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val dummyMovie: MovieEntityResponse = DataDummy.generateDetailMovie()
    private val dummyMovieid: Int = dummyMovie.id

    @get:Rule
    var instanceExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var detailMovieObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp(){
        detailMovieViewModel = DetailMovieViewModel(movieRepository)
        detailMovieViewModel.setCurrentMovie(dummyMovieid.toString())
    }

    @Test
    fun getDetailMovie(){
        val movieEntityDummy = Resource.success(DataDummy.generateDummyDetailMovies(dummyMovie, true))
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = movieEntityDummy

        movieRepository.getDetailMovie(dummyMovieid)
        `when` (movieRepository.getDetailMovie(dummyMovieid)).thenReturn(movie)
        verify(movieRepository).getDetailMovie(dummyMovieid)

        detailMovieViewModel.getDetailMovie().observeForever(detailMovieObserver)
        verify(detailMovieObserver).onChanged(movieEntityDummy)
        assertNotNull(movieEntityDummy)
        assertEquals(movieEntityDummy.data?.id, dummyMovie.id)
        assertEquals(movieEntityDummy.data?.id, dummyMovie.id)
        assertEquals(movieEntityDummy.data?.overview, dummyMovie.overview)
        assertEquals(movieEntityDummy.data?.originalLanguage, dummyMovie.originalLanguage)
        assertEquals(movieEntityDummy.data?.originalTitle, dummyMovie.originalTitle)
        assertEquals(movieEntityDummy.data?.video, dummyMovie.video)
        assertEquals(movieEntityDummy.data?.title, dummyMovie.title)
        assertEquals(movieEntityDummy.data?.posterPath, dummyMovie.posterPath)
        assertEquals(movieEntityDummy.data?.backdropPath, dummyMovie.backdropPath)
        assertEquals(movieEntityDummy.data?.releaseDate, dummyMovie.releaseDate)
        assertEquals(movieEntityDummy.data?.adult, dummyMovie.adult)
    }
}