package com.example.osagocalculation.presentation.insurances

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.osagocalculation.di.main.FragmentScope
import com.example.osagocalculation.domain.Interactor
import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.domain.entities.InsuranceDomain
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@FragmentScope
class InsurancesViewModel @Inject constructor(private val interactor: Interactor) : ViewModel() {

    private val _insurancesLiveData: MutableLiveData<List<InsuranceDomain>> = MutableLiveData()
    val insuranceLiveData: LiveData<List<InsuranceDomain>>
        get() = _insurancesLiveData

    private val _errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val errorLiveData: LiveData<Throwable>
        get() = _errorLiveData

    private val _progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val progressLiveData: LiveData<Boolean>
        get() = _progressLiveData

    private val compositeDisposable = CompositeDisposable()

    fun loadInsurances(factors: List<Factors>) {
        interactor.getInsurances(factors)
            .doOnSubscribe { _progressLiveData.postValue(true) }
            .doOnError { _progressLiveData.postValue(true) }
            .delay(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { _progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _insurancesLiveData.value = it },
                onError = { _errorLiveData.value = it }
            )
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
