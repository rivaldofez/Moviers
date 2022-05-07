package com.rivaldofez.moviers.ui.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.rivaldofez.moviers.data.source.TvShowRepository
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse
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
class TvShowViewModelTest {
    private lateinit var tvShowViewModel: TvShowViewModel
    private val sortDummy = SortUtils.sortFilter[1]

    @get:Rule
    var instanceExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvSHowRepository: TvShowRepository

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<PagedList<TvShowResponse>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowResponse>

    @Before
    fun setUp(){
        tvShowViewModel = TvShowViewModel(tvSHowRepository)
    }

    @Test
    fun getPopularTvShows(){
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(10)

        val tvshows = MutableLiveData<Resource<PagedList<TvShowResponse>>>()
        tvshows.value = dummyTvShows

        `when`(tvSHowRepository.getPopularTvShows(sortDummy)).thenReturn(tvshows)
        val tvshowListResponses = tvShowViewModel.getPopularTvShows(sortDummy).value?.data

        verify(tvSHowRepository).getPopularTvShows(sortDummy)
        assertNotNull(tvshowListResponses)
        assertEquals(10, tvshowListResponses?.size)

        tvShowViewModel.getPopularTvShows(sortDummy).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShows)
    }

    @Test
    fun getEmptyPopularTvShows(){
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(0)

        val tvshows = MutableLiveData<Resource<PagedList<TvShowResponse>>>()
        tvshows.value = dummyTvShows

        `when`(tvSHowRepository.getPopularTvShows(sortDummy)).thenReturn(tvshows)
        val tvshowListResponses = tvShowViewModel.getPopularTvShows(sortDummy).value?.data

        verify(tvSHowRepository).getPopularTvShows(sortDummy)
        assertNotNull(tvshowListResponses)
        assertEquals(0, tvshowListResponses?.size)

        tvShowViewModel.getPopularTvShows(sortDummy).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShows)
    }
}