package com.example.popularmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.popularmovies.R
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
        val recyclerView = binding.recyclerViewMovies
        recyclerView.adapter = adapter
        val networkConnection = NetworkConnection(requireContext().applicationContext)
        networkConnection.observe(viewLifecycleOwner, Observer { isConnected ->
            if (isConnected) {
                binding.statusInternetText.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                viewModel.listMovie.observe(viewLifecycleOwner, Observer { listMovie ->
                    adapter.submitList(listMovie)
                })
            } else {
                recyclerView.visibility = View.GONE
                binding.statusInternetText.visibility = View.VISIBLE
            }
        })

        return binding.root
    }
}