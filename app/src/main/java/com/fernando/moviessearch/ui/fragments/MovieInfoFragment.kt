package com.fernando.moviessearch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.fernando.moviessearch.databinding.FragmentMovieInfoBinding
import com.fernando.moviessearch.ui.utils.FragmentViewBinding
import com.fernando.moviessearch.ui.viewModels.MovieViewModel

class MovieInfoFragment : FragmentViewBinding<FragmentMovieInfoBinding>() {
    private val movieInfoArgs : MovieInfoFragmentArgs by navArgs()
    private val movieViewModel : MovieViewModel by viewModels()

    override fun inflate(inflater: LayoutInflater): FragmentMovieInfoBinding {
        return FragmentMovieInfoBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestMovie(movieInfoArgs.movieID)
        initObservables()
    }

    private fun requestMovie(movieID: Long) {
        movieViewModel.setMovie(movieID)
    }

    private fun initObservables() {
        movieViewModel.movie.observe(requireActivity()) { movie ->
            println(movie)
        }
    }
}