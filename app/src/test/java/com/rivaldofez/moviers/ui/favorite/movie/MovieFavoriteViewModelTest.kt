package com.rivaldofez.moviers.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.rivaldofez.moviers.data.source.MovieRepository
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.utils.DataDummy
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
class MovieFavoriteViewModelTest {
    private lateinit var movieFavoriteViewModel: MovieFavoriteViewModel
    private val movieDummy = DataDummy.generateDummyDetailMovies(
        DataDummy.generateDetailMovie(), true
    )

    @get:Rule
    var instanceExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieFavoriteObserver: Observer<List<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp(){
        movieFavoriteViewModel = MovieFavoriteViewModel(movieRepository)
    }

    @Test
    fun getAllFavoriteMovies(){
        val dummyFavoriteMovies = pagedList
        `when`(dummyFavoriteMovies.size).thenReturn(10)

        val favoriteMovies = MutableLiveData<PagedList<MovieEntity>>()
        favoriteMovies.value = dummyFavoriteMovies

        `when`(movieRepository.getAllFavoriteMovies()).thenReturn(favoriteMovies)
        val favoriteMovieList = movieFavoriteViewModel.getAllFavoriteMovies().value
        verify(movieRepository).getAllFavoriteMovies()

        assertNotNull(favoriteMovieList)
        assertEquals(10, favoriteMovieList?.size)

        movieFavoriteViewModel.getAllFavoriteMovies().observeForever(movieFavoriteObserver)
        verify(movieFavoriteObserver).onChanged(dummyFavoriteMovies)
    }

    @Test
    fun setFavoriteMovie(){
        movieDummy.isFavorite = true
        val setFavorite = movieFavoriteViewModel.setFavoriteMovie(movieDummy)
        assertNotNull(setFavorite)

        movieDummy.isFavorite = false
        val removeFavorite = movieFavoriteViewModel.setFavoriteMovie(movieDummy)
        assertNotNull(removeFavorite)
    }

}