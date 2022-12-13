package com.example.moviestest.ui.movies


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviestest.common.Constants.MOVIE_IMAGE_PATH
import com.example.moviestest.data.models.Movie
import com.example.moviestest.databinding.ListItemMovieBinding
import javax.inject.Inject

class MoviesListAdapter @Inject constructor(val clickListener: OnItemClickListener) :
    ListAdapter<Movie, MoviesListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesListAdapter.ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                movie.also { it ->
                    fullNameTextView.text = it.title
                    yearTextView.text = it.release_year

                    Glide.with(moviePoster.context)
                        .load(MOVIE_IMAGE_PATH + it.poster_path)
                        .into(moviePoster)
                }
            }

            binding.root.setOnClickListener {
//                clickListener.onItemClick(movie)
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Movie)
    }
}


class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}