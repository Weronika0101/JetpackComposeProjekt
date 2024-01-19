package com.example.aandroidprojekt

import android.util.Log
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class SharedViewModel : ViewModel() {
    private val _selectedPhoto = mutableIntStateOf(0)
    val selectedPhoto: MutableIntState = _selectedPhoto

    fun setSelectedPhoto(photoIndex: Int) {
        _selectedPhoto.intValue = photoIndex
        Log.d("kupa", selectedPhoto.toString())
        Log.d("kupa2", _selectedPhoto.toString())
    }
}