package com.project.segunfrancis.coolmovies.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.like.LikeButton
import com.like.OnLikeListener
import com.project.segunfrancis.coolmovies.databinding.FragmentMovieDetailsBinding
import com.project.segunfrancis.coolmovies.ui.favorite.FavoriteViewModel
import com.project.segunfrancis.coolmovies.util.AppConstants.BACKDROP_SIZE
import com.project.segunfrancis.coolmovies.util.AppConstants.IMAGE_BASE_URL
import com.project.segunfrancis.coolmovies.util.loadImage
import com.project.segunfrancis.coolmovies.util.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = args.movieDetails
        with(binding) {
            posterImage.loadImage(IMAGE_BASE_URL.plus(BACKDROP_SIZE).plus(result.backdrop_path))
            titleTextView.text = result.title
            plotTextView.text = result.overview
            releaseDateText.text = result.release_date
            voteAverageText.text = result.vote_average.toString()
            likeButton.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                    viewModel.addFavorite(result)
                }

                override fun unLiked(likeButton: LikeButton?) {
                    viewModel.removeFavorite(result.id)
                }
            })
        }
        viewModel.favoriteMessage.observe(viewLifecycleOwner) {
            binding.root.snack(it, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}