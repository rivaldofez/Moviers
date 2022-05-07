package com.rivaldofez.moviers.ui.home.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse
import com.rivaldofez.moviers.databinding.FragmentTvShowBinding
import com.rivaldofez.moviers.ui.detail.tvshow.DetailTvShow
import com.rivaldofez.moviers.utils.SortUtils
import com.rivaldofez.moviers.utils.showLoading
import com.rivaldofez.moviers.viewmodel.ViewModelFactoryTvShow
import com.rivaldofez.moviers.vo.Status

class TvShowFragment : Fragment(), TvShowFragmentCallback {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val factory = ViewModelFactoryTvShow.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter(this)
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, SortUtils.sortFilter)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fragmentTvShowBinding.spinnerSort.adapter = arrayAdapter

            fragmentTvShowBinding.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    viewModel.getPopularTvShows(SortUtils.sortFilter[position]).observe(viewLifecycleOwner, { tvshows ->
                        if(tvshows != null){
                            when(tvshows.status){
                                Status.LOADING -> showLoading(true, fragmentTvShowBinding.loading)
                                Status.SUCCESS -> {
                                    showLoading(false, fragmentTvShowBinding.loading)
                                    tvShowAdapter.submitList(tvshows.data)
                                }
                                Status.ERROR -> {
                                    showLoading(false, fragmentTvShowBinding.loading)
                                    Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            with(fragmentTvShowBinding.rvTvshow){
                layoutManager = GridLayoutManager(context, 3)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onTvShowClick(tvShow: TvShowResponse) {
        val intent = Intent(context, DetailTvShow::class.java)
        intent.putExtra(DetailTvShow.EXTRA_TVSHOW, tvShow.id.toString())
        requireContext().startActivity(intent)
    }
}