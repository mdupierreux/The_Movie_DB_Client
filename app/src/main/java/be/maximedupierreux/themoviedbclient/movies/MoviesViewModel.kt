package be.maximedupierreux.themoviedbclient.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import be.maximedupierreux.themoviedbclient.model.Movie
import be.maximedupierreux.themoviedbclient.model.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    var moviesService: MoviesService = MoviesService.create()
    val movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()


    fun findMovies(searchTerm: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val moviesResponse = moviesService.searchMovies("b2168bae3a2c67509eb6b97572f521c2", searchTerm)
            if (moviesResponse.isSuccessful) {
                 movies.value = moviesResponse.body()?.movies?.toMutableList()
                Log.d("TAG", "findMovies: "+moviesResponse.body()?.totalResults)
            }
        }
    }
}