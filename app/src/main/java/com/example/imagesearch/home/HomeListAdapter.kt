package com.example.imagesearch.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.databinding.ItemHomeBinding

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    private val list = ArrayList<HomeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListAdapter.ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeListAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeModel) = with(binding) {
            item.img?.let {
                Glide.with(itemHomeIv.context)
                    .load(it)
                    .into(itemHomeIv)
            }
            itemHomeTvTitle.text = item.title
            itemHomeTvDate.text = item.date
        }
    }


    fun itemClear() {
        list.clear()
    }

    fun addItem(homeModel: HomeModel) {
        list.add(homeModel)
//        notifyItemInserted(list.size - 1)
        notifyDataSetChanged()
    }
}