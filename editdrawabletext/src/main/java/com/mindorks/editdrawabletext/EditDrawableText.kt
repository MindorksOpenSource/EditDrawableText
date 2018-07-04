package com.mindorks.editdrawabletext

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText

class EditDrawableText : EditText {
    
    private var drawableRight: Drawable? = null
    private var drawableLeft: Drawable? = null
    private var drawableTop: Drawable? = null
    private var drawableBottom: Drawable? = null
    private var positionX: Int = 0
    private var positionY: Int = 0
    
    private var onDrawableClickListener: onDrawableClickListener? = null
    
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    
    override fun setCompoundDrawables(leftDrawable: Drawable?,
                                      topDrawable: Drawable?,
                                      rightDrawable: Drawable?,
                                      bottomDrawable: Drawable?) {
        if (leftDrawable != null) drawableLeft = leftDrawable
        if (rightDrawable != null) drawableRight = rightDrawable
        if (topDrawable != null) drawableTop = topDrawable
        if (bottomDrawable != null) drawableBottom = bottomDrawable
        super.setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottomDrawable)
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var bounds: Rect?
        if (event.action == MotionEvent.ACTION_DOWN) {
            positionX = event.x.toInt()
            positionY = event.y.toInt()
           
            
            // this works for left since container shares 0,0 origin with bounds
            if (drawableLeft != null) {
                bounds = drawableLeft!!.bounds
                
                var xClickPosition: Int
                var yClickPosition: Int
                /*
                 * @return pixels into dp
                 */
                val extraClickArea = (13 * resources.displayMetrics.density + 0.5).toInt()
                
                xClickPosition = positionX
                yClickPosition = positionY
                
                if (!bounds!!.contains(positionX, positionY)) {
                    /** Gives some extra space for tapping.  */
                    xClickPosition = positionX - extraClickArea
                    yClickPosition = positionY - extraClickArea
                    
                    if (xClickPosition <= 0) xClickPosition = positionX
                    if (yClickPosition <= 0) yClickPosition = positionY
                    
                    /** Creates square from the smallest value  from x or y*/
                    if (xClickPosition < yClickPosition) yClickPosition = xClickPosition
                }
                
                if (bounds.contains(xClickPosition, yClickPosition) && onDrawableClickListener != null) {
                    onDrawableClickListener!!.onClick(DrawablePosition.LEFT)
                    event.action = MotionEvent.ACTION_CANCEL
                    return false
                    
                }
            }
            
            if (drawableRight != null) {
                bounds = drawableRight!!.bounds
                var xClickPosition: Int
                var yClickPosition: Int
                val extraClickingArea = 13
                
                xClickPosition = positionX + extraClickingArea
                yClickPosition = positionY - extraClickingArea
                
                /**
                 * It right drawable -> subtract the value of x from the width of view. so that width - tapped area                     * will result in x co-ordinate in drawable bound.
                 */
                xClickPosition = width - xClickPosition
                if (xClickPosition <= 0) xClickPosition += extraClickingArea
                
                /* If after calculating for extra clickable area is negative.
                 * assign the original value so that after subtracting
                 * extra clicking area value doesn't go into negative value.
                 */
                
                if (yClickPosition <= 0) yClickPosition = positionY
                
                /**If drawable bounds contains the x and y points then move ahead. */
                if (bounds!!.contains(xClickPosition, yClickPosition) && onDrawableClickListener != null) {
                    onDrawableClickListener!!.onClick(DrawablePosition.RIGHT)
                    event.action = MotionEvent.ACTION_CANCEL
                    return false
                }
                return super.onTouchEvent(event)
            }
            
        }
        return super.onTouchEvent(event)
    }
    
    
    fun setDrawableClickListener(onDrawableClickListener: onDrawableClickListener) {
        this.onDrawableClickListener = onDrawableClickListener
    }
    
}