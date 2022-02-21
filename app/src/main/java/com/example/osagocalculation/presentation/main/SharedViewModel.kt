package com.example.osagocalculation.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.domain.Interactor
import com.example.osagocalculation.domain.entities.Factors
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SharedViewModel(private val interactor: Interactor) : ViewModel() {

    private val _factorsLiveData: MutableLiveData<List<Factors>> = MutableLiveData()
    val factorsLiveData: LiveData<List<Factors>>
        get() = _factorsLiveData

    private val _formItemsLiveData: MutableLiveData<List<FormData>> = MutableLiveData()
    val formItemsLivaData: LiveData<List<FormData>>
        get() = _formItemsLiveData

    private val _errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val errorLiveData: LiveData<Throwable>
        get() = _errorLiveData

    private val _progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val progressLiveData: LiveData<Boolean>
        get() = _progressLiveData

    private val compositeDisposable = CompositeDisposable()

    fun getInitialFactors() {
        interactor.getInitialFactors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _factorsLiveData.value = it },
                onError = { _errorLiveData.value = it }
            )
            .addTo(compositeDisposable)
    }

    fun getCalculatedFactors(formValues: List<FormData>) {
        interactor.getCalculatedFactors(formValues)
            .doOnSubscribe { _progressLiveData.postValue(false) }
            .delay(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .doAfterTerminate { _progressLiveData.postValue(true) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _factorsLiveData.value = it },
                onError = { _errorLiveData.value = it }
            )
            .addTo(compositeDisposable)
    }

    fun getFormItems() {
        interactor.getFormItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _formItemsLiveData.value = it },
                onError = { _errorLiveData.value = it }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
