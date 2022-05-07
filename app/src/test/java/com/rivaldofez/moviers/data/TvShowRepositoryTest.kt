@file:Suppress("UNCHECKED_CAST")
package com.rivaldofez.moviers.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.rivaldofez.moviers.data.source.local.LocalDataSource
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse
import com.rivaldofez.moviers.data.source.remote.response.RemoteDataSource
import com.rivaldofez.moviers.utils.*
import com.rivaldofez.moviers.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TvShowRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val tvShowRepository = FakeTvShowRepository(remote, local, appExecutors)

    private val sortDummy = SortUtils.sortFilter[0]
    private val tvshowResponse = DataDummy.generateTvShowListResponses()
    private val tvShowId = tvshowResponse[0].id

    @Test
    fun getPopularTvShows(){
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowResponse>
        Mockito.`when`(local.getPopularTvShow(sortDummy)).thenReturn(dataSourceFactory)
        tvShowRepository.getPopularTvShows(sortDummy)

        val tvshowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generatePopularTvShows().results))
        verify(local).getPopularTvShow(sortDummy)

        assertNotNull(tvshowEntities.data)
        assertEquals(tvshowResponse.size.toLong(), tvshowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTvShow(){
        val dummyTvShowEntity = MutableLiveData<TvShowEntity>()
        dummyTvShowEntity.value = DataDummy.generateDummyDetailTvShows(DataDummy.generateDetailTvShow(), true)

        Mockito.`when`(local.getDetailTvShow(tvShowId)).thenReturn(dummyTvShowEntity)

        val tvShowDetailEntity = LiveDataTestUtil.getValue(tvShowRepository.getDetailTvShow(tvShowId))
        verify(local).getDetailTvShow(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvshowResponse[0].id, tvShowDetailEntity.data?.id)
        assertEquals(tvshowResponse[0].firstAirDate, tvShowDetailEntity.data?.firstAirDate)
        assertEquals(tvshowResponse[0].originalLanguage, tvShowDetailEntity.data?.originalLanguage)
        assertEquals(tvshowResponse[0].posterPath, tvShowDetailEntity.data?.posterPath)
        assertEquals(tvshowResponse[0].backdropPath, tvShowDetailEntity.data?.backdropPath)
        assertEquals(tvshowResponse[0].originalName, tvShowDetailEntity.data?.originalName)
        assertEquals(tvshowResponse[0].name, tvShowDetailEntity.data?.name)
    }

    @Test
    fun getAllFavoriteTvShow(){
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getAllFavoriteTvShows()).thenReturn(dataSourceFactory)

        tvShowRepository.getAllFavoriteTvShow()
        val tvshowFavoriteEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateFavoriteTvShowEntity()))
        verify(local).getAllFavoriteTvShows()
        assertNotNull(tvshowFavoriteEntities)
        assertEquals(1, tvshowFavoriteEntities.data?.size)
    }

}