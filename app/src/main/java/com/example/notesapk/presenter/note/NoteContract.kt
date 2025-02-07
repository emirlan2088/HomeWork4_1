package com.example.notesapk.presenter.note

import com.example.notesapk.model.data.models.NoteModel

interface NoteContract {
    interface View{
        fun showNote(note: List<NoteModel>)
    }
    interface Presenter{
        fun loadNotes()
        fun deleteNote(note: NoteModel)
    }
}