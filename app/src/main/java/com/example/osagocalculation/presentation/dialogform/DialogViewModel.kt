package com.example.osagocalculation.presentation.dialogform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DialogViewModel : ViewModel() {

    private val _stateLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val stateLiveData: LiveData<Boolean>
        get() = _stateLiveData

    fun setFragmentListener(state: Boolean) {
        _stateLiveData.value = state
    }

}
