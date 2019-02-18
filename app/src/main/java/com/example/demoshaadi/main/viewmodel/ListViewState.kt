package com.example.demoshaadi.main.viewmodel

import com.example.demoshaadi.api.model.Response
import com.example.demoshaadi.base.BaseViewState

class ListViewState private constructor(data: Response?, currentState: Int, error: Throwable?) :
    BaseViewState<Response>() {
    init {
        this.data = data
        this.error = error
        this.currentState = currentState
    }

    companion object {

        var ERROR_STATE = ListViewState(null, BaseViewState.State.FAILED.value, Throwable())
        var LOADING_STATE = ListViewState(null, BaseViewState.State.LOADING.value, null)
        var SUCCESS_STATE = ListViewState(Response(), BaseViewState.State.SUCCESS.value, null)
    }


}
