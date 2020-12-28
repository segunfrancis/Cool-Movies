package com.project.segunfrancis.coolmovies.ui.details

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.like.LikeButton
import com.like.OnLikeListener
import com.project.segunfrancis.coolmovies.databinding.FragmentMovieDetailsBinding
import com.project.segunfrancis.coolmovies.ui.MainViewModel
import com.project.segunfrancis.coolmovies.ui.favorite.FavoriteViewModel
import com.project.segunfrancis.coolmovies.ui.model.Genre
import com.project.segunfrancis.coolmovies.util.AppConstants.BACKDROP_SIZE
import com.project.segunfrancis.coolmovies.util.AppConstants.IMAGE_BASE_URL
import com.project.segunfrancis.coolmovies.util.EventObserver
import com.project.segunfrancis.coolmovies.util.State
import com.project.segunfrancis.coolmovies.util.loadImage
import com.project.segunfrancis.coolmovies.util.snack
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: FavoriteViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater)

        // Setting the state of the Like button
        binding.likeButton.isLiked =
            sharedPreferences.getBoolean(args.movieDetails.id.toString(), false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.genreIDs.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Success -> {
                    val items = state.data
                    val result = args.movieDetails
                    val genreIDs: MutableList<Genre> = mutableListOf()
                    for (item in result.genre_ids) {
                        for (genre in items!!) {
                            if (genre.id == item) {
                                genreIDs.add(genre)
                            }
                        }
                    }
                    val tempList: MutableList<String> = mutableListOf()
                    for (genre in genreIDs) {
                        tempList.add(genre.name)
                    }
                    binding.text6.isVisible = tempList.isNotEmpty()
                    binding.genres.text = tempList.toString().removeSurrounding("[", "]")
                }

                is State.Loading -> {}
                is State.Error -> { Timber.e(state.error) }
            }
        }

        val editor = sharedPreferences.edit()
        val result = args.movieDetails
        with(binding) {
            posterImage.loadImage(IMAGE_BASE_URL.plus(BACKDROP_SIZE).plus(result.backdrop_path))
            titleTextView.text = result.title
            plotTextView.text = result.overview
            releaseDateText.text = result.release_date
            progressBar.apply {
                progress = result.vote_average.toFloat()
                labelText = result.vote_average.toString()
            }
            likeButton.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                    viewModel.addFavorite(result)
                    editor.putBoolean(result.id.toString(), true)
                    editor.apply()
                }

                override fun unLiked(likeButton: LikeButton?) {
                    viewModel.removeFavorite(result.id)
                    editor.putBoolean(result.id.toString(), false)
                    editor.apply()
                }
            })
        }
        viewModel.favoriteMessage.observe(viewLifecycleOwner, EventObserver {
            binding.root.snack(it.message, it.warning)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}