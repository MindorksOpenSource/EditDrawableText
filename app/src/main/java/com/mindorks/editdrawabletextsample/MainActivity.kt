package com.mindorks.editdrawabletextsample

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mindorks.editdrawabletext.DrawablePosition
import com.mindorks.editdrawabletext.OnDrawableClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClickListeners()
    }
    
    private fun setOnClickListeners() {
        drawableEditText.setDrawableClickListener(object : OnDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                when (target) {
                    DrawablePosition.RIGHT -> displayToastMessage("Clicked Right Drawable").show()
                    else -> {
                    }
                }
            }
            
        })
        drawableEditTextLeft.setDrawableClickListener(object : OnDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                when (target) {
                    DrawablePosition.LEFT -> displayToastMessage("Clicked Left Drawable").show()
                    else -> {
                    }
                }
            }
            
            
        })
        drawableEditTextTop.setDrawableClickListener(object : OnDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                when (target) {
                    DrawablePosition.TOP -> displayToastMessage("Clicked TOP Drawable").show()
                    else -> {
                    }
                }
            }


        })
        drawableEditTextBottom.setDrawableClickListener(object : OnDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                when (target) {
                    DrawablePosition.BOTTOM -> displayToastMessage("Clicked BOTTOM Drawable").show()
                    else -> {
                    }
                }
            }


        })
    }

}

/**
 * Extension function to show Toast in activity
 */
fun Activity.displayToastMessage(message: String): Toast {
    return Toast.makeText(this, message, Toast.LENGTH_SHORT)
}
