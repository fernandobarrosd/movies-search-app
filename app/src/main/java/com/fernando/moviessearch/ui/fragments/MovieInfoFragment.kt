package com.fernando.moviessearch.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.Visibility
import com.bumptech.glide.Glide
import com.fernando.moviessearch.R
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
        showMovieInfoSkeleton()
        requestMovie(movieInfoArgs.movieID)
        initObservables()
    }

    private fun requestMovie(movieID: Long) {
        movieViewModel.setMovie(movieID)
    }

    private fun showMovieInfoSkeleton() {
        binding.movieSkeletonLinearLayout.visibility = View.VISIBLE
        binding.movieInfoLinearLayout.visibility = View.GONE
    }

    private fun showMovieInfo() {
        binding.movieInfoLinearLayout.visibility = View.VISIBLE
        binding.movieSkeletonLinearLayout.visibility = View.GONE
    }

    private fun initObservables() {
        movieViewModel.movie.observe(requireActivity()) { movie ->
            showMovieInfo()
            binding.apply {
                val movieImageURL = "https://image.tmdb.org/t/p/original/${movie.posterImageUrl}"
                val movieReleaseYear = movie.releaseDate.year.toString()

                movieTitleTextView.text = movie.title
                movieReleaseYearTextView.text = movieReleaseYear
                if (movie.sinopse.isNotEmpty()) {
                    movieSinopseTextView.text = movie.sinopse
                }
                else {
                    movieSinopseTextView.text = "Sinopse indisponivel"
                }
                setMovieImage(movieImageURL)
            }
        }
    }

    private fun setMovieImage(imageURL: String) {
        Glide.with(binding.root.context)
            .load(imageURL)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.moviePosterImageView)
    }
}