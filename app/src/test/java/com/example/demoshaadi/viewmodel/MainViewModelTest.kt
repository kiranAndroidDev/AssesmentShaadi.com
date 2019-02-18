package com.example.demoshaadi.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import com.example.demoshaadi.api.ApiClient
import com.example.demoshaadi.api.ApiEndPoint
import com.example.demoshaadi.api.RxSingleSchedulers
import com.example.demoshaadi.api.model.Response
import com.example.demoshaadi.main.viewmodel.ListViewState
import com.example.demoshaadi.main.viewmodel.MainViewModel
import io.reactivex.Observable
import junit.framework.Assert.assertTrue
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiEndPoint: ApiEndPoint
    @Mock
    lateinit var apiClient: ApiClient
    lateinit var viewModel: MainViewModel
    @Mock
    lateinit var observer: Observer<ListViewState>
    @Mock
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var lifecycle: Lifecycle


    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        viewModel = MainViewModel(apiClient, RxSingleSchedulers.TEST_SCHEDULER)
        viewModel.listState.observeForever(observer)
    }

    @Test
    fun testNull() {
        `when`(apiClient.fetchList()).thenReturn(null)
        assertNotNull(viewModel.listState)
        assertTrue(viewModel.listState.hasObservers())
    }

    @Test
    fun testApiFetchDataSuccess() {
        // Mock API response
        `when`(apiClient.fetchList()).thenReturn(Observable.just(Response()))
        viewModel.fetchList()
        verify<Observer<ListViewState>>(observer).onChanged(ListViewState.LOADING_STATE)
        verify<Observer<ListViewState>>(observer).onChanged(ListViewState.SUCCESS_STATE)
    }

    @Test
    fun testApiFetchDataError() {
        `when`(apiClient.fetchList()).thenReturn(Observable.error(Throwable("Api error")))
        viewModel.fetchList()
        verify<Observer<ListViewState>>(observer).onChanged(ListViewState.LOADING_STATE)
        verify<Observer<ListViewState>>(observer).onChanged(ListViewState.ERROR_STATE)
    }

}