package be.maximedupierreux.themoviedbclient.movies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import be.maximedupierreux.themoviedbclient.R
import be.maximedupierreux.themoviedbclient.databinding.MoviesListBinding
import kotlinx.android.synthetic.main.movies_list.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MoviesListFragment : Fragment() {

    private var _binding: MoviesListBinding? = null
    private lateinit var viewModel: MoviesViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MoviesListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        viewModel.findMovies("interstellar");

        viewModel.movies.observe(viewLifecycleOwner, { movies ->
            if (movies != null && movies.isNotEmpty()) {
                movies.forEach {
                    Log.d("MOVIE", "onActivityCreated: "+it.originalTitle)
                }
            }
        })

        setupMoviesList()

        etMovieTitle.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.findMovies(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupMoviesList() {
        val adapter = MoviesAdapter()
        val recyclerView = rvMovies
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        viewModel.movies.observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                adapter.submitList(movies)
            }
        })
    }
}