package com.example.imagesearch.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.R
import com.example.imagesearch.databinding.ItemHomeBinding
import com.example.imagesearch.repo.RepoFragment
import com.example.imagesearch.repo.toRepoModel

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    private val list = ArrayList<HomeModel>()

    var itemClick: ItemClick? = null
    interface ItemClick{
        fun onClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListAdapter.ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeListAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
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
//            itemHomeIvLove.setImageResource()
        }
    }

    fun getItem(position: Int): HomeModel{
        return list[position]
    }

    fun itemClear() {
        list.clear()
    }

    fun addItem(homeModel: HomeModel) {
        list.add(homeModel)
        notifyDataSetChanged()
    }
}