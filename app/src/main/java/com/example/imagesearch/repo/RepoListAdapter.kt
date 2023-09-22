package com.example.imagesearch.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.databinding.ItemRepoBinding
import com.example.imagesearch.home.HomeModel

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private val list = ArrayList<RepoModel>()
    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListAdapter.ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepoListAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
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
    fun addItem(item: RepoModel) {
        list.add(item)
        notifyDataSetChanged()
//        if (!list.contains(item)) {
//
//            list.add(item)
//            notifyDataSetChanged()
//
//        }
    }



//    fun getItem(position: Int): RepoModel{
//        return list[position]
//    }

    fun removeItem(position: Int) {
        if (position >= 0 && position < list.size) {
            list.removeAt((position))
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }
    }
}


















