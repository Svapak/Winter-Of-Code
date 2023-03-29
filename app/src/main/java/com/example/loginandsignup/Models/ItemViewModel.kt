package com.example.loginandsignup.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginandsignup.Repository.ItemRepository

class ItemViewModel : ViewModel() {

    private val repository : ItemRepository
    private val _allItems = MutableLiveData<List<Item>>()
    val allItems : LiveData<List<Item>> = _allItems

    init {
        repository = ItemRepository().getInstance()
        repository.loadItems(_allItems)
    }
}