package com.example.notesapk.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapk.App
import com.example.notesapk.R
import com.example.notesapk.data.models.NoteModel
import com.example.notesapk.databinding.FragmentNoteBinding
import com.example.notesapk.ui.adapters.NoteAdapter
import com.example.notesapk.ui.interfaces.onClickItem
import com.example.notesapk.utils.PreferenceHelper

class NoteFragment : Fragment(), onClickItem {
    var binding: FragmentNoteBinding? = null
    private val noteAdapter = NoteAdapter(this)
    private val sharedPreferences = PreferenceHelper()

    private var isGridLayout = false // Переменная для отслеживания текущего макета

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences.unit(requireContext())
        loadLayoutManagerState()
        initialize()
        setupListener()
        getData()
    }

    private fun loadLayoutManagerState() {
        isGridLayout = !sharedPreferences.saveLayoutManager
    }

    private fun initialize() {
        // Устанавливаем RecyclerView с начальным LayoutManager (LinearLayoutManager)
        binding?.rvNote?.apply {
            layoutManager = if (isGridLayout){
                GridLayoutManager(requireContext(), 2)
            }else{
                LinearLayoutManager(requireContext())
            }
            adapter = noteAdapter
        }
        updateLayoutManagerIcon()
    }

    private fun updateLayoutManagerIcon() {
        binding?.layoutManager?.setImageResource(if (isGridLayout) R.drawable.shape2 else R.drawable.shape)
    }

    private fun setupListener() = with(binding!!) {
        btnAction.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }

        // Переключаем макет при нажатии на ImageView с id "layout_manager"
        layoutManager.setOnClickListener {
            toggleLayoutManager()
        }
    }

    // Метод для переключения между LinearLayoutManager и GridLayoutManager
    private fun toggleLayoutManager() {
        binding?.rvNote?.apply {
            if (isGridLayout) {
                // Если текущий макет сетка, меняем на LinearLayoutManager
                layoutManager = LinearLayoutManager(requireContext())
                isGridLayout = false
            } else {
                // Если текущий макет список, меняем на GridLayoutManager
                layoutManager = GridLayoutManager(requireContext(), 2) // 2 столбца в сетке
                isGridLayout = true
            }
            saveLayoutManagerState()
            updateLayoutManagerIcon()
        }
    }

    private fun saveLayoutManagerState() {
        sharedPreferences.saveLayoutManager = !isGridLayout
    }

    private fun getData() {
        App.appDataBase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { listModel ->
            noteAdapter.submitList(listModel)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = android.app.AlertDialog.Builder(requireContext())
        with(builder){

        }
    }
}
