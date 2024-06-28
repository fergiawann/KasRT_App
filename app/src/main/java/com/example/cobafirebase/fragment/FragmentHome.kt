package com.example.cobafirebase.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.cobafirebase.DataWarga
import com.example.cobafirebase.R
import java.util.Timer
import java.util.TimerTask

class FragmentHome : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var sliderAdapter: SliderAdapter
    private var timer: Timer? = null
    private var currentPage = 0
    private val DELAY_MS: Long = 2000 // Delay in milliseconds before the next page automatically slides.
    private val PERIOD_MS: Long = 4000 // Period between successive page slides.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.viewPager)
        sliderAdapter = SliderAdapter()
        viewPager.adapter = sliderAdapter

        val buttonDataWarga: CardView = view.findViewById(R.id.buttonDataWarga)
        buttonDataWarga.setOnClickListener {
            val intent = Intent(requireContext(), DataWarga::class.java)
            startActivity(intent)
        }

        // Start auto sliding.
        startAutoSlider()

        // Detect swipe gestures manually.
        val gestureDetector = GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean {
                stopAutoSlider() // Stop auto sliding when user interacts with the ViewPager.
                return true
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if (e1 != null && e2 != null) {
                    if (e1.x < e2.x) {
                        // Swipe left.
                        viewPager.currentItem = viewPager.currentItem - 1
                    } else if (e1.x > e2.x) {
                        // Swipe right.
                        viewPager.currentItem = viewPager.currentItem + 1
                    }
                    startAutoSlider() // Restart auto sliding after manual swipe.
                }
                return super.onScroll(e1, e2, distanceX, distanceY)
            }
        })

        viewPager.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event) // Detect touch events.
            false
        }
    }

    private fun startAutoSlider() {
        val handler = Handler()
        val update = Runnable {
            if (currentPage == sliderAdapter.itemCount) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }

        // Start auto sliding.
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)
    }

    private fun stopAutoSlider() {
        timer?.cancel()
        timer = null
    }

    inner class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

        private val images = intArrayOf(R.drawable.banner_welcome, R.drawable.banner_welcome, R.drawable.banner_welcome)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.home_fragment, parent, false)
            return SliderViewHolder(view)
        }

        override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
            val imageResId = images[position]
            when (position) {
                0 -> holder.imageView1.setImageResource(imageResId)
                1 -> holder.imageView2.setImageResource(imageResId)
                2 -> holder.imageView3.setImageResource(imageResId)
            }
        }

        override fun getItemCount(): Int {
            return images.size
        }

        inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView1: ImageView = itemView.findViewById(R.id.sliderImage1)
            val imageView2: ImageView = itemView.findViewById(R.id.sliderImage2)
            val imageView3: ImageView = itemView.findViewById(R.id.sliderImage3)
        }
    }
}

