package com.example.notesapk.ui.fragment.note

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.notesapk.App
import com.example.notesapk.data.models.NoteModel
import com.example.notesapk.databinding.FragmentNoteDetailBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTime()
        setupListener()
        setupTextWatcher()
    }

    private fun setupTime() {
        setupDate()
        val txtTime: TextView = binding.txtTime
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                txtTime.text = currentTime
                handler.postDelayed(this, 1000) // Обновление каждую секунду
            }
        }
        handler.post(runnable)
    }

    private fun setupDate() {
        val txtDate: TextView = binding.txtDate

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault())
        val currentDate = LocalDate.now().format(formatter)
        txtDate.text = currentDate
    }

    private fun setupListener() = with(binding) {
        btnOk.setOnClickListener {
            val etTitle = title.text.toString()
            val etText = text.text.toString()
            App.appDataBase?.noteDao()?.insert(NoteModel(title = etTitle, description = etText))
            findNavController().navigateUp()
        }
        btnBack.setOnClickListener {
            val etTitle = title.text.toString()
            val etText = text.text.toString()
            App.appDataBase?.noteDao()?.insert(NoteModel(title = etTitle, description = etText))
            findNavController().navigateUp()
        }
    }

    private fun setupTextWatcher() = with(binding) {
        val checkInputs = {
            val isNotEmpty = title.text.isNotEmpty() && text.text.isNotEmpty()
            btnOk.visibility = if (isNotEmpty) View.VISIBLE else View.GONE
        }

        title.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkInputs()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkInputs()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        checkInputs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
