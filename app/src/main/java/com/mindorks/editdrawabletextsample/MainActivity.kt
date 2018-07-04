package com.mindorks.editdrawabletextsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mindorks.editdrawabletext.DrawablePosition
import com.mindorks.editdrawabletext.EditDrawableText
import com.mindorks.editdrawabletext.onDrawableClickListener

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val drawable_editText: EditDrawableText = findViewById(R.id.drawable_editText)
        onClickListeners(drawable_editText)
        
    }
    
    private fun onClickListeners(drawable_editText: EditDrawableText) {
        drawable_editText.setDrawableClickListener(object : onDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                when (target) {
                    DrawablePosition.RIGHT -> displayToastMessage("Clicked Right Drawable").show()
                    DrawablePosition.LEFT -> displayToastMessage("Clicked Left Drawable").show()
                }
            }
            
            
        })
    }
    
    fun displayToastMessage(message: String): Toast {
        return Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
    }
}
