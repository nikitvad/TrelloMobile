package com.nikitvad.oryanmat.trellowidget

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : BaseViewModel, VB : ViewDataBinding> : DaggerAppCompatActivity() {
    lateinit var viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, getLayoutId())

        viewBinding.setVariable(BR.viewModel, getViewModel())
        getViewModel().onBind()
        viewBinding.executePendingBindings()

    }


    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): T


}
