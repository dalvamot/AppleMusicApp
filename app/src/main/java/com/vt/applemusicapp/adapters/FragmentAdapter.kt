package com.vt.applemusicapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vt.applemusicapp.views.ClassicFragment
import com.vt.applemusicapp.views.PopFragment
import com.vt.applemusicapp.views.RockFragment

class FragmentAdapter(
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    // the count of the fragments we want to display
    override fun getItemCount(): Int = 3

    // the creation of the fragments depending on the position
    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> RockFragment.newInstance()
            1 -> ClassicFragment.newInstance()
            2 -> PopFragment.newInstance()
            else -> RockFragment.newInstance()
        }
    }
}