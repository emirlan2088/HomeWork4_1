package com.example.notesapk.presenter.notedetail

import com.example.notesapk.App
import com.example.notesapk.model.data.models.NoteModel

class DetailPresenter(private val view : NoteDetailContract.View) : NoteDetailContract.Presenter{
    override fun saveNote(note: NoteModel) {
        App.appDataBase?.noteDao()?.insert(note)
    }

    override fun updateNote(note: NoteModel) {
        App.appDataBase?.noteDao()?.update(note)

    }

}