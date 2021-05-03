package be.maximedupierreux.themoviedbclient.movies

import be.maximedupierreux.themoviedbclient.model.MoviesResponse
import com.squareup.moshi.Moshi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("/3/search/movie")
    suspend fun searchMovies(@Query("api_key") apiKey: String, @Query("query") query: String): Response<MoviesResponse>


    companion object {
        private const val API_URL = "https://api.themoviedb.org/"

        fun create(): MoviesService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .baseUrl(API_URL)
                .build()

            return retrofit.create(MoviesService::class.java)
        }
    }
}