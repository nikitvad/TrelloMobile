package com.nikitvad.oryanmat.trellowidget

import android.databinding.BindingAdapter
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class BindingAdapter{

    companion object {

        @BindingAdapter("adapter")
        fun recyclerAdapter(recyclerView: RecyclerView, adapter:NiceRecyclerAdapter< ViewDataBinding, Any>){
            recyclerView.adapter = adapter
        }
    }

}