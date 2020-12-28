package com.project.segunfrancis.coolmovies.ui.now_playing

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
import com.project.segunfrancis.coolmovies.databinding.NowPlayingFragmentBinding
import com.project.segunfrancis.coolmovies.ui.home.HomeFragmentDirections
import com.project.segunfrancis.coolmovies.ui.top_rated.adapter.MovieAdapter
import com.project.segunfrancis.coolmovies.ui.top_rated.adapter.MovieLoadStateAdapter
import com.project.segunfrancis.coolmovies.util.errorMessage
import com.project.segunfrancis.coolmovies.util.loadingIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    private var _binding: NowPlayingFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NowPlayingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NowPlayingFragmentBinding.inflate(layoutInflater)
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
                binding.errorText.text = it.error.errorMessage(requireContext())
            }
        }

        lifecycleScope.launch {
            viewModel.getNowPlayingMovies().collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}