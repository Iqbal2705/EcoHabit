package com.example.mobile_iqbal.Activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mobile_iqbal.Note.FragmentNote

class ActivityTabsAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ActivityTabFragment.newInstance(
                ActivityTabFragment.TAB_JELAJAHI
            )

            1 -> ActivityTabFragment.newInstance(
                ActivityTabFragment.TAB_FAVORIT
            )

            2 -> ActivityTabFragment.newInstance(
                ActivityTabFragment.TAB_RIWAYAT
            )

            3 -> FragmentNote()

            else -> throw IllegalStateException(
                "Posisi tab tidak valid"
            )
        }
    }
}