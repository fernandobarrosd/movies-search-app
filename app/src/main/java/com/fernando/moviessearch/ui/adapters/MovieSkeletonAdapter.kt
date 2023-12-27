package com.fernando.moviessearch.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fernando.moviessearch.R

class MovieSkeletonAdapter : RecyclerView.Adapter<MovieSkeletonAdapter.MovieSkeletonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSkeletonViewHolder {
        val skeletonCard = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_skeleton, parent, false)
        return MovieSkeletonViewHolder(skeletonCard)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: MovieSkeletonViewHolder, position: Int) {

    }

    class MovieSkeletonViewHolder(private val view: View) : RecyclerView.ViewHolder(view)
}