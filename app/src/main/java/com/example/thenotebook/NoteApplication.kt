package com.example.thenotebook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// application class for hilt, to create all singleton objects
@HiltAndroidApp
class NoteApplication: Application() {
}