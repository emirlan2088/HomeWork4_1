package com.example.notesapk.ui.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapk.R
import com.example.notesapk.databinding.FragmentOnBoardBinding
import com.example.notesapk.ui.adapters.OnBoardAdapter


class OnBoardFragment : Fragment() {
    private var binding: FragmentOnBoardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialze()
    }

    private fun initialze() {
        binding?.viewPager?.adapter = OnBoardAdapter(this)
    }


}