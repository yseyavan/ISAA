package com.praveenan.isaa

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import soup.neumorphism.NeumorphCardView
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var add: NeumorphCardView? = null
    private var visits: NeumorphCardView? = null
    private var notes: NeumorphCardView? = null
    private var resources: NeumorphCardView? = null
    private lateinit var viewPager2: ViewPager2
    private val sliderHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add = findViewById<View>(R.id.add) as NeumorphCardView
        visits = findViewById<View>(R.id.visits) as NeumorphCardView
        notes = findViewById<View>(R.id.notes) as NeumorphCardView
        resources = findViewById<View>(R.id.resources) as NeumorphCardView
        viewPager2 = findViewById(R.id.viewPagerImageSlider)
        val sliderItems: MutableList<SliderItem> = ArrayList()
        sliderItems.add(SliderItem(R.drawable.img1))
        sliderItems.add(SliderItem(R.drawable.img2))
        sliderItems.add(SliderItem(R.drawable.img3))
        sliderItems.add(SliderItem(R.drawable.img4))
        sliderItems.add(SliderItem(R.drawable.img5))
        viewPager2.setAdapter(SliderAdapter(sliderItems, viewPager2))
        add!!.setOnClickListener(this)
        visits!!.setOnClickListener(this)
        notes!!.setOnClickListener(this)
        resources!!.setOnClickListener(this)
        viewPager2.setClipToPadding(false)
        viewPager2.setClipChildren(false)
        viewPager2.setOffscreenPageLimit(3)
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(20))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 4000)
            }
        })
    }

    override fun onClick(view: View) {
        val i: Intent
        when (view.id) {
            R.id.add -> {
                i = Intent(this, AddVisitActivity::class.java)
                startActivity(i)
            }
            R.id.visits -> {
                i = Intent(this, VisitActivity::class.java)
                startActivity(i)
            }
            R.id.notes -> {
                i = Intent(this, NotesActivity::class.java)
                startActivity(i)
            }
            R.id.resources -> {
                i = Intent(this, ResourcesActivity::class.java)
                startActivity(i)
            }
            else -> {
            }
        }
    }

    private val sliderRunnable = Runnable { viewPager2!!.currentItem = viewPager2!!.currentItem + 1 }
    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }
}