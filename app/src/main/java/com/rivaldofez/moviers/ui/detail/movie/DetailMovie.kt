package com.rivaldofez.moviers.ui.detail.movie

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.moviers.BuildConfig
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.databinding.ActivityDetailMovieBinding
import com.rivaldofez.moviers.ui.favorite.movie.MovieFavoriteViewModel
import com.rivaldofez.moviers.ui.webview.WebViewActivity
import com.rivaldofez.moviers.utils.*
import com.rivaldofez.moviers.viewmodel.ViewModelFactoryMovie
import com.rivaldofez.moviers.vo.Status

class DetailMovie : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }
    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private lateinit var movieFavoriteViewModel: MovieFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)
        setActionBar()

        val factory = ViewModelFactoryMovie.getInstance(this)
        detailMovieViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        movieFavoriteViewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]

        val bundle = intent.extras
        if(bundle != null){
            val movieId = bundle.getString(EXTRA_MOVIE)
            if(movieId != null){
                detailMovieViewModel.setCurrentMovie(movieId)
                detailMovieViewModel.getDetailMovie().observe(this, { movie ->
                    if(movie != null){
                        when(movie.status){
                            Status.LOADING -> showLoading(true, detailMovieBinding.loading)
                            Status.SUCCESS -> {
                                showLoading(false, detailMovieBinding.loading)
                                movie.data?.let { setContentView(it) }
                            }

                            Status.ERROR -> {
                                showLoading(false, detailMovieBinding.loading)
                                Toast.makeText(this, "There is an Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun setContentView(movie: MovieEntity){
        Glide.with(this).load(BuildConfig.API_PATH_IMAGE + movie.posterPath).apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)).into(detailMovieBinding.imgPoster)
        Glide.with(this).load(BuildConfig.API_PATH_IMAGE + movie.posterPath).apply(
            RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)).into(detailMovieBinding.imgBackdrop)

        detailMovieBinding.tvDate.text = formatDate(movie.releaseDate)

        detailMovieBinding.tvSynopsis.text = movie.overview
        detailMovieBinding.tvOriginal.text = movie.originalTitle
        detailMovieBinding.tvHomepage.text = movie.homepage
        detailMovieBinding.tvStatus.text = movie.status
        detailMovieBinding.tvDuration.text = formatRuntime(movie.runtime)
        detailMovieBinding.chartPopularity.setProgress(movie.voteAverage * 10F, true)
        detailMovieBinding.tvBudget.text = formatCurrency(movie.budget)
        detailMovieBinding.tvRevenue.text = formatCurrency(movie.revenue)
        detailMovieBinding.tvTitle.text = movie.title
        detailMovieBinding.btnHomePage.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(WebViewActivity.EXTRA_LINKS, movie.homepage)
            startActivity(intent)
        }

        detailMovieBinding.btnFavorite.apply {
            this@DetailMovie.setStateFavoriteIcon(detailMovieBinding.btnFavorite, movie.isFavorite)
            setOnClickListener {
                movieFavoriteViewModel.setFavoriteMovie(movie)
                this@DetailMovie.showToastFavorite(movie.isFavorite)
            }
        }

        val genres: List<String> = movie.genre.split(",").map { it.trim() }
        with(detailMovieBinding.llGenre){
            this.removeAllViews()
            genres.map { this@DetailMovie.generateButtonTextView(it,this) }
        }

        val languages: List<String> = movie.language.split(",").map { it.trim() }
        with(detailMovieBinding.llLanguage){
            this.removeAllViews()
            languages.map{ this@DetailMovie.generateButtonTextView(it, this)}
        }
    }

    private fun setActionBar(){
        supportActionBar?.title = getString(R.string.detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        Glide.get(this).clearMemory()
        super.onDestroy()
    }
}