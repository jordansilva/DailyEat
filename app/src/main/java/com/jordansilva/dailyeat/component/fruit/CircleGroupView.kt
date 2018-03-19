package com.jordansilva.dailyeat.component.fruit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.jordansilva.dailyeat.R
import java.util.*


/**
 * Created by jordansilva on 17/03/18.
 */
class CircleGroupView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var items: ArrayList<CircleItemView> = ArrayList()
    var drawPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var shadowPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        drawPaint.color = ContextCompat.getColor(context, R.color.purple)
        drawPaint.isAntiAlias = true
        drawPaint.style = Paint.Style.FILL

        shadowPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
    }

    fun addItem(@DrawableRes imageId: Int, @ColorRes colorId: Int) {
        val item = CircleItemView(ContextCompat.getDrawable(context, imageId)!!,
                ContextCompat.getColor(context, colorId))
        items.add(item)


        invalidate()
        requestLayout()
    }

    fun configureItem() {
//        item.setImageResource(imageId)
//        item.setBackgroundResource(colorId)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            item.elevation = 2.dp
//        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val shadowSize = 10
        val w = width - (paddingLeft + paddingRight + shadowSize).toFloat()
        val h = height - (paddingTop + paddingBottom + shadowSize).toFloat()
        val radius = radius(w, h)
        val cx = paddingLeft + (w / 2)
        val cy = paddingTop + (h / 2)

//        shadowPaint.shader = LinearGradient(0f, 0f, 0f, cy,
//                Color.parseColor("#3359437A"),
//                Color.parseColor("#FF59437A"),
//                android.graphics.Shader.TileMode.CLAMP)
//        canvas.drawCircle(cx + shadowSize, cy + shadowSize, radius, shadowPaint)
        canvas.drawCircle(cx, cy, radius, drawPaint)

        items.forEach {
            val itemWidth = (w / 2) / items.size.toFloat()
            val itemHeight = (h / 2) / items.size.toFloat()
            val itemRadius = radius(itemWidth, itemHeight)


            it.draw(canvas, itemWidth, itemHeight, itemRadius)
        }
    }

    fun radius(width: Float, height: Float) = Math.min(width, height) / 2.toFloat()

    class CircleItemView(val image: Drawable,
                         val color: Int) {

        var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

        init {
            paint.color = color
        }

        fun draw(canvas: Canvas, x: Float, y: Float, radius : Float) {
            canvas.drawCircle(x, y, radius, paint)
            image.setBounds(150, 150, 0,0 )
            image.draw(canvas)
        }
    }
}