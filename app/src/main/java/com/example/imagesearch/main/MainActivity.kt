package com.example.imagesearch.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.imagesearch.databinding.ActivityMainBinding
import com.example.imagesearch.home.HomeModel
import com.example.imagesearch.repo.RepoFragment
import com.example.imagesearch.repo.toRepoModel
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


    /**
     * 마지막 검색어를 Shared Preferences에 저장
     * context 호출하는 컨텍스트 (일반적으로 Activity 또는 Application)
     * query 저장하려는 검색어 문자열
    */
    fun saveData(context: Context, query: String){
        val prefs = context.getSharedPreferences("lastSearch", Context.MODE_PRIVATE)
        prefs.edit().putString("pref", query).apply()
    }


    fun getLastSearch(context: Context): String? {
        val prefs = context.getSharedPreferences("lastSearch", Context.MODE_PRIVATE)
        return prefs.getString("pref", null)
    }
}