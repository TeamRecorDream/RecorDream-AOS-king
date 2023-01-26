package com.recodream_aos.recordream.presentation

import android.content.Intent
import android.os.Bundle
import com.recodream_aos.recordream.R
import com.recodream_aos.recordream.base.BindingActivity
import com.recodream_aos.recordream.databinding.ActivityMainBinding
import com.recodream_aos.recordream.presentation.mypage.MypageActivity
import com.recodream_aos.recordream.presentation.storagy.fragment.StorageFragment

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        clickEvent()
//        initNav()
        setOnClick()
    }

    private fun displayFragment() {
//        supportFragmentManager.beginTransaction()
//            .add(R.id.home_container, HomeFragment())
//            .commit()
//
//        binding.bnvHome.setOnItemSelectedListener {
//            changeFragment(
//                when(it.itemId){
//                    R.id.home_menu -> HomeFragment()
//                    R.id.gallery_menu -> GalleryFragment()
//                    else -> SearchFragment()
//                }
//            )
//            true
//        }
//        binding.bnvHome.selectedItemId = R.id.home_menu
//    }
//
//    private fun changeFragment(fragment: Fragment){
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.home_container,fragment)
//            .commit()
    }

//    private fun initNav() {
//        NavigationUI.setupWithNavController(
//            binding.bnvMainCustomnav,
//            findNavController(R.id.fcv_main_navhostfragment)
//        )
//    }

    //    private fun clickEvent() {
//        with(binding) {
//            ivMainMypage.setOnClickListener {
//                val intent = Intent(this@MainActivity, MypageActivity::class.java)
//                startActivity(intent)
//            }
//
//            ivMainWritebtn.setOnClickListener {
//                val intent = Intent(this@MainActivity, WriteActivity::class.java)
//                startActivity(intent)
//            }
//            ivMainSearch.setOnClickListener {
//                val intent = Intent(this@MainActivity, SearchActivity::class.java)
//                startActivity(intent)
//            }
//        }
    private fun setOnClick() {
        binding.ivMainMypage.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }
        binding.ivMainSearch.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main_navhostfragment, StorageFragment()).commit()
        }

    }
}
