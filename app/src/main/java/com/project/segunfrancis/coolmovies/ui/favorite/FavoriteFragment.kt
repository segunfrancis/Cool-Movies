package com.project.segunfrancis.coolmovies.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.project.segunfrancis.coolmovies.databinding.FavoriteFragmentBinding
import com.project.segunfrancis.coolmovies.ui.favorite.adapter.FavoriteMovieAdapter
import com.project.segunfrancis.coolmovies.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteAdapter = FavoriteMovieAdapter { result ->
            val directions = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(result)
            requireParentFragment().findNavController().navigate(directions)
        }
        binding.favoriteRecyclerView.apply {
            adapter = favoriteAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        viewModel.allFavorites.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
            binding.emptyBoxAnimation.isVisible = it.isEmpty()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}