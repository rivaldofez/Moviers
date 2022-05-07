package com.rivaldofez.moviers.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rivaldofez.moviers.ui.favorite.movie.MovieFavoriteFragment
import com.rivaldofez.moviers.ui.favorite.tvshow.TvShowFavoriteFragment

class FavoritePagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        lateinit var fragment: Fragment

        when(position){
            0 -> {
                val fragmentMovie = MovieFavoriteFragment()
                fragment = fragmentMovie
            }

            1 -> {
                val fragmentTvShow = TvShowFavoriteFragment()
                fragment = fragmentTvShow
            }
        }
        return fragment
    }
}