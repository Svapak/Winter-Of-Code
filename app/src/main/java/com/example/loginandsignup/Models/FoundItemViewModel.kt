package com.example.loginandsignup.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginandsignup.Repository.FoundItemRepository

class FoundItemViewModel : ViewModel() {

    private val repository : FoundItemRepository
    private val _allItems = MutableLiveData<List<Item>>()
    val allItems : LiveData<List<Item>> = _allItems

    init {
        repository = FoundItemRepository().getInstance()
        repository.loadItems(_allItems)
    }
}