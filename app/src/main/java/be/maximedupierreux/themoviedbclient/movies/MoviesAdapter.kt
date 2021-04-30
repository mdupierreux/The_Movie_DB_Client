package be.maximedupierreux.themoviedbclient.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.maximedupierreux.themoviedbclient.R
import be.maximedupierreux.themoviedbclient.model.Movie
import kotlinx.android.synthetic.main.movie_holder.view.*

class MoviesAdapter(): ListAdapter<Movie, MoviesAdapter.MovieVieHolder>(Movie.DIFF_UTIL_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVieHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_holder, parent, false)
        return MovieVieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieVieHolder, position: Int) {
        val movie = getItem(position)
        holder.bindMovie(movie)
    }

    class MovieVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var movie: Movie
        fun bindMovie(movie: Movie) {
            this.movie = movie
            with(movie){
                itemView.tvMovieTitle.text = originalTitle
                itemView.tvMovieReleaseDate.text = releaseDate
            }
        }
    }
}