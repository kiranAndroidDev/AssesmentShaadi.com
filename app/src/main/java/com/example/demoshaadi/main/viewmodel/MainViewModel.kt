package com.example.demoshaadi.main.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.example.demoshaadi.api.model.Response
import com.example.demoshaadi.api.ApiClient
import com.example.demoshaadi.api.RxSingleSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

import javax.inject.Inject


class MainViewModel @Inject
constructor(internal var apiClient: ApiClient, internal var rxSingleSchedulers: RxSingleSchedulers) : ViewModel() {
    internal var disposable: CompositeDisposable? = null
    var listState = MutableLiveData<ListViewState>()
        internal set

    init {
        disposable = CompositeDisposable()
    }

    fun fetchList() {
        disposable!!.add(apiClient.fetchList()
            .doOnSubscribe { newsList -> onLoading() }
            .compose(rxSingleSchedulers.applySchedulers())
            .subscribe(
                { this.onSuccess(it) },
                { this.onError(it) })
        )
    }

    private fun onSuccess(response: Response) {
        ListViewState.SUCCESS_STATE.data = response
        listState.postValue(ListViewState.SUCCESS_STATE)
    }

    private fun onError(error: Throwable) {
        ListViewState.ERROR_STATE.error = error
        listState.postValue(ListViewState.ERROR_STATE)
    }

    private fun onLoading() {
        listState.postValue(ListViewState.LOADING_STATE)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }
}
