package com.example.imagesearch.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.imagesearch.R
import com.example.imagesearch.repo.RepoFragment
import com.example.imagesearch.home.HomeFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<MainTabs>()

    init {
        fragments.add(MainTabs(HomeFragment.newInstance(),
            R.string.home_fragment_home_title,
            R.drawable.home
        ))
        fragments.add(MainTabs(
            RepoFragment.newInstance(),
            R.string.home_fragment_repo_title,
            R.drawable.menu
        ))
    }

    fun getIcon(position: Int): Int{
        return fragments[position].icon
    }

    fun getTitle(position: Int): Int{
        return fragments[position].title
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
}