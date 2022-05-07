package com.rivaldofez.moviers.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rivaldofez.moviers.ui.home.movie.MovieFragment
import com.rivaldofez.moviers.ui.home.tvshow.TvShowFragment

class HomePagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        lateinit var fragment: Fragment

        when(position){
            0 -> {
                val fragmentMovie = MovieFragment()
                fragment = fragmentMovie
            }

            1 -> {
                val fragmentTvShow = TvShowFragment()
                fragment = fragmentTvShow
            }
        }
        return fragment
    }
}