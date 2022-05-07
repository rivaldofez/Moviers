package com.rivaldofez.moviers.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rivaldofez.moviers.data.source.TvShowRepository
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
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
class DetailTvShowViewModelTest {
    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDetailTvShow()
    private val dummyTvShowId: Int = dummyTvShow.id

    @get:Rule
    var instanceExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var detailTvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp(){
        detailTvShowViewModel = DetailTvShowViewModel(tvShowRepository)
        detailTvShowViewModel.setCurrentTvShow(dummyTvShowId.toString())
    }

    @Test
    fun getDetailTvShow(){
        val tvShowEntityDummy = Resource.success(DataDummy.generateDummyDetailTvShows(dummyTvShow, true))
        val tvshow = MutableLiveData<Resource<TvShowEntity>>()
        tvshow.value = tvShowEntityDummy

        tvShowRepository.getDetailTvShow(dummyTvShowId)
        `when`(tvShowRepository.getDetailTvShow(dummyTvShowId)).thenReturn(tvshow)
        verify(tvShowRepository).getDetailTvShow(dummyTvShowId)

        detailTvShowViewModel.getDetailTvShow().observeForever(detailTvShowObserver)
        verify(detailTvShowObserver).onChanged(tvShowEntityDummy)
        assertNotNull(tvShowEntityDummy)
        assertEquals(dummyTvShow.id, tvShowEntityDummy.data?.id)
        assertEquals(dummyTvShow.firstAirDate, tvShowEntityDummy.data?.firstAirDate)
        assertEquals(dummyTvShow.originalLanguage, tvShowEntityDummy.data?.originalLanguage)
        assertEquals(dummyTvShow.posterPath, tvShowEntityDummy.data?.posterPath)
        assertEquals(dummyTvShow.backdropPath, tvShowEntityDummy.data?.backdropPath)
        assertEquals(dummyTvShow.originalName, tvShowEntityDummy.data?.originalName)
        assertEquals(dummyTvShow.name, tvShowEntityDummy.data?.name)
        assertEquals(dummyTvShow.overview, tvShowEntityDummy.data?.overview)
    }
}