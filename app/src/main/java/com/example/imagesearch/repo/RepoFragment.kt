package com.example.imagesearch.repo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagesearch.R
import com.example.imagesearch.databinding.FragmentHomeBinding
import com.example.imagesearch.databinding.FragmentRepoBinding
import com.example.imagesearch.home.HomeModel

class RepoFragment : Fragment() {

    private val listAdapter by lazy{
        RepoListAdapter()
    }

    companion object{
        fun newInstance() = RepoFragment()
    }

    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)

        // 전달된 데이터를 받아옴
        val repoItem = arguments?.getParcelable<RepoModel>("repoItem")


        // repoItem을 사용하여 필요한 작업을 수행
        if(repoItem != null) {
            listAdapter.addItem(repoItem)
//            Log.d("ItemClicked", "Fragment onCreateView")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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