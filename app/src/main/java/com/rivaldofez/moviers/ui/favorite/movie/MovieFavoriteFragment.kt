package com.rivaldofez.moviers.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.databinding.FragmentMovieFavoriteBinding
import com.rivaldofez.moviers.ui.detail.movie.DetailMovie
import com.rivaldofez.moviers.viewmodel.ViewModelFactoryMovie


class MovieFavoriteFragment : Fragment(), MovieFavoriteCallback {

    private lateinit var movieFavoriteBinding: FragmentMovieFavoriteBinding
    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter
    private lateinit var viewModel: MovieFavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        movieFavoriteBinding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return movieFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(movieFavoriteBinding.rvMovies)

        if(activity != null){
            val factory = ViewModelFactoryMovie.getInstance(requireContext())
            viewModel = ViewModelProvider(this,factory)[MovieFavoriteViewModel::class.java]

            movieFavoriteAdapter = MovieFavoriteAdapter(this)
            viewModel.getAllFavoriteMovies().observe(viewLifecycleOwner, { movies ->
                if(movies != null){
                    if(movies.size != 0){
                        showMessageNull(false)
                        movieFavoriteAdapter.submitList(movies)
                    }else{
                        showMessageNull(true)
                    }
                }
            })

            with(movieFavoriteBinding.rvMovies){
                layoutManager = LinearLayoutManager(context)
                adapter = movieFavoriteAdapter
            }
        }
    }

    override fun onMovieClick(movie: MovieEntity) {
        val intent = Intent(context, DetailMovie::class.java)
        intent.putExtra(DetailMovie.EXTRA_MOVIE, movie.id.toString())
        requireContext().startActivity(intent)
    }

    private fun showMessageNull(state: Boolean){
        if(state){
            movieFavoriteBinding.progressBar.visibility = View.GONE
            movieFavoriteBinding.rvMovies.visibility = View.GONE
            movieFavoriteBinding.tvMessage.text = getString(R.string.null_message)
            movieFavoriteBinding.tvMessage.visibility = View.VISIBLE
        }else{
            movieFavoriteBinding.progressBar.visibility = View.GONE
            movieFavoriteBinding.rvMovies.visibility = View.VISIBLE
            movieFavoriteBinding.tvMessage.visibility = View.GONE
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean =
            true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if(view != null){
                val swipedPosition = viewHolder.layoutPosition
                val movieEntity = movieFavoriteAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavoriteMovie(it) }

                val snackbar = Snackbar.make(view as View, "Batalkan menghapus item sebelumnya?", Snackbar.LENGTH_LONG)

                snackbar.setAction("OK"){
                    movieEntity?.let { viewModel.setFavoriteMovie(it) }
                }
                snackbar.show()
            }
        }
    })
}