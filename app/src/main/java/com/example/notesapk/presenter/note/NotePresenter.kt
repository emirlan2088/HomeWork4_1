package com.example.notesapk.presenter.note

import com.example.notesapk.App
import com.example.notesapk.model.data.models.NoteModel
import com.example.notesapk.views.ui.fragment.note.NoteFragment

class NotePresenter(private val view: NoteContract.View): NoteContract.Presenter {
    override fun loadNotes() {
        App.appDataBase?.noteDao()?.getAll()?.observeForever{note ->
            view.showNote(note)
        }
    }

    override fun deleteNote(note: NoteModel) {
        App.appDataBase?.noteDao()?.delete(note)
    }
}