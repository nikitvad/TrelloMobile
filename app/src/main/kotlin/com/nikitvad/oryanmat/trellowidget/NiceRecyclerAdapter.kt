package com.nikitvad.oryanmat.trellowidget

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NiceRecyclerAdapter<T: ViewDataBinding, D> constructor(val items:List<D>, var itemLayoutId: Int) : RecyclerView.Adapter<NiceViewHolder<T, D>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NiceViewHolder<T, D> {
        return NiceViewHolder(LayoutInflater.from(parent.context).inflate(itemLayoutId, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NiceViewHolder<T, D>, position: Int) {
        holder.bind(items[position])
    }

//    fun setItems(newItems: List<D>){
//        items.clear()
//        items.addAll(newItems)
//        notifyDataSetChanged()
//    }

}

class NiceViewHolder<T: ViewDataBinding, D>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: D){
        val binding = DataBindingUtil.bind<T>(itemView)
        binding!!.setVariable(BR.datamodel, data)
        binding.executePendingBindings()
    }
}