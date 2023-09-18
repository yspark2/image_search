package com.example.imagesearch.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.imagesearch.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewPagerAdapter by lazy {
        MainViewPagerAdapter(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()

    }

    private fun initView() = with(binding){

        activityMainViewpager.adapter = viewPagerAdapter

        TabLayoutMediator(activityMainTab, activityMainViewpager){ tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
            tab.setIcon(viewPagerAdapter.getIcon(position))
        }.attach()

        binding.activityMainViewpager.registerOnPageChangeCallback(
            object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    when(position){
                        0 -> {
                            binding.activityMainFab.show()
                        }
                        1 -> {
                            binding.activityMainFab.hide()
                        }
                    }
                }
            }
        )
    }
}