package com.project.segunfrancis.coolmovies.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.project.segunfrancis.coolmovies.ui.all.AllMoviesFragment
import com.project.segunfrancis.coolmovies.ui.favorite.FavoriteFragment

/**
 * Created by SegunFrancis
 */

class HomePagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 2
    }


    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> AllMoviesFragment()
            1 -> FavoriteFragment()
            else -> throw IllegalStateException("Unknown Item")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "All Movies"
            1 -> "Favorites"
            else -> throw IllegalStateException("Unknown Item")
        }
    }
}