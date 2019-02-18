package com.example.demoshaadi.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import dagger.android.AndroidInjection

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected var binding: B? = null

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, layout)
        super.onCreate(savedInstanceState)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
