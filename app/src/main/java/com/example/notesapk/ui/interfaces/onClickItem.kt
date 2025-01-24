package com.example.notesapk.ui.interfaces

import com.example.notesapk.data.models.NoteModel

interface onClickItem {
    fun onLongClick(noteModel: NoteModel)
}