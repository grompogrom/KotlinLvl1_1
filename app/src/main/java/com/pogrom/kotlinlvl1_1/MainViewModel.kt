package com.pogrom.kotlinlvl1_1

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _itemsList = mutableStateListOf<Item>()
    val items: List<Item> = _itemsList
//    init {
//        _itemsList.addAll((1..20).toList().map { Item(it) })
//    }


    fun addItem(){
        val index = _itemsList.size
        val color = index % 2 == 0
        _itemsList.add(Item(index, color))
    }
}