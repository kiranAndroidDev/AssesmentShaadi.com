package com.example.demoshaadi.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.demoshaadi.api.model.Response
import com.example.demoshaadi.api.model.Results
import com.example.demoshaadi.R
import com.example.demoshaadi.base.BaseActivity
import com.example.demoshaadi.databinding.ActivityMainBinding
import com.example.demoshaadi.main.viewmodel.MainViewModel
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var mainViewModel: MainViewModel? = null
    private var userListAdapter: UserListAdapter? = null
    @Inject lateinit var myViewModelFactory: MyViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this, myViewModelFactory).get(MainViewModel::class.java)
        initUserDataAdapter()
        observeDataChange()
        mainViewModel!!.fetchList()
    }

    private fun initUserDataAdapter() {
        userListAdapter = UserListAdapter(this, ArrayList<Results>())
        binding?.rvUserList?.setLayoutManager(LinearLayoutManager(this))
        binding?.rvUserList?.setAdapter(userListAdapter)

    }

    private fun observeDataChange() {
        mainViewModel!!.listState.observe(this,   Observer {
            when (it?.currentState) {
                0 -> binding?.showLoading = true
                1 -> {
                    binding?.setShowLoading(false)
                    loadNewsData(it.data!!)
                }
                -1 // show error
                -> binding?.setShowLoading(false)
            }
        } )

    }

    private fun loadNewsData(data: Response) {
        userListAdapter!!.addNewsList(data.getResults()!!)
    }


    override val layout: Int = R.layout.activity_main
}

