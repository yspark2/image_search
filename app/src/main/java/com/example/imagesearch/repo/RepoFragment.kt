package com.example.imagesearch.repo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagesearch.R
import com.example.imagesearch.databinding.FragmentHomeBinding
import com.example.imagesearch.databinding.FragmentRepoBinding
import com.example.imagesearch.home.HomeModel
import com.example.imagesearch.main.MainActivity
import com.example.imagesearch.main.SharedViewModel

class RepoFragment : Fragment() {

    lateinit var sharedViewModel: SharedViewModel
    private val listAdapter by lazy {
        RepoListAdapter()
    }

    companion object {
        fun newInstance() = RepoFragment()
    }

    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel =
            ViewModelProvider(requireActivity() as MainActivity)[SharedViewModel::class.java]

        //선택된 데이터를 관찰하고 데이터가 변경될때마다 RepoListAdapter에 추가
        sharedViewModel.selectedItem.observe(viewLifecycleOwner, Observer { selectedItem ->

            if (selectedItem != null) {
                listAdapter.addItem(selectedItem.toRepoModel())
                Log.d("ItemClicked", "추가 : $selectedItem")
//                listAdapter.notifyDataSetChanged()
            }
        })

        listAdapter.itemClick = object: RepoListAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Log.d("ItemClicked", "삭제 : $position")
                listAdapter.removeItem(position)
                sharedViewModel.selectedItem.value?.like = false

            }
        }
        initView()
    }

    private fun initView() = with(binding) {

        repoRecyclerview.adapter = listAdapter
        repoRecyclerview.layoutManager =
            StaggeredGridLayoutManager(2, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL)
        // 주변에 아이템 깜빡이는 버그 현상 제거
        repoRecyclerview.itemAnimator = null

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}