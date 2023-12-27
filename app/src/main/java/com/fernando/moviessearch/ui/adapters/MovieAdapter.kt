package com.fernando.moviessearch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fernando.moviessearch.R
import com.fernando.moviessearch.databinding.ViewHolderMovieBinding
import com.fernando.moviessearch.models.Movie
import com.fernando.moviessearch.ui.fragments.HomeFragmentDirections

class MovieAdapter(private val movies: List<Movie>, private val fragment: Fragment) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ViewHolderMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MovieViewHolder(binding, fragment)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        viewHolder.bind(movie)
    }

    class MovieViewHolder(private val binding: ViewHolderMovieBinding, private val fragment: Fragment) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            initListeners(movie)
            binding.apply {
                val movieImageURL = "https://image.tmdb.org/t/p/original/${movie.posterImageUrl}"
                val movieReleaseYear = movie.releaseDate.year.toString()

                movieTitleTextView.text = movie.title
                movieReleaseYearTextView.text = movieReleaseYear
                setMovieImage(movieImageURL)
            }
        }

        private fun initListeners(movie: Movie) {
            binding.viewMoreButton.setOnClickListener {
                val homeAction = HomeFragmentDirections.actionHomeFragmentToMovieInfoFragment(movie.id)
                fragment.findNavController().navigate(homeAction)
            }
        }
        private fun setMovieImage(imageURL: String) {
            Glide.with(binding.root.context)
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.moviePosterImageView)
        }
    }
}