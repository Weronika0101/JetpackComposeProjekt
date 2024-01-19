package com.example.aandroidprojekt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.aandroidprojekt.data.DBItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = MyRepository(app.applicationContext)

    fun getContacts(): Flow<List<DBItem>>? {
        return repo.getData3()
    }
    fun insertItem(item: DBItem) {
        repo.addItem(item)
    }

    fun deleteContact(contact: DBItem) = CoroutineScope(viewModelScope.coroutineContext).launch {
        repo.deleteItem(contact)
    }
    fun getContactById(itemId: String): DBItem? {
        return repo.getDataById(itemId.toLong())
    }


    fun updateItem(updatedItem: DBItem) {
        repo.updateItem(updatedItem)

    }
}