package com.rivaldofez.moviers.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tvshow
        )
    }

    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val favoritePagerAdapter = FavoritePagerAdapter(requireActivity() as AppCompatActivity)

            fragmentFavoriteBinding.viewpagerFavorite.adapter = favoritePagerAdapter
            TabLayoutMediator(fragmentFavoriteBinding.tabLayoutFavorite, fragmentFavoriteBinding.viewpagerFavorite){ tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }
}