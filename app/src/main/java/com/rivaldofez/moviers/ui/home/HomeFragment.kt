package com.rivaldofez.moviers.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tvshow
        )
    }

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val homePagerAdapter = HomePagerAdapter(requireActivity() as AppCompatActivity)

            fragmentHomeBinding.viewpagerHome.adapter = homePagerAdapter
            TabLayoutMediator(fragmentHomeBinding.tabLayoutHome, fragmentHomeBinding.viewpagerHome){ tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }


}