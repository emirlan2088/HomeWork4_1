package com.example.notesapk.views.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.notesapk.views.ui.fragment.onboard.OnBoardPagerFragment

class OnBoardAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int) = OnBoardPagerFragment().apply {
        arguments = Bundle().apply {
            putInt(OnBoardPagerFragment.ARG_POSITION,position)
        }
    }
}