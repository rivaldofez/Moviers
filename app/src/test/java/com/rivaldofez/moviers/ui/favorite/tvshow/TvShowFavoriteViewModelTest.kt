package com.rivaldofez.moviers.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.rivaldofez.moviers.data.source.TvShowRepository
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
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
class TvShowFavoriteViewModelTest{
    private lateinit var tvshowFavoriteViewModel: TvShowFavoriteViewModel
    private val tvshowDummy = DataDummy.generateDummyDetailTvShows(
        DataDummy.generateDetailTvShow(), true
    )

    @get:Rule
    var instanceExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvshowRepository: TvShowRepository

    @Mock
    private lateinit var tvShowFavoriteObserver: Observer<List<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp(){
        tvshowFavoriteViewModel = TvShowFavoriteViewModel(tvshowRepository)
    }

    @Test
    fun getAllFavoriteTvShows(){
        val dummyFavoriteTvShows = pagedList
        `when`(dummyFavoriteTvShows.size).thenReturn(10)

        val favoriteTvShows = MutableLiveData<PagedList<TvShowEntity>>()
        favoriteTvShows.value = dummyFavoriteTvShows

        `when`(tvshowRepository.getAllFavoriteTvShow()).thenReturn(favoriteTvShows)
        val favoriteTvShowsList = tvshowFavoriteViewModel.getAllFavoriteTvShow().value
        verify(tvshowRepository).getAllFavoriteTvShow()

        assertNotNull(favoriteTvShowsList)
        assertEquals(10, favoriteTvShowsList?.size)

        tvshowFavoriteViewModel.getAllFavoriteTvShow().observeForever(tvShowFavoriteObserver)
        verify(tvShowFavoriteObserver).onChanged(dummyFavoriteTvShows)
    }

    @Test
    fun setFavoriteTvShow(){
        tvshowDummy.isFavorite = false
        val setFavorite = tvshowFavoriteViewModel.setFavoriteTvShow(tvshowDummy)
        assertNotNull(setFavorite)

        tvshowDummy.isFavorite = false
        val removeFavorite = tvshowFavoriteViewModel.setFavoriteTvShow(tvshowDummy)
        assertNotNull(removeFavorite)
    }
}