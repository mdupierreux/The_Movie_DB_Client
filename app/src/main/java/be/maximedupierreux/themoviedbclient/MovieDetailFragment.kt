package be.maximedupierreux.themoviedbclient

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.maximedupierreux.themoviedbclient.databinding.MovieDetailsFragmentBinding
import be.maximedupierreux.themoviedbclient.movies.MoviesViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_details_fragment.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private var _binding: MovieDetailsFragmentBinding? = null
    private lateinit var viewModel: MoviesViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)

        val filmId = arguments?.getInt("movieId")
        filmId?.let {
            val film = viewModel.findMovie(it)
            binding.tvSynopsis.text = film?.overview
            binding.tvTitle.text = film?.originalTitle

            binding.btnWebsite.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.themoviedb.org/")
                startActivity(intent)
            }

            context?.let { context ->
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original/${film?.posterPath}")
                    .centerCrop()
                    .into(binding.ivPoster)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}