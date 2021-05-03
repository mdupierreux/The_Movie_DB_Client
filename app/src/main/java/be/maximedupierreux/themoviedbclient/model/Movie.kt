package be.maximedupierreux.themoviedbclient.model

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @field: Json(name = "original_title") var originalTitle: String?,
    @field: Json(name = "poster_path") var posterPath: String?,
    @field: Json(name = "release_date") var releaseDate: String?,
    @field: Json(name = "id") var id: Int?,
    @field: Json(name = "overview") var overview: String?,
    @field: Json(name = "vote_average") var voteAverage: String?

) {
    companion object {
        val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.originalTitle == newItem.originalTitle
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}