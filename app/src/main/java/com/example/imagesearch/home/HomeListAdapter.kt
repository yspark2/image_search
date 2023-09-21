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

//            Log.d("ItemClicked", "position $position at onBindViewHolder")

            // 클릭한 아이템의 정보
            val clickItem = list[position]

            // 클릭한 아이템의 정보를 RepoModel로 변환
            val repoItem = clickItem.toRepoModel()

//            Log.d("ItemClicked", repoItem.toString())

            // RepoFragment에 클릭한 아이템의 정보를 전달
            val repoFragment = RepoFragment.newInstance()
            val bundle = Bundle()
            bundle.putParcelable("repoItem", repoItem)
            repoFragment.arguments = bundle


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

    fun itemClear() {
        list.clear()
    }

    fun addItem(homeModel: HomeModel) {
        list.add(homeModel)
//        notifyItemInserted(list.size - 1)
        notifyDataSetChanged()
    }
}