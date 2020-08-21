package com.example.popularmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.adapters.MovieAdapter
import com.example.popularmovies.databinding.FragmentPopularMoviesBinding
import com.example.popularmovies.network.NetworkConnection
import com.example.popularmovies.view_models.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
    private val viewModel: PopularMoviesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val adapter = MovieAdapter()
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        val recyclerView = binding.recyclerViewMovies
        recyclerView.adapter = adapter

        val statusInternet = binding.statusInternet
        val networkConnection = NetworkConnection(requireContext().applicationContext)
        networkConnection.observe(viewLifecycleOwner, Observer { isConnected ->
            if (isConnected) {
                statusInternet.visibility = View.GONE
                viewModel.moviePagedList.observe(viewLifecycleOwner, Observer { listMovie ->
                    adapter.submitList(listMovie)
                })
            } else {
                statusInternet.visibility = View.VISIBLE
            }
        })

        return binding.root
    }
}