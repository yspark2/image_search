package com.example.imagesearch.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagesearch.R
import com.example.imagesearch.databinding.FragmentHomeBinding
import com.example.imagesearch.main.MainViewModel
import com.example.imagesearch.main.MainViewModelFactory
import com.example.imagesearch.main.Repository

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
        HomeListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.fragmentHomeBtn.setOnClickListener {
            viewModel.searchImage(binding.fragmentHomeEt.text.trim().toString())

            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // 키보드 숨기기
            imm.hideSoftInputFromWindow(binding.fragmentHomeEt.windowToken, 0)
        }

        viewModel.myCustomPosts.observe(viewLifecycleOwner, Observer { result ->
            if (result.isSuccessful) {

//                Log.d("test", "$result")
                listAdapter.itemClear()
                for (i in result.body()!!.documents!!) {
                    Log.d("test", "$i")
                    val homeModel = HomeModel(
                        img = i.image_url,
                        title = i.siteName,
                        date = i.dateTime,
                        like = false,
                    )
                    listAdapter.addItem(homeModel)
                }
            } else {

                Log.d("test", "fail")

            }
        })
    }

    private fun initView() = with(binding) {
        fragmentHomeRecyclerview.adapter = listAdapter
        fragmentHomeRecyclerview.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}