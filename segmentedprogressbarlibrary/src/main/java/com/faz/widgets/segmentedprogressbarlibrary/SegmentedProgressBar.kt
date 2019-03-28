package com.faz.widgets.segmentedprogressbarlibrary

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.faz.segmentedprogressbarlibrary.R

class SegmentedProgressBar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var delimiterSize = DEFAULT_DELIMITER_SIZE

    private var delimiterColor = DEFAULT_DELIMITER_COLOR

    private var activeColor = DEFAULT_ACTIVE_COLOR

    private var inactiveColor = DEFAULT_INACTIVE_COLOR

    var maxValue: Int = DEFAULT_MAX_VALUE
        set(value) {
            if (value > 0) {
                field = value
                curValue = 0
            }
        }

    var curValue: Int = 0
        set(value) {
            field = if (value > maxValue) {
                maxValue
            } else {
                value
            }
            invalidate()
        }

    private var WIDTH: Int = 0

    private var HEIGHT: Int = 0

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.SegmentedProgressBar)
        for (i in 0 until a.indexCount) {
            val attr = a.getIndex(i)
            when (attr) {
                R.styleable.SegmentedProgressBar_maxValue ->
                    maxValue = a.getInteger(attr, DEFAULT_MAX_VALUE)
                R.styleable.SegmentedProgressBar_delimiterSize ->
                    delimiterSize = a.getDimensionPixelSize(attr, DEFAULT_DELIMITER_SIZE)
                R.styleable.SegmentedProgressBar_delimiterColor ->
                    delimiterColor = a.getColor(attr, DEFAULT_DELIMITER_COLOR)
                R.styleable.SegmentedProgressBar_activeColor ->
                    activeColor = a.getColor(attr, DEFAULT_ACTIVE_COLOR)
                R.styleable.SegmentedProgressBar_inactiveColor ->
                    inactiveColor = a.getColor(attr, DEFAULT_INACTIVE_COLOR)
            }
        }
        a.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL

        // background
        paint.color = inactiveColor
        canvas.drawRect(
                0f,
                0f,
                WIDTH.toFloat(),
                HEIGHT.toFloat(),
                paint)

        // foreground
        paint.color = activeColor
        val filledWidth = (curValue.toFloat() / maxValue) * WIDTH
        canvas.drawRect(
                0f,
                0f,
                filledWidth,
                HEIGHT.toFloat(),
                paint)

        // delimiters
        paint.color = delimiterColor
        if (maxValue > 1) {
            val segmentWidth = WIDTH.toFloat() / maxValue
            for (i in 1 until maxValue) {
                val xPos = segmentWidth * i
                canvas.drawRect(
                        xPos,
                        0f,
                        xPos + delimiterSize,
                        HEIGHT.toFloat(),
                        paint
                )
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        WIDTH = w
        HEIGHT = h
    }

    companion object {
        val DEFAULT_INACTIVE_COLOR = Color.parseColor("#eaeaea")
        val DEFAULT_ACTIVE_COLOR = Color.parseColor("#00aec5")
        val DEFAULT_DELIMITER_COLOR = Color.parseColor("#ffffff")
        const val DEFAULT_DELIMITER_SIZE = 2
        const val DEFAULT_MAX_VALUE = 10
    }
}
