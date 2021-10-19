package com.onurbarman.inviousgchallenge.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.inviousgchallenge.utils.GlideUtils
import com.onurbarman.inviousgchallenge.R
import com.onurbarman.inviousgchallenge.data.model.movie.MovieResponse
import com.onurbarman.inviousgchallenge.databinding.ItemSearchMovieBinding

class SearchAdapter(
    private var movies: List<MovieResponse>,
    private val onMovieClick: (movie: MovieResponse) -> Unit
) : RecyclerView.Adapter<SearchAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchMovieBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(movies: List<MovieResponse>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearAdapter() {
        this.movies = listOf()
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(binding: ItemSearchMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imgPoster: ImageView = itemView.findViewById(R.id.imgPoster)
        private val textName: TextView = itemView.findViewById(R.id.textMovieName)
        private val textGenre: TextView = itemView.findViewById(R.id.textGenre)
        private val textPlot: TextView = itemView.findViewById(R.id.textPlot)
        private val textImdb: TextView = itemView.findViewById(R.id.textImdb)

        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieResponse) {
            itemView.setOnClickListener {
                onMovieClick.invoke(movie)
            }

            movie.run {
                GlideUtils.urlToImageView(imgPoster.context, Poster, imgPoster)
                textName.text = Title
                textGenre.text = Genre
                textPlot.text = Plot
                textImdb.text = imdbRating
            }
        }
    }
}