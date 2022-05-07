package com.rivaldofez.moviers.ui.detail.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rivaldofez.moviers.BuildConfig
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.databinding.ActivityDetailTvShowBinding
import com.rivaldofez.moviers.ui.favorite.tvshow.TvShowFavoriteViewModel
import com.rivaldofez.moviers.ui.webview.WebViewActivity
import com.rivaldofez.moviers.utils.*
import com.rivaldofez.moviers.viewmodel.ViewModelFactoryTvShow
import com.rivaldofez.moviers.vo.Status

class DetailTvShow : AppCompatActivity() {
    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    private lateinit var detailTvShowBinding: ActivityDetailTvShowBinding
    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private lateinit var tvShowFavoriteViewModel: TvShowFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(detailTvShowBinding.root)
        setActionBar()

        val factory = ViewModelFactoryTvShow.getInstance(this)
        detailTvShowViewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]
        tvShowFavoriteViewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]

        val bundle = intent.extras
        if(bundle != null){
            val tvShowId = bundle.getString(EXTRA_TVSHOW)
            if(tvShowId != null){
                detailTvShowViewModel.setCurrentTvShow(tvShowId)
                detailTvShowViewModel.getDetailTvShow().observe(this, { tvshow ->
                    when(tvshow.status){
                        Status.LOADING -> showLoading(true, detailTvShowBinding.loading)
                        Status.SUCCESS -> {
                            showLoading(false, detailTvShowBinding.loading)
                            tvshow.data?.let { setViewContent(it) }
                        }
                        Status.ERROR -> {
                            showLoading(false, detailTvShowBinding.loading)
                            Toast.makeText(this, "There is an Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    private fun setViewContent(tvShow: TvShowEntity){
        Glide.with(this).load(BuildConfig.API_PATH_IMAGE + tvShow.posterPath).into(detailTvShowBinding.imgPoster)
        Glide.with(this).load(BuildConfig.API_PATH_IMAGE + tvShow.backdropPath).into(detailTvShowBinding.imgBackdrop)
        detailTvShowBinding.tvDate.text = formatDate(tvShow.firstAirDate)
        detailTvShowBinding.tvTitle.text = tvShow.name
        detailTvShowBinding.tvSynopsis.text = tvShow.overview
        detailTvShowBinding.tvOriginal.text = tvShow.originalName
        detailTvShowBinding.tvHomepage.text = tvShow.homepage
        detailTvShowBinding.tvStatus.text = tvShow.status
        detailTvShowBinding.tvEpisode.text = tvShow.numberOfEpisodes.toString()
        detailTvShowBinding.tvSeason.text = tvShow.numberOfSeasons.toString()
        detailTvShowBinding.tvLatestEpisode.text = getString(R.string.episode_run, tvShow.latestEpisode)
        detailTvShowBinding.chartPopularity.setProgress(tvShow.voteAverage * 10F, true)

        detailTvShowBinding.btnHomePage.setOnClickListener{
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(WebViewActivity.EXTRA_LINKS, tvShow.homepage)
            startActivity(intent)
        }

        detailTvShowBinding.btnFavorite.apply {
            this@DetailTvShow.setStateFavoriteIcon(detailTvShowBinding.btnFavorite,tvShow.isFavorite)
            setOnClickListener {
                tvShowFavoriteViewModel.setFavoriteTvShow(tvShow)
                this@DetailTvShow.showToastFavorite(tvShow.isFavorite)
            }
        }

        val genres: List<String> = tvShow.genre.split(",").map { it.trim() }
        with(detailTvShowBinding.llGenre){
            this.removeAllViews()
            genres.map { this@DetailTvShow.generateButtonTextView(it,this) }
        }

        val languages: List<String> = tvShow.language.split(",").map { it.trim() }
        with(detailTvShowBinding.llLanguage){
            this.removeAllViews()
            languages.map{ this@DetailTvShow.generateButtonTextView(it, this)}
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar(){
        supportActionBar?.title = getString(R.string.detail_tvshow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        Glide.get(this).clearMemory()
        super.onDestroy()
    }
}