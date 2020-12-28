package com.project.segunfrancis.coolmovies.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.project.segunfrancis.coolmovies.ui.top_rated.TopRatedMoviesFragment
import com.project.segunfrancis.coolmovies.ui.favorite.FavoriteFragment
import com.project.segunfrancis.coolmovies.ui.now_playing.NowPlayingFragment
import com.project.segunfrancis.coolmovies.ui.popular.PopularMoviesFragment

/**
 * Created by SegunFrancis
 */

class HomePagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> PopularMoviesFragment()
            1 -> TopRatedMoviesFragment()
            2 -> NowPlayingFragment()
            3 -> FavoriteFragment()
            else -> throw IllegalStateException("Unknown Item")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            0 -> "Popular"
            1 -> "Top Rated"
            2 -> "Now Playing"
            3 -> "Favorites"
            else -> throw IllegalStateException("Unknown Item")
        }
    }
}