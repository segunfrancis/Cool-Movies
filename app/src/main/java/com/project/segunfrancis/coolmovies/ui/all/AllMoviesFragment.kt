package com.project.segunfrancis.coolmovies.ui.all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.project.segunfrancis.coolmovies.R
import com.project.segunfrancis.coolmovies.databinding.AllMoviesFragmentBinding
import com.project.segunfrancis.coolmovies.ui.all.adapter.MovieAdapter
import com.project.segunfrancis.coolmovies.ui.all.adapter.MovieLoadStateAdapter
import com.project.segunfrancis.coolmovies.ui.home.HomeFragmentDirections
import com.project.segunfrancis.coolmovies.util.loadingIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@AndroidEntryPoint
class AllMoviesFragment : Fragment() {

    private var _binding: AllMoviesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AllMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AllMoviesFragmentBinding.inflate(layoutInflater)
        binding.loadingIndicator.setImageDrawable(loadingIndicator(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter { result ->
            val directions = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(result!!)
            requireParentFragment().findNavController().navigate(directions)
        }
        binding.retryButton.setOnClickListener { movieAdapter.retry() }
        binding.movieRecyclerView.apply {
            adapter = movieAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter { movieAdapter.retry() }
            )
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        movieAdapter.addLoadStateListener { loadState ->
            binding.movieRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.loadingIndicator.isVisible = loadState.source.refresh is LoadState.Loading
            binding.errorText.isVisible = loadState.source.refresh is LoadState.Error
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            binding.noConnectionAnimation.isVisible = loadState.source.refresh is LoadState.Error

            val errorState =
                loadState.source.append as? LoadState.Error ?: loadState.append as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error
            errorState?.let {
                binding.errorText.text = when(it.error) {
                    is UnknownHostException -> resources.getString(R.string.text_error_unknown_host_exception)
                    is SocketTimeoutException -> resources.getString(R.string.text_error_socket_timeout_exception)
                    else -> resources.getString(R.string.text_error_general)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.getTopRatedMovies().collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}