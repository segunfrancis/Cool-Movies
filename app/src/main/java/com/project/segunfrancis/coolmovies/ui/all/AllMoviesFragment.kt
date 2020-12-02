package com.project.segunfrancis.coolmovies.ui.all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.project.segunfrancis.coolmovies.databinding.AllMoviesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class AllMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = AllMoviesFragment()
    }

    private var _binding: AllMoviesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AllMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AllMoviesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter()
        binding.movieRecyclerView.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getTopRatedMovies().collectLatest {
                movieAdapter.submitData(it)
                Timber.d(it.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}