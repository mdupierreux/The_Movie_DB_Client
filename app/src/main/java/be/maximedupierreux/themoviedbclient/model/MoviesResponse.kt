package be.maximedupierreux.themoviedbclient.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse (
    @field: Json(name = "total_results") var totalResults: String,
    @field: Json(name = "results") var movies: MutableList<Movie>
)