package com.rivaldofez.moviers.ui.home.movie

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
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.databinding.FragmentMovieBinding
import com.rivaldofez.moviers.ui.detail.movie.DetailMovie
import com.rivaldofez.moviers.utils.SortUtils
import com.rivaldofez.moviers.utils.showLoading
import com.rivaldofez.moviers.viewmodel.ViewModelFactoryMovie
import com.rivaldofez.moviers.vo.Status


class MovieFragment : Fragment(), MovieFragmentCallback {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater,container,false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val factory = ViewModelFactoryMovie.getInstance(requireContext())
            viewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter(this)
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, SortUtils.sortFilter)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fragmentMovieBinding.spinnerSort.adapter = arrayAdapter

            fragmentMovieBinding.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    viewModel.getPopularMovies(SortUtils.sortFilter[position]).observe(viewLifecycleOwner, { movies ->
                        if(movies != null){
                            when(movies.status){
                                Status.LOADING -> showLoading(true, fragmentMovieBinding.loading)
                                Status.SUCCESS -> {
                                    showLoading(false, fragmentMovieBinding.loading)
                                    movieAdapter.submitList(movies.data)
                                }
                                Status.ERROR -> {
                                    showLoading(false, fragmentMovieBinding.loading)
                                    Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            with(fragmentMovieBinding.rvMovies){
                layoutManager = GridLayoutManager(context, 3)
                adapter = movieAdapter
            }
        }
    }

    override fun onMovieClick(movie: MovieResponse) {
        val intent = Intent(context, DetailMovie::class.java)
        intent.putExtra(DetailMovie.EXTRA_MOVIE, movie.id.toString())
        requireContext().startActivity(intent)
    }
}