package com.rivaldofez.moviers.ui.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.rivaldofez.moviers.data.source.MovieRepository
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.utils.SortUtils
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
class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel
    private val sortDummy = SortUtils.sortFilter[0]

    @get:Rule
    var instanceExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<MovieResponse>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieResponse>

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getPopularMovies(){
        val dummyMovies = Resource.success(pagedList)
        `when` (dummyMovies.data?.size).thenReturn(10)

        val movies = MutableLiveData<Resource<PagedList<MovieResponse>>>()
        movies.value = dummyMovies

        `when`(movieRepository.getPopularMovies(sortDummy)).thenReturn(movies)
        val movieListResponses = movieViewModel.getPopularMovies(sortDummy).value?.data

        verify(movieRepository).getPopularMovies(sortDummy)
        assertNotNull(movieListResponses)
        assertEquals(10, movieListResponses?.size)

        movieViewModel.getPopularMovies(sortDummy).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)

    }

    @Test
    fun getEmptyPopularMovies(){
        val dummyMovies = Resource.success(pagedList)
        `when` (dummyMovies.data?.size).thenReturn(0)

        val movies = MutableLiveData<Resource<PagedList<MovieResponse>>>()
        movies.value = dummyMovies

        `when`(movieRepository.getPopularMovies(sortDummy)).thenReturn(movies)
        val movieListResponses = movieViewModel.getPopularMovies(sortDummy).value?.data

        verify(movieRepository).getPopularMovies(sortDummy)
        assertNotNull(movieListResponses)
        assertEquals(0, movieListResponses?.size)

        movieViewModel.getPopularMovies(sortDummy).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)

    }
}