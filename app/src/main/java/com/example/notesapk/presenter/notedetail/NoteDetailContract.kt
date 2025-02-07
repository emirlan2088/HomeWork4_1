package com.example.notesapk.presenter.notedetail

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.example.notesapk.model.data.models.NoteModel

interface NoteDetailContract {
    interface  View {
        fun  swowNote(note: NoteModel)
        fun showError(message: String)
    }
    interface Presenter {
        fun saveNote(note: NoteModel)
        fun updateNote(note: NoteModel)
    }
}