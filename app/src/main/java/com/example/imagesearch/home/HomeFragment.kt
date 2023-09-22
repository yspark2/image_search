package com.example.imagesearch.home

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagesearch.R
import com.example.imagesearch.databinding.FragmentHomeBinding
import com.example.imagesearch.main.MainActivity
import com.example.imagesearch.main.MainViewModel
import com.example.imagesearch.main.MainViewModelFactory
import com.example.imagesearch.main.Repository
import com.example.imagesearch.main.SharedViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var sharedViewModel: SharedViewModel
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
            MainActivity().saveData(requireContext(), binding.fragmentHomeEt.text.trim().toString())

            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // 키보드 숨기기
            imm.hideSoftInputFromWindow(binding.fragmentHomeEt.windowToken, 0)
        }


        viewModel.myCustomPosts.observe(viewLifecycleOwner, Observer { result ->
            if (result.isSuccessful) {

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

        // ViewModel 초기화
        sharedViewModel = ViewModelProvider(requireActivity() as MainActivity)[SharedViewModel::class.java]

        listAdapter.itemClick = object : HomeListAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                val selectedItem = listAdapter.getItem(position)
                sharedViewModel.selectedItem.value = selectedItem
            }
        }
    }

    private fun initView() = with(binding) {
        val lastSearch = MainActivity().getLastSearch(requireContext())
        binding.fragmentHomeEt.text = lastSearch?.let{ Editable.Factory.getInstance().newEditable(it)}
        fragmentHomeRecyclerview.adapter = listAdapter
        fragmentHomeRecyclerview.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        // 주변에 아이템 깜빡이는 버그 현상 제거
        fragmentHomeRecyclerview.itemAnimator = null

        binding.fragmentHomeFab.setOnClickListener {
            binding.fragmentHomeRecyclerview.smoothScrollToPosition(0)
        }

//        binding.fragmentHomeRecyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                var isFabVisible = true
//                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//                if(firstVisibleItemPosition == 0 && isFabVisible){
//                    binding.fragmentHomeFab.hide()
//                    isFabVisible = false
//                }else if(firstVisibleItemPosition > 0 && !isFabVisible) {
//                    binding.fragmentHomeFab.show()
//                    isFabVisible = true
//                }
//
//            }
//        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}