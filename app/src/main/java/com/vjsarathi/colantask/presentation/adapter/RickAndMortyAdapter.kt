package com.vjsarathi.colantask.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.vjsarathi.colantask.data.model.response.Result
import com.vjsarathi.colantask.databinding.ItemRickAndMortyBinding
import com.vjsarathi.colantask.presentation.adapter.binder.RickBinder
import com.vjsarathi.colantask.presentation.listener.GenericClickListener

class RickAndMortyAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val listener: GenericClickListener<Result>
) : RecyclerView.Adapter<RickAndMortyAdapter.RickViewHolder>() {


    private var data = emptyList<Result>()


    inner class RickViewHolder(
        private val itemBinding: ItemRickAndMortyBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: Result) {
            val model = RickBinder(listener)
            itemBinding.binder = model
            model.bindData(data, adapterPosition)
            itemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemRickAndMortyBinding.inflate(layoutInflater, parent, false)
        itemBinding.lifecycleOwner = viewLifecycleOwner
        return RickViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: RickViewHolder,
        position: Int
    ) {
        holder.bind(data[position])
        /* Glide.with(holder.itemView).load(list[position].image).into(holder.characterImage)

         // loading character name in text View
         holder.name.text = list[position].name*/
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Result>) {
        this.data = list
        notifyDataSetChanged()
    }
}