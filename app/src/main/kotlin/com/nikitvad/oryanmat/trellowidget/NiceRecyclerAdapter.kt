package com.nikitvad.oryanmat.trellowidget

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NiceRecyclerAdapter<T : ViewDataBinding, D> constructor(val items: List<D>, var itemLayoutId: Int, val onItemClickListener: OnItemClickListener<D>) : RecyclerView.Adapter<NiceRecyclerAdapter<T, D>.NiceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NiceViewHolder {
        return NiceViewHolder(LayoutInflater.from(parent.context).inflate(itemLayoutId, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class NiceViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: D) {
            val binding = DataBindingUtil.bind<T>(itemView)
            binding!!.setVariable(BR.datamodel, data)
            binding.executePendingBindings()
            itemView.setOnClickListener {  onItemClickListener.onItemClick(adapterPosition, data)}

        }

    }
}


interface OnItemClickListener<D> {
    fun onItemClick(pos: Int, item: D)
}
