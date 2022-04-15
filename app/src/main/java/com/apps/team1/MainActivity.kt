package com.apps.team1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initHeader()
    }

    private fun initHeader() {
        val viewPager = findViewById<ViewPager2>(R.id.vp_main)

        viewPager.adapter = ScreenSlidePagerAdapter(this)
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        viewPager.currentItem = 2

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val indicator1 = findViewById<View>(R.id.indicator1)
                val indicator2 = findViewById<View>(R.id.indicator2)
                val indicator3 = findViewById<View>(R.id.indicator3)

                when(position) {
                    0 -> {
                        indicator1.setBackgroundColor(resources.getColor(android.R.color.white, null))
                        indicator2.setBackgroundColor(resources.getColor(android.R.color.darker_gray, null))
                        indicator3.setBackgroundColor(resources.getColor(android.R.color.darker_gray, null))
                    }
                    1 -> {
                        indicator1.setBackgroundColor(resources.getColor(android.R.color.darker_gray, null))
                        indicator2.setBackgroundColor(resources.getColor(android.R.color.white, null))
                        indicator3.setBackgroundColor(resources.getColor(android.R.color.darker_gray, null))
                    }
                    2 -> {
                        indicator1.setBackgroundColor(resources.getColor(android.R.color.darker_gray, null))
                        indicator2.setBackgroundColor(resources.getColor(android.R.color.darker_gray, null))
                        indicator3.setBackgroundColor(resources.getColor(android.R.color.white, null))
                    }
                }
            }
        })

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleX = 0.85f + r * 0.15f
        }
        viewPager.setPageTransformer(compositePageTransformer)
    }

    inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment =
            when(position) {
                0 -> GopayCoinsFragment()
                1 -> GopayLaterFragment()
                2 -> GopayFragment()
                else -> Fragment()
            }
    }
}