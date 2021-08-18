package com.project.zoomview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import java.lang.Float.max
import java.lang.Float.min

class MainActivity : AppCompatActivity() {

    lateinit var scaleGestureDetector: ScaleGestureDetector
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener(image))

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    private inner class ScaleListener(myView: View):
        ScaleGestureDetector.SimpleOnScaleGestureListener(){

            val theView = myView
            var scaleFactor = 1.0f

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            scaleFactor *= detector!!.scaleFactor

            scaleFactor = max(0.1f, min(scaleFactor, 3.0f))

            theView.scaleX = scaleFactor
            theView.scaleY = scaleFactor

            return true
        }
    }
}