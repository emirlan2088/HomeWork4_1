package com.example.notesapk.views.ui.interfaces

import com.example.notesapk.model.data.models.NoteModel

interface onClickItem {

    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}