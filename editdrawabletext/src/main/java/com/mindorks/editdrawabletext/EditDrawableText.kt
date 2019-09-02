package com.mindorks.editdrawabletext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText
import kotlin.math.abs

class EditDrawableText(context: Context, attrs: AttributeSet) : EditText(context, attrs) {

    private var drawableRight: Drawable? = null
    private var drawableLeft: Drawable? = null
    private var drawableTop: Drawable? = null
    private var drawableBottom: Drawable? = null
    private var positionX: Int = 0
    private var positionY: Int = 0
    private var isDrawableShownWhenTextIsEmpty = true
    private var onDrawableClickListener: OnDrawableClickListener? = null


    init {
        parseAttributes(
                context.obtainStyledAttributes(
                        attrs,
                        R.styleable.EditDrawableText
                )
        )
    }

    private fun parseAttributes(obtainStyledAttributes: TypedArray) {
        isDrawableShownWhenTextIsEmpty = obtainStyledAttributes.getBoolean(R.styleable.EditDrawableText_isDrawableShownWhenTextIsEmpty, isDrawableShownWhenTextIsEmpty);
        obtainStyledAttributes.recycle()
        hasDrawable(isDrawableShownWhenTextIsEmpty)
    }

    fun hasDrawable(value: Boolean) {
        isDrawableShownWhenTextIsEmpty = value
        if (!isDrawableShownWhenTextIsEmpty) this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        invalidate()
    }

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
        val editText = this
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(char: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (char.isEmpty()) {
                    if (!isDrawableShownWhenTextIsEmpty) editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                } else editText.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom)
            }


        })
        if (event.action == MotionEvent.ACTION_DOWN) {
            positionX = event.x.toInt()
            positionY = event.y.toInt()

            // this works for left since container shares 0,0 origin with bounds
            if (drawableLeft != null) {
                bounds = drawableLeft?.bounds
                setupDrawableLeftClick(bounds, event)
            }

            if (drawableRight != null) {
                bounds = drawableRight?.bounds
                setupDrawableRightClick(bounds, event)
            }

            if (drawableTop != null) {
                bounds = drawableTop?.bounds
                setupDrawableTopClick(bounds, event)
            }

            if (drawableBottom != null) {
                bounds = drawableBottom?.bounds
                setupDrawableBottomClick(bounds, event)
            }


        }
        return super.onTouchEvent(event)
    }

    private fun setupDrawableBottomClick(bounds: Rect?, event: MotionEvent) {
        val extraClickingArea = 13
        if (abs((width - paddingLeft - paddingRight) / 2 + paddingLeft - positionX) <= bounds!!.width() / 2 + extraClickingArea) {
            onDrawableClickListener?.onClick(DrawablePosition.BOTTOM)
            event.action = MotionEvent.ACTION_CANCEL
        }
    }

    private fun setupDrawableTopClick(bounds: Rect?, event: MotionEvent) {
        val extraClickingArea = 13
        if (abs((width - paddingLeft - paddingRight) / 2 + paddingLeft - positionX) <= bounds!!.width() / 2 + extraClickingArea) {
            onDrawableClickListener?.onClick(DrawablePosition.TOP)
            event.action = MotionEvent.ACTION_CANCEL
        }
    }

    private fun setupDrawableLeftClick(bounds: Rect?, event: MotionEvent) {
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
            onDrawableClickListener?.onClick(DrawablePosition.LEFT)
            event.action = MotionEvent.ACTION_CANCEL

        }
    }

    private fun setupDrawableRightClick(bounds: Rect?, event: MotionEvent) {
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
            onDrawableClickListener?.onClick(DrawablePosition.RIGHT)
            event.action = MotionEvent.ACTION_CANCEL
        }
    }

    fun setDrawableClickListener(OnDrawableClickListener: OnDrawableClickListener) {
        this.onDrawableClickListener = OnDrawableClickListener
    }

}