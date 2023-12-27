package com.fernando.moviessearch.ui.fragments

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fernando.moviessearch.databinding.FragmentHomeBinding
import com.fernando.moviessearch.models.Movie
import com.fernando.moviessearch.ui.adapters.MovieAdapter
import com.fernando.moviessearch.ui.adapters.MovieSkeletonAdapter
import com.fernando.moviessearch.ui.utils.FragmentViewBinding
import com.fernando.moviessearch.ui.viewModels.MoviesViewModel

class HomeFragment : FragmentViewBinding<FragmentHomeBinding>() {
    private val moviesViewModel : MoviesViewModel by viewModels()

    override fun inflate(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setRecyclerViewWithMovieSkeletonAdapter()
        setMovies()
        initListeners()
        initObservables()
    }

    private fun initListeners() {
        binding.searchView.setOnQueryTextListener(object: OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                setRecyclerViewWithMovieSkeletonAdapter()
                moviesViewModel.searchMovies(newText!!)
                return true
            }

        })
    }

    private fun setMovies() {
        moviesViewModel.setMovies()
    }

    private fun setRecyclerViewWithMovieSkeletonAdapter() {
        binding.moviesRecyclerView.adapter = MovieSkeletonAdapter()
    }

    private fun setRecyclerViewWithMovieAdapter(movies: List<Movie>) {
        binding.moviesRecyclerView.adapter = MovieAdapter(movies, this)
    }

    private fun initObservables() {
        moviesViewModel.allMovies.observe(requireActivity()) { movies ->
            setRecyclerViewWithMovieAdapter(movies)
        }

        moviesViewModel.hasError.observe(requireActivity()) { hasError ->
            if (hasError) {
                val alertDialog = AlertDialog.Builder(requireContext()).apply {
                    setTitle("Intern error")
                        .setMessage("Intern application error")
                        .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
                }
                alertDialog.create().show()
            }
        }
    }


    private fun isOrientation(orientation: Int): Boolean = resources.configuration.orientation == orientation

    private fun initRecyclerView() {
        binding.moviesRecyclerView.also {
            val cols = if (isOrientation(Configuration.ORIENTATION_LANDSCAPE)) { 5 } else { 2 }
            it.layoutManager = GridLayoutManager(context, cols)
        }
    }
}