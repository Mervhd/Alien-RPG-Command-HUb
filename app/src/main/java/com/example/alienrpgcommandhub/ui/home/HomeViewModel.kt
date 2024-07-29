package com.example.alienrpgcommandhub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "ALIEN RPG Command HUB"
    }
    val text: LiveData<String> = _text
}