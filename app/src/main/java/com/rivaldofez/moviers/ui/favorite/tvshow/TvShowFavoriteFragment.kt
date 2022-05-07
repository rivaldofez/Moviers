package com.rivaldofez.moviers.ui.favorite.tvshow

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
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.databinding.FragmentTvShowFavoriteBinding
import com.rivaldofez.moviers.ui.detail.tvshow.DetailTvShow
import com.rivaldofez.moviers.viewmodel.ViewModelFactoryTvShow

class TvShowFavoriteFragment : Fragment(), TvShowFavoriteCallback {
    private lateinit var tvshowFavoriteBinding: FragmentTvShowFavoriteBinding
    private lateinit var tvshowFavoriteAdapter: TvShowFavoriteAdapter
    private lateinit var viewModel: TvShowFavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tvshowFavoriteBinding = FragmentTvShowFavoriteBinding.inflate(layoutInflater, container, false)
        return tvshowFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(tvshowFavoriteBinding.rvTvshow)

        val factory = ViewModelFactoryTvShow.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]

        tvshowFavoriteAdapter = TvShowFavoriteAdapter(this)

        viewModel.getAllFavoriteTvShow().observe(viewLifecycleOwner, { tvshows ->
            if(tvshows != null){
                if(tvshows.size != 0){
                    showMessageNull(false)
                    tvshowFavoriteAdapter.submitList(tvshows)
                }else{
                    showMessageNull(true)
                }
            }
        })

        with(tvshowFavoriteBinding.rvTvshow){
            layoutManager = LinearLayoutManager(context)
            adapter = tvshowFavoriteAdapter
        }
    }

    override fun onTvShowClick(tvShow: TvShowEntity) {
        val intent = Intent(context, DetailTvShow::class.java)
        intent.putExtra(DetailTvShow.EXTRA_TVSHOW, tvShow.id.toString())
        requireContext().startActivity(intent)
    }

    private fun showMessageNull(state: Boolean){
        if(state){
            tvshowFavoriteBinding.progressBar.visibility = View.GONE
            tvshowFavoriteBinding.rvTvshow.visibility = View.GONE
            tvshowFavoriteBinding.tvTvshowMessage.text = getString(R.string.null_message)
            tvshowFavoriteBinding.tvTvshowMessage.visibility = View.VISIBLE
        }else{
            tvshowFavoriteBinding.progressBar.visibility = View.GONE
            tvshowFavoriteBinding.rvTvshow.visibility = View.VISIBLE
            tvshowFavoriteBinding.tvTvshowMessage.visibility = View.GONE
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
                val tvShowEntity = tvshowFavoriteAdapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setFavoriteTvShow(it) }

                val snackbar = Snackbar.make(view as View, "Batalkan menghapus item sebelumnya?", Snackbar.LENGTH_LONG)

                snackbar.setAction("OK"){
                    tvShowEntity?.let { viewModel.setFavoriteTvShow(it) }
                }
                snackbar.show()
            }
        }
    })

}