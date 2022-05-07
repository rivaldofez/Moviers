package com.rivaldofez.moviers.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_LINKS = "extra_link"
    }
    private lateinit var webviewBinding: ActivityWebViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webviewBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(webviewBinding.root)
        setActionBar()

        webviewBinding.webTrailer.settings.javaScriptEnabled = true

        webviewBinding.webTrailer.webViewClient = WebViewClient()

        val bundle = intent.extras
        if(bundle != null){
            val trailerUrl = bundle.getString(EXTRA_LINKS)
            if(trailerUrl != null)
                webviewBinding.webTrailer.loadUrl(trailerUrl)
        }
    }

    private fun setActionBar(){
        supportActionBar?.title = getString(R.string.home_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}