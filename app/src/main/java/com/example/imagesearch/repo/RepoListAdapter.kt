package com.example.imagesearch.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.databinding.ItemRepoBinding

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private val list = ArrayList<RepoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListAdapter.ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepoListAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepoModel) = with(binding) {
            item?.img.let {
                Glide.with(itemRepoIv.context)
                    .load(it)
                    .into(itemRepoIv)
            }
            itemRepoTvTitle.text = item.title
            itemRepoTvDate.text = item.date
        }
    }

    fun addItem(item: RepoModel){
        list.add(item)
        notifyDataSetChanged()
    }
}


















