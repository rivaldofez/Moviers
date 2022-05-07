package com.rivaldofez.moviers.data.source.remote.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rivaldofez.moviers.BuildConfig
import com.rivaldofez.moviers.data.source.remote.ApiResponse
import com.rivaldofez.moviers.data.source.remote.config.ApiConfig
import com.rivaldofez.moviers.data.source.remote.response.movie.MovieEntityResponse
import com.rivaldofez.moviers.data.source.remote.response.movie.MovieListResponse
import com.rivaldofez.moviers.data.source.remote.response.tvshow.TvShowEntityResponse
import com.rivaldofez.moviers.data.source.remote.response.tvshow.TvShowListResponse
import com.rivaldofez.moviers.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource()
            }
    }

    fun getPopularMovies(): LiveData<ApiResponse<MovieListResponse>> {
        EspressoIdlingResource.increment()

        val resultPopularMovie = MutableLiveData<ApiResponse<MovieListResponse>>()
        val client = ApiConfig.getApiService().getPopularMovies(key = BuildConfig.API_KEY, page = "1")
        client.enqueue(object: Callback<MovieListResponse>{
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        resultPopularMovie.value = ApiResponse.success(response.body() as MovieListResponse)
                    }else{
                        Log.e("RemoteDataSource", "data receive is null")
                    }
                }else{
                    Log.e("RemoteDataSource", "fail at getPopularMovies because: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "fail at getPopularMovies because: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultPopularMovie
    }

    fun getPopularTvShow(): LiveData<ApiResponse<TvShowListResponse>>{
        EspressoIdlingResource.increment()

        val resultPopularTvShow = MutableLiveData<ApiResponse<TvShowListResponse>>()
        val client = ApiConfig.getApiService().getPopularTvShow(key = BuildConfig.API_KEY, page = "1")
        client.enqueue(object: Callback<TvShowListResponse>{
            override fun onResponse(
                call: Call<TvShowListResponse>,
                response: Response<TvShowListResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        resultPopularTvShow.value = ApiResponse.success(response.body() as TvShowListResponse)
                    }else{
                        Log.e("RemoteDataSource", "data receive is null")
                    }
                }else{
                    Log.e("RemoteDataSource", "fail at getPopularTvShows because: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowListResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "fail at getPopularTvShows because: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultPopularTvShow
    }

    fun getDetailMovie(movieId: String): LiveData<ApiResponse<MovieEntityResponse>>{
        EspressoIdlingResource.increment()

        val resultDetailMovie = MutableLiveData<ApiResponse<MovieEntityResponse>>()
        val client = ApiConfig.getApiService().getMovieById(key = BuildConfig.API_KEY,id =  movieId)
        client.enqueue(object: Callback<MovieEntityResponse>{
            override fun onResponse(
                call: Call<MovieEntityResponse>,
                response: Response<MovieEntityResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        resultDetailMovie.value = ApiResponse.success(response.body() as MovieEntityResponse)
                    }else{
                        Log.e("RemoteDataSource", "data receive is null")
                    }
                }else{
                    Log.e("RemoteDataSource", "fail at getDetailMovie because: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieEntityResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "fail at getDetailMovie because: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultDetailMovie
    }

    fun getDetailTvShow(tvShowId: String): LiveData<ApiResponse<TvShowEntityResponse>>{
        EspressoIdlingResource.increment()

        val resulDetailTvShow = MutableLiveData<ApiResponse<TvShowEntityResponse>>()
        val client = ApiConfig.getApiService().getTvShowById(key = BuildConfig.API_KEY, id = tvShowId)
        client.enqueue(object: Callback<TvShowEntityResponse>{
            override fun onResponse(
                call: Call<TvShowEntityResponse>,
                response: Response<TvShowEntityResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        resulDetailTvShow.value = ApiResponse.success(response.body() as TvShowEntityResponse)
                    }else{
                        Log.e("RemoteDataSource", "data receive is null")
                    }
                }else{
                    Log.e("RemoteDataSource", "fail at getDetailTvShow because: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowEntityResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "fail at getDetailTvShow because: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resulDetailTvShow
    }
}